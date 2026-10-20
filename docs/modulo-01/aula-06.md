# Aula 06 — Exemplos e Visão de Geral de Cases de System Design

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado: Casos Práticos de System Design

Esta aula consolida a parte teórica de System Design aplicando conceitos a cenários do mundo real, com foco no planejamento de arquiteturas para e-commerce, catálogos multicanal (broadcast) e sistemas de alta concorrência.

### 1. Metodologia de Resolução de Problemas

Para desenhar sistemas eficientes, adota-se um fluxo estruturado inspirado na literatura clássica de System Design:

Passo 1: Compreensão profunda do problema de negócio, requisitos funcionais e não funcionais.

Passo 2: Desenho do contexto de alto nível utilizando o padrão C4 Model (níveis C1 e C2) para mapear atores, sistemas externos e componentes internos.

Passo 3: Identificação de dependências críticas (pagamentos, gateways geográficos, notificações).

Passo 4: Avaliação rigorosa de trade-offs de armazenamento, cache e particionamento.

### 2. Cenários Práticos Analisados

E-commerce Multicanal / Broadcast: Um agregador que consulta múltiplos fornecedores em tempo real para exibir o menor preço de um produto (ex: iPhone 16) para o usuário. O projeto inicial foca no contexto interno de integração antes de abrir o leque para clientes finais, mitigando gargalos de concorrência.

Sistemas de Mobilidade e Reservas (Estilo Uber): Integração de dados geoespaciais, comunicação assíncrona para despacho de corridas, consistência eventual e alta disponibilidade em larga escala.

Redes Sociais e Feeds de Notícias: Uso de bancos de dados orientados a grafos ou estruturas NoSQL otimizadas para leitura rápida de relacionamentos (Leader-Follower e particionamento por chaves eficientes).

### 3. Decisões Arquiteturais e Estratégias

Priorização do Negócio: A escolha tecnológica deve sempre servir ao domínio do problema e não a tendências passageiras de mercado.

Limitações de Entrevistas vs. Mundo Real: Entrevistas de arquitetura focam em cortes conceituais limpos, enquanto o ambiente de produção real lida com custos de infraestrutura em nuvem, latências de rede imprevisíveis e falhas de hardware sistêmicas.

Limitações Mencionadas

A complexidade na estimativa de capacidade de armazenamento e tráfego (back-of-the-envelope estimations) em cenários de crescimento exponencial.

A dificuldade de manter consistência de dados em topologias altamente distribuídas sem impactar a latência percebida pelo usuário.

Perguntas de Revisão (Casos Práticos)

Qual é o propósito de utilizar o C4 Model no projeto inicial de um sistema distribuído?

Resposta: Fornecer uma estrutura hierárquica e clara de diagramas para documentar a arquitetura de software, desde o contexto de alto nível até os componentes detalhados.

Em um sistema agregador de preços (broadcast), por que muitas vezes recomenda-se iniciar a integração com fluxos internos antes de disponibilizar o acesso público?

Resposta: Para validar a estabilidade dos contratos, o fluxo de dados e mitigar gargalos de performance e concorrência em ambiente controlados.

Qual é a importância de separar os requisitos funcionais dos não funcionais no início do planejamento de arquitetura?

Resposta: Os requisitos funcionais definem o que o sistema deve fazer, enquanto os não funcionais determinam restrições cruciais de desempenho, escalabilidade, segurança e disponibilidade.

Como o particionamento de dados (sharding) impacta a escalabilidade em sistemas de redes sociais com alto volume de gravações?

Resposta: Permite distribuir a carga de escrita entre múltiplos servidores horizontais, embora aumente a complexidade de consultas cruzadas e rotinas de manutenção.

Qual é a principal lição ao ponderar escolhas tecnológicas em projetos de grande escala?

Resposta: Priorizar as reais necessidades do domínio de negócio e os trade-offs arquiteturais em detrimento do uso de tecnologias modismos.

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
