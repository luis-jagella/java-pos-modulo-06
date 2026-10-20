# Aula 05 — Comunicação entre Componentes (System Design)

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado: Comunicação entre Componentes

Esta aula foca na integração e comunicação entre microsserviços e componentes de software sob a ótica de System Design, examinando padrões síncronos e assíncronos, resiliência, protocolos e ferramentas de mercado.

### 1. Comunicação Síncrona vs. Assíncrona

Comunicação Síncrona: O chamador aguarda a resposta imediata do receptor (bloqueante). O protocolo predominante é o HTTP/REST.

Comunicação Assíncrona: O emissor envia a mensagem para um canal intermediário (broker) e prossegue com sua execução sem esperar a conclusão do processamento, garantindo maior desacoplamento e resiliência por meio de mensageria e arquiteturas orientadas a eventos (Event-Driven Architecture).

### 2. Padrões e Protocolos Síncronos

HTTP / REST: Amplamente utilizado em integrações externas e B2B. Baseia-se no modelo de maturidade de Richardson, uso correto de verbos HTTP (GET, POST, PUT, DELETE), especificação de contratos via OpenAPI/Swagger e o formato JSON como padrão dominante.

gRPC: Desenvolvido pelo Google sobre HTTP/2, utiliza serialização binária com Protocol Buffers (Protobuf), oferecendo altíssima performance e comunicação multiplexada, ideal para microsserviços internos de baixa latência.

### 3. Padrões e Ferramentas Assíncronas

Utilização de brokers de mensageria robustos como Apache Kafka e RabbitMQ.

Permite o desacoplamento de fluxos complexos, como no processamento de pedidos de e-commerce onde o serviço de pagamento notifica de forma assíncrona os módulos de estoque e faturamento.

### 4. Resiliência, Tolerância a Falhas e Observabilidade

Circuit Breaker: Padrão fundamental com estados fechado, aberto e meio-aberto (half-open) para prevenir falhas em cascata quando um serviço dependente fica indisponível.

Retry com Exponential Backoff e Jitter: Estratégias inteligentes de nova tentativa para evitar sobrecarga em sistemas instáveis.

Health Checks: Monitoramento de saúde de containers através de sondas de vivência e prontidão (Liveness e Readiness probes).

Observabilidade: Stack integrada envolvendo métricas (Prometheus, Grafana), logs estruturados (Elasticsearch, Logstash, Kibana - ELK) e rastreamento distribuído (Distributed Tracing com OpenTelemetry e Jaeger).

Limitações Mencionadas

A complexidade na gestão de contratos e versionamento de APIs em ambientes altamente distribuídos.

O risco de degradação de performance em cadeias longas de chamadas síncronas (latency stacking).

Perguntas de Revisão (Comunicação entre Componentes)

Qual é a principal vantagem da comunicação assíncrona baseada em mensageria em relação às chamadas síncronas diretas?

Resposta: O desacoplamento temporal e espacial entre os serviços, aumentando a resiliência e evitando que a indisponibilidade de um consumidor derrube o sistema produtor.

Quais são as principais características técnicas que diferenciam o gRPC do REST tradicional?

Resposta: O gRPC utiliza HTTP/2, multiplexação de conexões e serialização binária via Protocol Buffers, resultando em menor latência e maior eficiência se comparado ao JSON sobre HTTP/1.1.

Qual é a função do padrão Circuit Breaker em uma arquitetura distribuída?

Resposta: Proteger o sistema contra falhas em cascata, interrompendo rapidamente chamadas a serviços externos que estejam indisponíveis e permitindo sua recuperação gradual.

O que são Liveness e Readiness probes em ambientes de containers?

Resposta: São mecanismos de verificação de saúde utilizados para checar se a aplicação está viva (reiniciando-a se necessário) e se está pronta para receber tráfego externo.

Quais pilares compõem uma estratégia completa de observabilidade em microsserviços?

Resposta: Métricas, logs estruturados e rastreamento distribuído (distributed tracing).

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
