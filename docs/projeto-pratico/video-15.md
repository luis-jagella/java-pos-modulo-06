# Projeto — Vídeo 15: Resiliência de pagamentos

> Tarefa 14. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas - Tarefa 14: Resiliência de Pagamentos (System Design)
Conceitos Explicados

Cenário de Pico Extremo (Flash Sale / Thunder Herd): Situação de estresse em produção (como uma promoção relâmpago de 70% de desconto conduzida por um autor celebridade, Estevam Ray) gerando um pico massivo de 100.000 cliques simultâneos no botão de compra.

Falha em Cascata e Esgotamento de Threads: O impacto causado por um serviço externo de pagamento lento (Stripe demorando 40 segundos para responder e retornando erro 503 Service Temporarily Unavailable), prendendo as threads síncronas da API principal até derrubar o sistema inteiro.

Tempestade de Retentativas (Retry Storm): Comportamento de usuários ansiosos que clicam repetidas vezes na tela de carregamento, gerando um tráfego abusivo interno comparável a um ataque DDoS.

Desacoplamento Assíncrono: Uso de um message broker (como o Amazon SQS) para enfileirar requisições de pagamento, permitindo que a API principal responda imediatamente ao usuário (status 202 Accepted) sem bloquear suas threads.

Circuit Breaker: Padrão de resiliência (com Resilience4j) implementado no worker de pagamento para detectar falhas/lentidão e abrir o circuito (fail-fast), evitando chamadas contínuas a um gateway morto ou sobrecarregado.

Idempotência (Idempotent Consumer): Padrão arquitetural em que o consumidor de mensagens garante que uma mesma operação (como uma cobrança) não seja executada múltiplas vezes, utilizando chaves de idempotência validadas em um data store (como o Redis).

ADR (Architecture Decision Record): Documento formal para registrar decisões arquiteturais, contexto, opções consideradas, escolhas adotadas e consequências perante o time.

Refatorações

Redesenho da arquitetura de contêineres e do fluxo de pagamentos: introdução de um worker dedicado ao processamento assíncrono de pagamentos, integração com broker de mensagens, adoção de Circuit Breaker no consumo síncrono externo e validação de chaves de idempotência via Redis.

Problemas e Correções Realmente Mencionados

Problema: Lentidão de 40 segundos e erros 503 retornados pelo gateway de pagamento (Stripe), travando as threads da API e derrubando a aplicação. Correção proposta: Desacoplamento assíncrono com mensageria e aplicação de Circuit Breaker com estratégia fail-fast no worker.

Problema: Múltiplas cobranças duplicadas no cartão de crédito dos clientes devido aos cliques repetidos durante a lentidão. Correção proposta: Utilização de uma chave de idempotência e checagem prévia em um data store (Redis) antes de efetivar o pagamento.

Limitações do que Depende da Tela

O instrutor utiliza um editor visual baseado em Mermaid na tela para desenhar e modificar os diagramas de contêiner e de sequência, apontando visualmente para os componentes (Frontend, API principal, Redis, SQS, Worker de Pagamento e Gateway Externo).

5 Perguntas com Respostas

O que caracteriza um cenário de flash sale ou thunder herd em uma aplicação corporativa?

Resposta: Um pico repentino e avassalador de acessos e cliques simultâneos (como 100.000 usuários tentando comprar ao mesmo tempo).

Por que chamadas síncronas diretas a um gateway de pagamento instável podem causar o colapso da API principal?

Resposta: Porque a lentidão extrema do serviço externo (ex: 40 segundos de espera) esgota e trava todas as threads disponíveis na API principal.

Qual é o principal objetivo do padrão Circuit Breaker na comunicação com APIs externas?

Resposta: Identificar falhas ou lentidões e abrir o circuito rapidamente (fail-fast), evitando sobrecarregar ainda mais o serviço parceiro e permitindo uma recuperação controlada.

Como o sistema evita cobrar o cliente em dobro caso ele clique várias vezes no botão de compra durante uma falha de resposta?

Resposta: Através do uso de uma chave de idempotência enviada nas requisições e validada em um armazenamento centralizado (como o Redis) antes de processar o pagamento.

Por que adotar uma arquitetura baseada em mensageria (broker) para o fluxo de pagamentos sob alta carga?

Resposta: Para desacoplar a API principal do gateway de pagamento, permitindo responder instantaneamente ao usuário (ex: status 202) sem bloquear recursos computacionais preciosos.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
