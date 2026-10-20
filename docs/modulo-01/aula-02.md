# Aula 02 — Princípios Básicos de System Design

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Aqui estão as notas de estudo completas e detalhadas da aula de System Design (Aula 2), ministrada pelo professor Otávio Santana. O material está estruturado na ordem dos tópicos abordados, destacando conceitos, definições históricas, decisões arquiteturais, limitações e trade-offs.

Identificação do Conteúdo

Tema Principal: System Design (Conceitos fundamentais, camadas físicas e lógicas, componentes, frontend vs. backend, escalabilidade, latência e throughput).

Professor: Otávio Santana

Contexto: Segunda aula do módulo focado em System Design, fazendo a ponte entre os fundamentos de computação clássicos e a arquitetura de software moderna.

### 1. Introdução e Fundamentos de Computação

A importância dos fundamentos: Para avançar em System Design e arquitetura de software, é vital revisar os fundamentos de computação clássicos (retornando a referências históricas como Turing, Peter Norton, entre outros).

Evolução do perfil profissional: Na faculdade, muitas vezes questiona-se a utilidade prática de certos conceitos teóricos. À medida que o profissional evolui para níveis sênior e de arquitetura, torna-se indispensável revisar esses conceitos para tomar decisões técnicas assertivas.

### 2. Camadas Físicas e Lógicas

Um dos maiores desafios do System Design é lidar com a complexidade do negócio através da divisão em camadas. Com a evolução da computação, o número de camadas físicas e lógicas aumentou drasticamente para atender a diferentes objetivos de escalabilidade e manutenção.

A. Camadas Físicas (Infraestrutura e Hardware)

1 Camada (Monolito Físico): Antigamente, a aplicação, o banco de dados e todos os recursos rodavam em uma única máquina física. Caso a máquina caísse, toda a empresa saía do ar.

3 Camadas Físicas: Separação física entre a aplicação e o banco de dados. Se a aplicação sofresse um crash, o banco de dados permanecia salvo.

Múltiplas Camadas / Microsserviços: Aplicação fragmentada em vários serviços, comunicação com parceiros (third-party) e gateways distribuídos.

Trade-offs das Camadas Físicas:

Vantagens: Maior facilidade para escalar gargalos específicos (adicionar mais servidores de banco ou de aplicação de forma isolada); tolerância a falhas em arquiteturas distribuídas (ex: se um nó cai, outros suportam a carga).

Desafios: Aumento expressivo na complexidade de orquestração, necessidade de gerenciar consistência de dados (ex: Two-Phase Commit, padrões de consistência eventual) e riscos de falhas de rede interna caso estratégias de Circuit Breaker ou Fallback não sejam implementadas.

B. Camadas Lógicas (Organização do Código)

Monolito Lógico Clássico: Todo o código concentrado em um único script ou método principal (ex: o clássico public static void main ou interfaces gráficas iniciais em Swing e JavaFX).

Evolução para o Front-End e Back-End:

Início dos anos 2000 (JSP, Servlets, requisições onde o cabeçalho abria a conexão e o rodapé fechava).

Popularização de linguagens de script no servidor (PHP) e as primeiras tendências de mover lógica para o cliente.

A Era Ágil e o DDD (Domain-Driven Design): Com a disseminação dos métodos ágeis (Manifesto Ágil de 2001), a complexidade exigiu a quebra em camadas lógicas bem definidas (Camada de Negócio, Camada de Modelo, Objetos de Acesso a Dados - DAO).

Arquitetura Hexagonal e Clean Architecture: Quebra ainda maior para lidar com múltiplos requisitos de negócio, separando classes de caso de uso (Use Cases) para facilitar testes, manutenção e independência de frameworks.

Trade-offs das Camadas Lógicas:

Vantagens: Facilidade de testabilidade unitária, isolamento de regras de negócio e melhor alinhamento com equipes multidisciplinares.

Desafios: O perigo da "Arquitetura Cebola" ou excesso de camadas (over-engineering), onde criar camadas excessivas apenas para "seguir padrões" gera redundância de código (boilerplate), latência de serialização/desserialização de dados (ex: conversão de JSON para entidades de domínio, uso de ORMs como JPA/Hibernate) e desalinhamento de equipes em organizações flat.

### 3. O Conceito de Componentes no System Design

Definição: Um componente é uma unidade autônoma e modular dentro do System Design. Ele pode representar um elemento físico, lógico, uma interface de usuário, um gateway, uma camada de persistência ou um serviço de infraestrutura.

Características de um bom componente:

Encapsulamento claro da lógica e do comportamento.

Contratos bem definidos através de interfaces.

Reutilizabilidade, testabilidade e capacidade de ser implantado (deployable) de forma independente.

