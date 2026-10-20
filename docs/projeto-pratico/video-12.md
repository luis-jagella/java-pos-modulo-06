# Projeto — Vídeo 12: Escalabilidade do Cotubify

> Tarefa 12. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas
Conceitos Explicados

System Design e Escalabilidade: Discussão em formato de simulação de entrevista técnica (fase 2) para avaliar a resiliência de uma arquitetura diante de picos de acesso repentinos (ex: Black Friday).

Processamento Síncrono vs. Assíncrono: Identificação de gargalos em chamadas HTTP síncronas bloqueantes (como a geração de PDFs/EPUBs) e proposta de migração para mensageria assíncrona.

Padrão de Mensageria (Competing Consumers): Uso de um Message Broker para desacoplar o produtor do consumidor, permitindo que os workers processem as mensagens em seu próprio ritmo.

AJAX / Status HTTP 202 (Accepted): Retorno imediato ao frontend indicando que a requisição foi aceita para processamento posterior, evitando travamentos de threads.

ADR (Architecture Decision Records): Documentação formal de decisões arquiteturais para registrar o contexto, opções consideradas, decisão tomada e consequências (tradeoffs), evitando o efeito "hype" tecnológico.

Cache de Catálogo (Cache-Aside Pattern): Estratégia para armazenar em memória (ex: Top 100 livros mais vendidos) dados de alto volume de leitura para aliviar o banco de dados relacional.

TTL (Time-to-Live): Tempo de expiração configurado no cache (ex: 5 minutos) para garantir frescor aceitável dos dados sem sobrecarregar a infraestrutura.

Refatorações

Introdução de um Message Broker (AWS SQS) entre a API principal e o serviço gerador de e-books.

Transformação do serviço gerador de e-books em um Worker assíncrono.

Introdução de um servidor de cache em memória (AWS ElastiCache / Redis) utilizando o padrão Cache-Aside para o catálogo principal.

Atualização dos diagramas de contêiner (C4 Model) para refletir a inclusão do broker de mensagens e do cache, documentando as relações via TCP.

Problemas e Correções Mencionado(s)

Problema: 50 autores gerando e-books simultaneamente (levando em média 20 segundos cada) esgotavam as threads da API principal e elevavam o uso de CPU/memória no gerador, causando falhas de Out of Memory e timeouts.

Correção: Desacoplamento via fila assíncrona, permitindo escalabilidade horizontal dos workers.

Problema: 10.000 leitores por segundo acessando a página inicial para consultar o catálogo geravam consultas complexas e pesadas no PostgreSQL, levando a CPU a 100% e esgotando o pool de conexões (Hikari), resultando em erros 500.

Correção: Implementação de cache em memória com TTL de 5 minutos, reduzindo drasticamente o acesso direto ao banco de dados.

Problema: Efeito cascata por dependência externa (ex: falhas ou lentidão na API de pagamento Stripe deixavam o sistema pendurado de forma síncrona).

Correção: Identificação da necessidade futura de tratar a resiliência em integrações com terceiros.

Limitações do que Depende da Tela

A visualização do diagrama de arquitetura original (C4 Model) e a representação visual dos fluxos de rede (como protocolos TCP vs. HTTP/AMQP no SQS) exigiram correções e marcações de interrogação devido à falta de confirmação visual exata na documentação durante a simulação da entrevista.

5 Perguntas com Respostas

Qual é o principal problema identificado na geração de e-books quando 50 autores publicam simultaneamente?

Resposta: O processo leva em média 20 segundos e consome muita CPU e memória, bloqueando as threads da API principal e gerando risco de Out of Memory no servidor gerador.

Por que o uso de um banco de dados relacional sem cache falharia com 10.000 requisições por segundo na página inicial?

Resposta: Porque a consulta para listar os 100 livros mais vendidos é complexa e pesada, levando a CPU do PostgreSQL a 100%, esgotando o pool de conexões e causando erros 500 (timeouts).

Qual padrão de cache foi adotado para mitigar a carga no banco de dados na listagem do catálogo?

Resposta: O padrão Cache-Aside, onde a API verifica primeiro o cache e, em caso de miss, consulta o banco de dados e atualiza o cache.

Qual foi o TTL (Time-to-Live) definido para o cache do catálogo de mais vendidos?

Resposta: 5 minutos, garantindo um bom equilíbrio entre performance e atualização dos dados.

Para que servem os ADRs (Architecture Decision Records) segundo a discussão da aula?

Resposta: Para documentar formalmente o contexto, os problemas mapeados, as opções consideradas, a decisão tomada e os tradeoffs (consequências positivas e negativas), evitando que o time adote tecnologias apenas por "hype" e preservando o histórico de engenharia.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
