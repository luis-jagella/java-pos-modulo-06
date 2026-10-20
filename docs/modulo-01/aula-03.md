# Aula 03 — System Design e Documentação

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado da Aula: Documentação, C4 Model, UML e Ferramentas de Arquitetura


### 1. Introdução e Contextualização (Recapitulação)

A aula inicia fazendo uma ponte com o conteúdo anterior, que abordou System Design, conceitos fundamentais, definição de componentes e unidades, além dos tipos de camadas (físicas e lógicas).

Camadas Físicas: Promovem maior separação e isolamento de falhas, mas aumentam a complexidade de orquestração.

Camadas Lógicas: Oferecem maior grau de abstração e separação de responsabilidades, porém podem impactar a performance e gerar delay layers se mal dimensionadas.

Nesta terceira aula, o foco central é a documentação, essencial para garantir que o planejamento arquitetural seja escalável, compreendido e compartilhado por todo o time de forma alinhada.

### 2. Por que Documentar?

Muitos desenvolvedores demonstram resistência à criação de documentações tradicionais, mas o professor destaca que a documentação resolve problemas críticos no ciclo de vida do software:

Escalabilidade Humana e Alinhamento: Garante que tanto a equipe técnica quanto os stakeholders tenham a mesma visão do produto.

Redução de Reuniões: Desenvolvedores costumam desgostar de reuniões excessivas; uma documentação clara reduz drasticamente a necessidade de alinhamentos verbais repetitivos.

Onboarding e Offboarding: Facilita a entrada de novos membros no time (onboarding) e mitiga perdas de conhecimento quando colaboradores saem (offboarding).

Rastreabilidade e Refatoração: Permite entender o histórico de decisões, facilitando refatorações futuras e garantindo a continuidade do negócio.

### 3. Abordagem: Documentação como Código (Docs-as-Code / DocOps)

O professor recomenda fortemente a adoção de Documentação como Código (Docs-as-Code ou DocOps), integrando a documentação diretamente no controle de versão (Git), em vez de utilizar ferramentas isoladas como o Draw.io em unidades de rede separadas.

Vantagens do Docs-as-Code:

Versionamento: A documentação evolui junto com o software e com as mudanças de negócios e arquitetura.

Colaboração via Pull Requests: Alterações na arquitetura passam por code review, permitindo que o time questione, aprenda e identifique corner cases antes de consolidar a mudança.

Fonte Única da Verdade: Evita a dispersão de informações entre ferramentas desconectadas (como Miro, Jira e arquivos soltos).

### 4. Tipos de Documentação e Padrões Arquiteturais

A aula explora as principais ferramentas e metodologias de documentação aplicadas a System Design e Arquitetura:

A. C4 Model

Criado por Simon Brown, o C4 Model é comparado à analogia do Google Maps, permitindo dar zoom in e zoom out na arquitetura. Possui quatro níveis principais:

Contexto (Level 1): Visão de alto nível mostrando o sistema, os usuários e sistemas externos. Possui apenas um mapa geral.

Contêiner (Level 2): Aplicações, bancos de dados, serviços executáveis (ex: aplicação Java, gateways externos como Stripe ou PayPal). Possui apenas um mapa geral.

Componentes (Level 3): Divisões internas de um contêiner (ex: componentes de pagamento, módulos de autenticação). Pode haver múltiplos diagramas.

Código (Level 4): Diagramas de classes detalhados (opcional e raramente utilizado na prática diária).

Trade-off / Recomendação: O criador recomenda a abordagem Architecture as Code (usando ferramentas como Structurizr), evitando o uso de editores visuais manuais que se tornam obsoletos rapidamente.

B. UML (Unified Modeling Language)

A UML é clássica, amplamente ensinada em universidades, mas possui diferentes níveis de adoção no mercado:

Diagramas de Sequência: Altamente utilizados para definir novos fluxos e integrações.

Diagramas de Componentes e Atividades: Úteis para modelagem de processos e arquitetura.

Diagramas de Casos de Uso: Utilizados moderadamente dependendo da organização.

Diagramas de Classes: Pouco utilizados no dia a dia da maioria das empresas ocidentais, sendo mais comuns em culturas específicas de terceirização (outsourcing) de grande escala (ex: empresas de TI na Índia).

C. Architecture Decision Records (ADRs)

ADRs registram decisões arquiteturais relevantes; o percentual de adoção mencionado na análise automática não foi verificado:

Estrutura básica: Contexto, Decisão e Consequências.

