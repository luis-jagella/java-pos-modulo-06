# ADR 003 — Pagamentos resilientes e idempotentes

Status: aceito para o exercício; não implantado. Não realiza cobranças reais.

## Contexto

O gateway pode ficar lento, retornar erro ou processar uma cobrança sem que sua resposta chegue. Cliques repetidos e redelivery não podem criar cobranças adicionais. Não confundir indisponibilidade técnica com pagamento recusado pelo banco emissor.

## Opções consideradas

- HTTP síncrono com timeout: simples, mas ainda expõe o usuário à espera e exige tratamento de duplicação e resultado desconhecido.
- Fila sem idempotência: protege recursos da API, mas pode duplicar cobranças.
- Pedido durável, fila e idempotência no negócio e no provedor: escolhido, com reconciliação e maior complexidade.

## Decisão

1. Criar pedido e operação com chave estável por tentativa de negócio. Vincular a chave ao usuário, pedido, valor, moeda e fingerprint do payload. Usar restrição UNIQUE e transação no banco; reutilização com payload diferente deve ser rejeitada. O servidor também evita operações concorrentes para o mesmo pedido, mesmo se o cliente inventar novas chaves.
2. Gravar outbox na mesma transação e retornar `202 Accepted` com status consultável. Isso confirma recebimento, **não pagamento**.
3. Publicar comandos com ID da operação. O consumidor faz claim/lease atômico e respeita transições de estado; uma operação já concluída não é cobrada novamente. Idempotência não pode ser um simples `GET` seguido de `SET` sujeito a corrida.
4. Enviar a mesma chave de idempotência ao provedor conforme seu contrato e período de retenção. Definir timeouts, limite de concorrência, circuit breaker e retries apenas para falhas elegíveis, com backoff, jitter e orçamento total. Circuit breaker não impede duplicação por si só.
5. Em timeout após envio, registrar `RESULTADO_DESCONHECIDO`. Consultar o provedor/reconciliar antes de tentar uma nova cobrança. Se o provedor não oferecer garantias suficientes, encaminhar para tratamento manual; não presumir que o erro cancelou o débito.
6. Consumir webhooks autenticados e deduplicados. Atualizar resultado, ledger e outbox de notificação de forma transacional. Autorização de download depende do resultado confirmado. DLQ tem alarmes e processo explícito de reprocessamento com a mesma identidade.

## Consequências

A API deixa de esperar pelo parceiro e a operação fica rastreável, mas o frontend precisa mostrar pendência real e suportar consulta/notificação. Há atrasos e custo de operação. Aceitar fila de pagamento é uma escolha de produto que precisa de validação com o negócio (validade do preço, estoque, cancelamento e expectativa do cliente).

Redis pode auxiliar em limitação de tráfego, mas a verdade durável de idempotência e saldo fica no banco. Uma política de expiração não pode esquecer uma operação ainda conciliável. Não prometemos exactly-once distribuído; modelamos efeitos deduplicados e resultados ambíguos de forma explícita.

Veja o [diagrama de sequência](../README.md#fluxo-de-resiliência-de-pagamento). Estes mecanismos são uma proposta documentada, não uma integração de produção.