Tipos comuns de componentes: Interfaces gráficas (UI), serviços de domínio, infraestrutura de rede, mensageria, persistência de dados e componentes operacionais/segurança.

### 4. Frontend vs. Backend: Decisões e Abordagens

Frontend:

Focado na experiência do usuário, visualização e interações.

Abordagens: Do lado do cliente (Client-Side Rendering com frameworks como React, Angular, ou aplicações mobile iOS/Android) versus renderização no servidor (Server-Side Rendering com JSF, JSP, Spring MVC, Razor, Next.js).

Trade-off: Processamento no cliente alivia o servidor e acelera a navegação interativa (via AJAX), mas impacta o SEO e o consumo de recursos do dispositivo final.

Backend:

Concentra a inteligência do sistema, regras de negócio complexas, orquestração e acesso a dados.

Representa geralmente o custo computacional mais elevado e o ponto central de decisões arquiteturais.

### 5. Escalabilidade, Latência e Throughput

A escalabilidade no System Design está diretamente ligada à capacidade de crescer de forma sustentável diante do aumento de usuários, dados, requisições ou transações.

A. Tipos de Escalabilidade

Escalabilidade Vertical:

Consiste em aumentar o poder de hardware de uma única máquina (ex: subir o tamanho da instância na nuvem).

Vantagens: Simplicidade de manutenção e ausência de complexidade de distribuição de nós.

Desafios: Limites físicos do provedor de nuvem (o "teto" do hardware) e alto impacto em caso de indisponibilidade (SPOF).

Escalabilidade Horizontal:

Consiste em adicionar mais máquinas/nós ao ecossistema.

Vantagens: Maior tolerância a falhas e possibilidade de ampliar a capacidade adicionando nós, ainda sujeita a limites.

Desafios: Complexidade severa de coordenação, balanceamento de carga (load balancing) e replicação de dados.

B. Métricas de Desempenho

Latência: O tempo decorrido entre uma requisição e a respectiva resposta (geralmente medida em milissegundos ou segundos). O objetivo é sempre reduzi-la ao máximo.

Throughput: A capacidade de processamento do sistema em um determinado intervalo (ex: requisições por segundo, transações por minuto, jobs por hora). O objetivo é maximizá-lo.

### 6. Perguntas de Revisão

Qual é a principal diferença prática entre camadas físicas e camadas lógicas em System Design?

Resposta: As camadas físicas referem-se à distribuição da infraestrutura e do hardware (como servidores de aplicação e de banco de dados separados em máquinas distintas), focando em escalabilidade de infraestrutura e tolerância a falhas. Já as camadas lógicas dizem respeito à organização e separação do código-fonte (como separar interface, regras de negócio e persistência), focando na manutenibilidade, testabilidade e divisão de responsabilidades.

Quais são os principais trade-offs de se adotar uma arquitetura baseada em múltiplos microsserviços (camadas físicas distribuídas) em vez de um monolito?

Resposta: Vantagens: Facilidade para escalar serviços de forma independente, maior resiliência a falhas pontuais e liberdade tecnológica por equipe. Desafios: Aumento drástico na complexidade de orquestração, necessidade de lidar com consistência eventual, latência de rede e custos operacionais elevados na nuvem.

Como a escolha entre processamento no Frontend (Client-Side) e no Server-Side impacta o desempenho de um sistema?

Resposta: O processamento no Client-Side transfere o esforço de renderização e lógica visual para o navegador ou dispositivo do usuário (gerando interfaces mais dinâmicas após o carregamento inicial), mas pode exigir mais do dispositivo cliente. O Server-Side Rendering processa a resposta no servidor, melhorando a indexação para motores de busca (SEO) e o carregamento inicial, porém sobrecarrega o poder de processamento e a rede do servidor.

Explique a diferença entre Escalabilidade Vertical e Escalabilidade Horizontal.

Resposta: A escalabilidade vertical eleva a capacidade computacional adicionando mais recursos (CPU, memória) a uma única máquina existente, sendo simples de gerenciar, mas limitada pelo teto físico do hardware. A escalabilidade horizontal adiciona novas máquinas ao cluster, oferecendo alta disponibilidade e capacidade de expansão sujeita a limites de coordenação e infraestrutura, em troca de alta complexidade de sincronização e gerenciamento de estado.

O que medem as métricas de Latência e Throughput no contexto de performance de sistemas?

Resposta: A latência mede o tempo de atraso ou resposta de uma transação (quanto menor, melhor). O throughput mede a volumetria de dados ou requisições que o sistema consegue processar com sucesso por unidade de tempo, como requisições por segundo (quanto maior, melhor).

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