Evolução e Depreciação: Permitem registrar quando uma decisão anterior (ex: uso de Java 21 ou arquitetura monolítica) deixa de fazer sentido e é substituída por uma nova diretriz (ex: migração para Java 25 ou microsserviços).

Rastreabilidade: O time compreende o histórico e os motivos por trás de escolhas técnicas complexas.

### 5. Ferramentas de Documentação: Markdown vs. AsciiDoc

O professor compara as duas principais sintaxes de marcação utilizadas para documentação técnica:

Markdown (.md):

Vantagens: Simples, amigável, amplamente suportado por IDEs, GitHub, servidores de documentação (como Hugo).

Uso: Ideal para equipes que estão começando com Docs-as-Code ou para documentações diárias e readmes.

AsciiDoc (.adoc):

Vantagens: Mais robusto, extensível e poderoso. Suporta blocos avançados de notas, avisos (warnings), sumários automáticos avançados e geração de livros ou especificações técnicas complexas (ex: especificações oficiais do Jakarta EE e Java utilizam AsciiDoc convertidas para HTML/PDF via plugins do Maven).

Ferramentas associadas: Antora, Asciidoctor.

### 6. Ferramentas de Diagramação por Código

Para evitar as limitações de ferramentas visuais tradicionais, a aula destaca opções modernas baseadas em texto/código:

Mermaid:

Destacado pelo professor como uma excelente escolha devido à facilidade de uso, suporte nativo em Markdown, GitHub Pages, e capacidade de gerar diagramas de sequência, fluxo, classes e estados de forma rápida.

PlantUML:

Muito tradicional e poderoso para diagramas UML baseados em texto.

Structurizr / C4-PlantUML:

Focado na filosofia de Architecture as Code para o C4 Model.

### 7. Exemplos Práticos Apresentados na Aula

O professor utilizou exemplos conceituais simples para ilustrar a aplicação prática das ferramentas:

Fluxo de Requisição REST (Java): Representação em diagramas de sequência mostrando a interação entre Cliente, Controller, Service, Repository e Banco de Dados.

Ciclo de Vida de um Cartão de Crédito: Demonstração de máquina de estados (cartão criado, virtual/físico, suspenso, cancelado ou roubado).

Sistema Bancário Integrado: Exemplo de C4 Model contextualizando um Cliente, um Sistema Bancário Interno e integrações com adquirentes de pagamento externos (ex: Stripe, PayPal), evidenciando que o banco faz integrações (system integration) em vez de possuir todas as bandeiras nativamente.

### 8. Perguntas de Revisão

Q1: Por que o professor recomenda a adoção da "Documentação como Código" (Docs-as-Code) em detrimento de ferramentas visuais isoladas como o Draw.io?

Resposta: Porque o Docs-as-Code permite versionamento no Git, revisões por meio de Pull Requests (code review de arquitetura), garante uma única fonte da verdade e evita a obsolescência rápida e a dispersão dos artefatos em unidades de rede separadas.

Q2: Quais são os quatro níveis do C4 Model e qual analogia o criador Simon Brown utiliza para explicá-los?

Resposta: Os quatro níveis são: 1. Contexto, 2. Contêiner, 3. Componentes e 4. Código. A analogia utilizada é a do Google Maps, onde é possível navegar através de diferentes níveis de zoom para entender o sistema em várias granularidades.

Q3: O que significa a sigla ADR e qual é a sua principal utilidade em projetos de software?

Resposta: ADR significa Architecture Decision Record (Registro de Decisão Arquitetural). Sua principal utilidade é documentar o contexto, a decisão tomada e as consequências de escolhas arquiteturais importantes, permitindo rastrear o histórico evolutivo e a depreciação de tecnologias ao longo do tempo.

Q4: Qual é a principal diferença prática entre as sintaxes Markdown e AsciiDoc destacada na aula?

Resposta: O Markdown é mais simples, leve e amplamente utilizado para documentações cotidianas e arquivos README. O AsciiDoc é mais robusto, complexo e poderoso, sendo ideal para documentações extensas, livros e especificações técnicas complexas (como padrões Java/Jakarta) com suporte avançado a sumários, blocos de notas e avisos.

Q5: Entre os diagramas UML citados, qual deles é apontado como o mais frequentemente utilizado na prática de desenvolvimento moderno?

Resposta: O Diagrama de Sequência, amplamente utilizado para modelar novos fluxos de integração e chamadas entre componentes, APIs e serviços.

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
