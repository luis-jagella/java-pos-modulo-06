# ADR 001 — Desacoplar geração de e-books

Status: aceito para o exercício; não implantado.

## Contexto

A geração usa CPU e memória por tempo muito maior que uma consulta de catálogo. Mantê-la no caminho síncrono da API aumenta a duração das requisições e acopla disponibilidade da loja à do gerador. O cenário da aula usa vários autores publicando simultaneamente, não um benchmark medido deste repositório.

## Opções consideradas

1. HTTP síncrono: implantação simples, mas espera longa e limites difíceis de controlar.
2. HTTP assíncrono com fila interna em memória: melhora a espera, mas perde trabalho após falhas e exige controle de capacidade.
3. Broker durável com workers: permite fila, retries e escala independente, com custo operacional maior.

## Decisão

Usar RabbitMQ no desenho, com comandos contendo ID de trabalho, livro e revisão imutável do Git. A API grava pedido de geração e outbox na mesma transação e responde `202 Accepted`, com URL de acompanhamento. Um relay publica a outbox e workers consomem com concorrência/prefetch limitados. O resultado vai para object storage com uma chave versionada.

O worker só confirma a mensagem depois de persistir o resultado. Redelivery é esperado; a chave livro/revisão/formato impede publicar artefatos conflitantes. Aplicar timeout, retries limitados com backoff e DLQ com diagnóstico. Monitorar idade da fila, tempo de geração, erros e consumo de memória; não apenas quantidade de pods.

## Consequências

Desacoplamento temporal e escala independente, mas consistência eventual, mais infraestrutura e necessidade de idempotência e recuperação de trabalho. Fila não cria capacidade infinita: aplicar quotas por autor e contrapressão. O motor CLI continua local; broker e API não foram implementados nesta entrega.
