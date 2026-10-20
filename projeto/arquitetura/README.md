# Arquitetura Cotubify

Exercícios de System Design das tarefas 07, 12 e 14. O Cotubify é uma plataforma conceitual de autopublicação: autores conectam repositórios Markdown; leitores compram e baixam e-books. **Os diagramas e ADRs são modelagem; somente o motor CLI Cotuba está implementado.**

## Diagrama de Contexto

```mermaid
C4Context
    Person(autor, "Autor", "Escreve, publica e acompanha vendas")
    Person(leitor, "Leitor", "Consulta catálogo, compra e baixa livros")
    System(cotubify, "Cotubify", "Plataforma de autopublicação")
    System_Ext(git, "Provedor Git", "Versiona Markdown")
    System_Ext(pagamento, "Gateway de pagamento", "Pix e cartão")
    System_Ext(email, "Sistema de e-mail", "Recibos e avisos")
    Rel(autor, cotubify, "Publica livros e consulta saldo", "HTTPS")
    Rel(autor, git, "Escreve e versiona", "Git/HTTPS")
    Rel(leitor, cotubify, "Compra e baixa e-books", "HTTPS")
    Rel(cotubify, git, "Obtém fontes autorizadas", "HTTPS")
    Rel(cotubify, pagamento, "Solicita e consulta pagamentos", "HTTPS")
    Rel(cotubify, email, "Solicita notificações", "HTTPS")
```

## Diagrama de Container — evolução proposta

Na tarefa 07, a API principal chamava o gerador por HTTP síncrono. A tarefa 12 substitui essa relação por fila e adiciona cache. A tarefa 14 desacopla o processamento de pagamentos. As tecnologias abaixo são escolhas deste exercício, não serviços provisionados.

```mermaid
C4Container
    Person(autor, "Autor")
    Person(leitor, "Leitor")
    System_Ext(git, "Provedor Git")
    System_Ext(gateway, "Gateway de pagamento")
    System_Ext(email, "Serviço de e-mail")
    System_Boundary(sistema, "Cotubify") {
        Container(web, "Frontend", "Aplicação web", "Publicação e loja")
        Container(api, "API principal", "Java / Spring Boot", "Contas, catálogo, pedidos e outbox")
        ContainerDb(db, "Banco relacional", "PostgreSQL", "Pedidos, ledger, idempotência e outbox")
        ContainerDb(cache, "Cache de catálogo", "Redis", "Top 100 com TTL")
        ContainerQueue(fila, "Filas de trabalho", "RabbitMQ", "Geração e pagamentos em filas separadas")
        Container(gerador, "Worker de geração", "Java / motor Cotuba", "Processa uma revisão do livro")
        Container(worker, "Worker de pagamentos", "Java", "Timeout, circuit breaker e reconciliação")
        ContainerDb(storage, "Object Storage", "S3 compatível", "E-books e capas versionadas")
    }
    Rel(autor, web, "Publica e acompanha", "HTTPS")
    Rel(leitor, web, "Consulta e compra", "HTTPS")
    Rel(web, api, "Autenticação e operações", "HTTPS/JSON")
    Rel(api, db, "Transações de domínio e outbox", "SQL/TLS")
    Rel(api, cache, "Cache-aside", "RESP/TLS")
    Rel(api, fila, "Publica comandos via relay da outbox", "AMQP/TLS")
    Rel(gerador, fila, "Consome geração e confirma após persistir resultado", "AMQP/TLS")
    Rel(gerador, git, "Lê revisão autorizada", "Git/HTTPS")
    Rel(gerador, storage, "Publica artefatos", "HTTPS")
    Rel(worker, fila, "Consome pagamentos", "AMQP/TLS")
    Rel(worker, gateway, "Cobra com chave estável ou consulta estado", "HTTPS")
    Rel(worker, db, "Atualiza pagamento de modo transacional", "SQL/TLS")
    Rel(worker, email, "Solicita recibo após confirmação", "HTTPS")
    Rel(api, storage, "Emite download autorizado com prazo", "HTTPS")
    Rel(leitor, storage, "Baixa com autorização temporária", "HTTPS")
```

## Decisões

- [ADR 001 — Geração assíncrona](adr/001-geracao-assincrona.md).
- [ADR 002 — Cache de catálogo](adr/002-cache-catalogo.md).
- [ADR 003 — Resiliência e idempotência de pagamentos](adr/003-resiliencia-pagamentos.md).

## Fluxo de Resiliência de Pagamento

```mermaid
sequenceDiagram
    actor Cliente
    participant API
    participant Banco
    participant Fila
    participant Worker
    participant Gateway
    Cliente->>API: Comprar (pedido, chave K)
    API->>Banco: Transação: pedido + operação K + outbox (UNIQUE)
    Banco-->>API: Operação pendente
    API-->>Cliente: 202 e URL de status
    Cliente->>API: Clique repetido (mesmo pedido e K)
    API->>Banco: Localizar K e conferir payload
    Banco-->>API: Operação já existente
    API-->>Cliente: Mesmo status, sem nova cobrança
    Note over Banco,Fila: Relay publica outbox; duplicação é possível
    Banco->>Fila: Comando de pagamento (K)
    Fila->>Worker: Entrega (pode repetir)
    Worker->>Banco: Claim/lease atômico da operação K
    alt Já concluída ou com execução válida em andamento
        Banco-->>Worker: Não executar outra cobrança
    else Operação elegível
        Worker->>Gateway: Cobrar (chave estável K, timeout limitado)
        Gateway--xWorker: Timeout ou 503 (resultado pode ser desconhecido)
        Worker->>Banco: Registrar estado desconhecido / próxima tentativa
        Note over Worker,Gateway: Circuito aberto impede novas chamadas; não confirma pagamento
        Worker->>Fila: Retentativa adiada com backoff, jitter e limite
        Note over Worker,Gateway: Após recuperação: consultar ou repetir com a MESMA K se o contrato permitir
        Worker->>Gateway: Consultar operação K
        Gateway-->>Worker: Resultado confirmado
        Worker->>Banco: Transação: resultado + ledger + notificação única
    end
    Cliente->>API: Consultar status
    API->>Banco: Ler resultado autorizado do pedido
    Banco-->>API: Pago, pendente ou falha definitiva
    API-->>Cliente: Estado real da operação
```

Não há promessa de exactly-once de ponta a ponta. A garantia depende de restrições duráveis, transações locais, idempotência aceita pelo provedor e reconciliação de resultados ambíguos. Redis sozinho não substitui o registro durável de pagamentos.
