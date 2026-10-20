# Aula 11 — Visão Geral sobre DDD

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado da Aula: Domain-Driven Design (DDD) - Estratégia e Tática
### 1. Introdução e Mitos sobre o DDD

A aula aborda a última etapa do módulo focada em DDD (Domain-Driven Design), especificamente no escopo de Domain-Driven Architecture. O instrutor relata sua experiência internacional ministrando palestras em países como Suíça, Bulgária e Holanda, onde faz uma pergunta clássica às plateias: "Quem já ouviu falar de DDD?" Praticamente 100% do público levanta a mão. No entanto, ao perguntar "Quem já aplicou DDD com sucesso ao menos uma vez?", quase ninguém levanta.

O que o DDD NÃO é: O DDD não é um framework de tecnologia, não está restrito a microsserviços, não se resume a colocar anotações de entidades no código (@Entity), e não é sinônimo automático de complexidade excessiva.

Erros comuns: A crença equivocada de que DDD se trata apenas de escrever código limpo ou estruturar camadas técnicas.

### 2. O Conceito Real do DDD

O DDD é uma metodologia e um conjunto de princípios cujo objetivo principal é trazer o conhecimento de negócio (domínio) para dentro do software.

Transferência de Conhecimento (Transfer Knowledge): O foco é mapear o modelo mental dos especialistas de negócio (Domain Experts) — sejam médicos, economistas ou profissionais de e-commerce — para dentro do código-fonte.

Literatura recomendada: O instrutor destaca que, embora o livro clássico de Eric Evans seja a referência acadêmica, o livro de Vlad Khononov (sobre acoplamento e DDD) é apontado como a leitura mais prática e acessível para dominar o assunto.

### 3. Visão Estratégica vs. Visão Tática

O DDD divide-se em duas grandes frentes, sendo a vertente estratégica a mais negligenciada e a mais importante:

Design Estratégico (Aproximadamente 80% do valor): Focado na modelagem do negócio, definição de subdomínios, linguagem ubíqua e contextos delimitados. Errar nesta etapa compromete todo o projeto.

Design Tático: Focado nos detalhes de implementação técnica (entidades, objetos de valor, serviços, repositórios).

### 4. Subdomínios e Contextos Delimitados

Para organizar sistemas complexos, o DDD utiliza a segmentação de subdomínios:

Core (Essencial): O grande diferencial competitivo da organização. É onde o maior esforço de desenvolvimento e complexidade deve ser investido.

Generic (Genérico): Processos comuns a várias empresas (ex: sistemas de pagamento, autenticação). Não trazem diferencial competitivo único.

Supporting (Suporte): Auxiliam o negócio, mas não são o foco principal.

Linguagem Ubíqua (Ubiquitous Language): Desenvolvimento de um vocabulário comum e rigoroso compartilhado entre desenvolvedores e especialistas de negócio, eliminando ambiguidades (ex: a clássica piada de que "um pato perdeu a pata", onde o contexto define se o animal ficou manco ou viúvo).

Context Mapping: Mapeamento de fronteiras e relações entre diferentes Bounded Contexts (Contextos Delimitados), definindo como sistemas distintos se comunicam (via APIs, integrações, shared kernel, etc.).

Domain Storytelling: Técnica de contar histórias de negócio para extrair regras e modelar fluxos, destacada como uma das abordagens mais maduras e naturais da humanidade.

### 5. Design Tático e Ferramentas de Validação

Após estruturar a estratégia, passa-se para a implementação tática utilizando blocos de construção reconhecíveis:

Entidades (Entities) e Objetos de Valor (Value Objects): Distinção entre objetos com identidade própria e objetos definidos unicamente pelos seus atributos.

Serviços, Repositórios, Agregados (Aggregates) e Fábricas (Factories): Organização de regras de negócio complexas.

Ferramentas de Apoio Arquitetural:

JMolecules: Um framework voltado para criar evidências arquiteturais no código através de anotações que refletem conceitos de DDD e arquitetura em camadas.

ArchUnit: Utilizado em conjunto para testes automatizados de arquitetura, verificando as regras de dependência entre pacotes e camadas configuradas nos testes.

### 6. Limitações e Alinhamento Prático

O instrutor aponta que tentar aplicar o design tático (código) sem uma sólida fundação estratégica leva inevitavelmente ao fracasso do software.

A aula serve como uma introdução conceitual profunda, preparando o terreno para os exercícios práticos subsequentes.

Perguntas de Revisão (Aula 11)

Pergunta: Qual é o erro mais comum cometido ao tentar adotar o Domain-Driven Design (DDD)?

Resposta: Achar que o DDD se resume a um framework de tecnologia ou a padrões de código (como anotações de entidades), ignorando a modelagem estratégica de negócios.

Pergunta: Por que a modelagem estratégica é considerada mais importante que a tática no DDD?

Resposta: Porque ela representa cerca de 80% do valor do DDD, focando em entender o domínio, alinhar a linguagem com os especialistas e definir os limites corretos dos contextos antes de escrever o código.

Pergunta: Qual é a diferença prática entre um subdomínio Core e um subdomínio Generic?

Resposta: O Core é o diferencial competitivo exclusivo da empresa, onde deve ser concentrado o maior esforço de inovação; o Generic engloba funcionalidades comuns encontradas em diversas outras organizações (como processamento de pagamentos).

Pergunta: O que é a Linguagem Ubíqua (Ubiquitous Language)?

Resposta: É um vocabulário rigoroso e compartilhado entre desenvolvedores e especialistas de negócio, utilizado tanto na comunicação verbal quanto na modelagem do código para evitar ambiguidades.

Pergunta: Para que servem ferramentas como JMolecules e ArchUnit no contexto de arquitetura e DDD?

Resposta: Servem para mapear conceitos de DDD diretamente no código através de anotações e para automatizar testes de arquitetura, garantindo que as regras estruturais e de camadas da aplicação não sejam corrompidas ao longo do tempo.

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
