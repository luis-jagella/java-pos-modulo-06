# ADR 002 — Cache-aside para o catálogo

Status: aceito para o exercício; não implantado.

## Contexto

O catálogo Top 100 é muito lido e tolera alguma defasagem. Executar a mesma consulta agregada em cada acesso desperdiça capacidade do banco transacional. A quantidade de acessos da aula é uma premissa de cenário, não medição do código CLI.

## Opções consideradas

- Otimizar índices e consultas: necessário, mas não elimina leituras repetidas.
- Read replicas: ampliam leitura, com custo e atraso de replicação.
- Cache compartilhado: reduz carga para conteúdo repetido, mas exige política de validade e recuperação.

## Decisão

Usar Redis em cache-aside no desenho. A API busca a chave do catálogo; em miss, consulta o banco e popula com TTL inicial de cinco minutos, a validar com o negócio. Chaves incluem versão da consulta, idioma e filtros relevantes. Não misturar dados privados ou de autorização no cache público.

Usar single-flight/lock com prazo para evitar cache stampede, TTL com jitter e métricas de hit ratio, latência e idade dos dados. Uma falha no Redis tem fallback limitado para o banco, com proteção de concorrência; não liberar uma avalanche irrestrita de consultas. Preço final, saldo e autorização de compra são verificados no sistema de registro, não no cache.

## Consequências

Menor latência e carga, ao custo de dados temporariamente defasados e maior operação. TTL não garante atualização instantânea. Definir invalidação antecipada para alterações críticas e monitorar saturação do fallback. Não foram provisionados Redis nem o catálogo web neste repositório.
