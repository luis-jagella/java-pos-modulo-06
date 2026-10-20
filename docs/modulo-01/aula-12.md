# Aula 12 — Conhecendo o jMolecules

> Fonte: legenda automática disponibilizada pelo player da aula, lida integralmente. O download do áudio falhou. Termos como jMolecules, DDD e Value Object foram normalizados; a legenda não permite reproduzir literalmente o código mostrado na tela.

## Objetivo

Expressar decisões de design no código e verificar sua estrutura com testes. jMolecules fornece vocabulário arquitetural; a integração com ArchUnit permite executar regras em vez de depender apenas de diagramas e convenções.

## Evidências de DDD no código

O professor demonstra uma entidade de cartão com identificador e número mascarado. Ao marcar a classe como entidade sem indicar sua identidade, a validação falha. A correção identifica o atributo correspondente, deixando explícito o que distingue uma instância de outra.

- `@Entity`: objeto com identidade ao longo do tempo.
- `@Identity`: atributo ou método que representa essa identidade.
- `@ValueObject`: conceito definido pelo valor, como dinheiro ou uma bandeira de cartão no exemplo; não possui identidade independente.
- `@AggregateRoot`: raiz que delimita o acesso e as invariantes de um agregado. O exemplo utiliza um pedido e seus produtos.
- `@Repository`: abstração de uma coleção de objetos do domínio.
- `@Service`: operação de domínio que não pertence naturalmente a uma entidade ou objeto de valor.

Também são apresentados conceitos como fábricas, módulos e contextos delimitados. A biblioteca oferece modelos baseados em anotações e em interfaces; o instrutor prefere as anotações nos exemplos, mas ambas são opções.

## Repository não é apenas um DAO renomeado

Um DAO costuma abstrair operações de acesso a dados, como inserção, atualização e exclusão. Um Repository oferece uma visão de coleção alinhada ao domínio e à linguagem do negócio. A implementação pode usar um banco, mas o contrato não precisa expor os detalhes desse banco.

O exemplo verbal de uma coleção de carros chamada `Garagem` mostra que o nome pode comunicar o negócio em vez de carregar o sufixo técnico `Repository`. É uma escolha de linguagem, não obrigação de nomenclatura.

O instrutor menciona Jakarta Data e operações como `save` e criação, contrastando contratos orientados ao domínio com abstrações de persistência. Não há código suficiente na legenda para reconstruir essas assinaturas com segurança.

## Camadas e validação

Além de DDD, jMolecules permite indicar camadas de aplicação, domínio, infraestrutura e interface. As anotações podem expressar a estrutura em tipos ou pacotes, conforme sua finalidade. É possível combinar organização por funcionalidade com camadas internas.

O teste demonstrado reúne regras de DDD e de camadas. O valor está em detectar violações durante o build, por exemplo uma entidade sem identidade ou uma dependência proibida entre camadas.

Importante: anotar uma classe não comprova que o modelo está correto para o negócio. Testes estruturais verificam somente as regras configuradas; testes de comportamento e conversas com especialistas continuam necessários. Integrações com Spring dependem dos módulos e da configuração utilizados, não apenas de uma anotação isolada.

## Conexão com a aula anterior

O fechamento reforça a prioridade do design estratégico: linguagem compartilhada, entendimento do domínio e limites dos contextos vêm antes dos padrões táticos. A proporção de “80% estratégico” mencionada pelo professor é uma ênfase didática, não uma medição universal.

## Revisão

1. **Para que serve jMolecules?** Para expressar conceitos arquiteturais no código e permitir integração com ferramentas de validação e documentação.
2. **Por que a entidade do exemplo falhou no teste?** Faltava indicar sua identidade.
3. **Value Object precisa de ID?** Não; sua igualdade é definida pelos valores relevantes ao domínio.
4. **Qual a diferença entre DAO e Repository?** O primeiro abstrai acesso a dados; o segundo representa uma coleção do domínio.
5. **As regras substituem a modelagem de negócio?** Não. Elas ajudam a manter decisões estruturais, mas não validam sozinhas a adequação do modelo.

## Referências para conferir APIs

- [jMolecules — documentação oficial](https://github.com/xmolecules/jmolecules).
- [Integrações do jMolecules, incluindo ArchUnit](https://github.com/xmolecules/jmolecules-integrations).

Os exemplos de código do projeto serão próprios e identificados como prática, não como transcrição da tela.
