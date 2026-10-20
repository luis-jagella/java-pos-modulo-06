# Aula 08 — Visão Geral das Práticas de Código na Prática

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado: Visão Prática de Arquitetura, Simplicidade e Encapsulamento em Java
Introdução e Abordagem Prática

Esta aula demonstra na prática a aplicação dos conceitos teóricos de arquitetura e design de código utilizando o ecossistema Java (Java SE, Java 21) e frameworks modernos (como Jakarta CDI e Spring). O instrutor enfatiza que, embora utilize exemplos em Java, os conceitos de modelagem e arquitetura são amplamente agnósticos e aplicáveis a outras linguagens corporativas.

Simplicidade na Criação de Componentes e Injeção de Dependência

O Mito da Interface Obrigatória: Historicamente, nos primórdios do Java EE (época do EJB e CORBA), exigia-se a criação redundante de interfaces para praticamente qualquer componente (Service e ServiceImpl). Com a evolução dos frameworks modernos de Injeção de Dependência (CDI, Spring), essa obrigatoriedade técnica deixou de existir.

Boa Prática: Se um componente possui apenas uma única implementação e não há necessidade real de polimorfismo ou múltiplos contratos no momento, criar uma interface preventivamente gera complexidade desnecessária. A recomendação é começar com a classe concreta e refatorar extraindo uma interface apenas quando o requisito de negócio genuinamente exigir.

Encapsulamento e Modelagem de Domínio (Modelos Ricos vs. Anêmicos)

Modelos Anêmicos (Anemic Domain Models): Um erro comum em aplicações corporativas é criar entidades que funcionam apenas como estruturas de dados passivas (apenas atributos com getters e setters públicos), transferindo toda a lógica de negócio para classes de serviço hipertrofiadas (Service Layer).

Modelos Ricos (Rich Domain Models): A abordagem recomendada consiste em encapsular o comportamento e as regras de negócio diretamente dentro da entidade de domínio. Por exemplo, um objeto Team (Time) deve gerenciar sua própria lista de membros (Player), aplicando validações internas (ex: impedir duplicidade ou exceder limites) através de métodos expressivos de domínio, em vez de expor coleções mutáveis diretamente para fora.

Proteção de Coleções: Expor coleções mutáveis através de getters tradicionais fura o encapsulamento. A boa prática é retornar visões imutáveis (Collections.unmodifiableList) ou métodos controlados de manipulação de itens.

Tipos Fortes e Value Objects

O uso excessivo de tipos primitivos genéricos (String, int, BigDecimal) para representar conceitos fundamentais do negócio (como CPF, e-mail, moedas e valores monetários) é desaconselhado.

A criação de objetos de valor (Value Objects / Domain Primitives), como uma classe dedicada Money ou Email, garante validação no momento da construção, segurança semântica e evita erros clássicos de lógica (como somar moedas de tipos diferentes ou inverter parâmetros em métodos).

Organização de Pacotes: Package by Feature vs. Package by Layer

Por Camada (Package by Layer): Agrupar classes tecnicamente (todos os controllers em um pacote, todos os services em outro, todos os repositories em outro). Conforme o sistema cresce, essa estrutura dificulta a manutenção e o desacoplamento.

Por Funcionalidade (Package by Feature): Agrupar o código por domínio de negócio ou funcionalidade (ex: pacote de pagamentos contendo seu próprio controller, service e repositório). Essa abordagem facilita a modularização, a manutenibilidade e a futura extração para microsserviços.

Limitações da Aula

Os exemplos práticos de código digitados e demonstrados na IDE durante a gravação contêm trechos visuais na tela que não podem ser lidos por extenso apenas pelo áudio, sendo interpretados através da explicação verbal do instrutor sobre refatoração e boas práticas.

Perguntas de Revisão (Arquivo 2)

Pergunta: Por que a criação obrigatória de uma interface para cada classe de serviço deixou de ser uma boa prática na maioria dos cenários modernos?

Resposta: Porque com os frameworks modernos de Injeção de Dependência (como CDI e Spring), a injeção de classes concretas é totalmente suportada, tornando interfaces criadas sem necessidade de polimorfismo uma redundância que aumenta a complexidade de manutenção.

Pergunta: Qual é a principal diferença entre um Modelo de Domínio Anêmico e um Modelo de Domínio Rico?

Resposta: O modelo anêmico possui apenas dados e getters/setters, delegando a lógica para serviços externos; já o modelo rico encapsula o comportamento e as regras de negócio diretamente dentro das entidades de domínio.

Pergunta: Qual é o risco de expor uma coleção mutável diretamente através de um método getter em uma entidade Java?

Resposta: Permite que código externo modifique o estado interno da entidade sem passar pelas validações e regras de negócio definidas no domínio, quebrando o encapsulamento.

Pergunta: O que são Value Objects (Domain Primitives) e qual é a vantagem de utilizá-los?

Resposta: São pequenos objetos imutáveis que representam conceitos específicos do domínio (como Money ou Email). Sua vantagem é garantir validação imediata na criação e evitar o uso incorreto de tipos primitivos genéricos.

Pergunta: Em termos de estruturação de projeto, qual é a principal vantagem da abordagem Package by Feature em comparação a Package by Layer?

Resposta: A organização por funcionalidade agrupa todos os artefatos de um mesmo domínio de negócio juntos, facilitando a modularização, a legibilidade e a eventual migração para arquiteturas de microsserviços.

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
