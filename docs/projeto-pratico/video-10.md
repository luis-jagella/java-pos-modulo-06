# Projeto — Vídeo 10: LSP e composição

> Tarefa 10. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas: projeto-10.vtt
Conceitos Explicados

Princípio da Substituição de Liskov (LSP): Subtipos devem ser substituíveis por seus tipos base sem alterar o comportamento correto do programa. A definição original rigorosa vem de um artigo de 1988 da cientista da computação Barbara Liskov (que também criou os Abstract Data Types). Robert Martin (Uncle Bob) popularizou o conceito em seus livros (Agile Principles, Patterns, and Practices de 2002/2006 e Clean Architecture de 2017) com uma linguagem mais prática.

Subtipagem Comportamental: A noção forte de que um subtipo deve prover todo o comportamento do supertipo e adicionar funcionalidades extras, mas nunca remover ou alterar o contrato comportamental (exemplos citados: TreeSet vs List, Stack vs Queue).

Herança vs. Composição: A recomendação clássica de Effective Java (de Joshua Bloch) de favorecer a composição em vez da herança. Na prática de desenvolvimento real, o uso de extends é bastante reduzido em favor da programação orientada a interfaces e composição.

Encapsulamento Forte: Esconder a representação interna dos dados (como o uso de um Map ou TreeMap) para que quem consome a classe não dependa de detalhes de implementação e possa ter sua estrutura interna alterada sem impactos externos.

Refatorações

Substituição de Herança por Composição: A classe ContadorPalavras foi refatorada para deixar de estender TreeMap<String, Integer> e passar a conter um TreeMap como atributo privado (private Map<String, Integer> mapa = new TreeMap<>();).

Implementação de Iterable e Iterator Customizado: Para permitir o uso de laços for (foreach) em instâncias de ContadorPalavras sem expor o entrySet() do mapa interno, implementou-se a interface Iterable<ContagemPalavra>.

Criação de um Record DTO: Criação do record ContagemPalavra(String palavra, int ocorrências) para representar de forma limpa e isolada a contagem de cada palavra.

Problemas e Correções Realmente Mencionados

Problema: A classe ContadorPalavras estendia TreeMap, herdando dezenas de métodos públicos indesejados (clear, remove, etc.). Isso permitia que o código cliente chamasse operações indevidas (como limpar o contador inteiro ou remover palavras específicas com base em regras erradas).

Correção Ruim (Anti-pattern): Uma prática comum mas indesejada vista em bibliotecas (como o Properties que estende Hashtable no Java desde 1994, ou MessageHeaders no Spring) é sobrescrever métodos indesejados lançando UnsupportedOperationException ou documentando que o uso é desencorajado.

Correção Correta: Remover a herança (extends), aplicar composição encapsulando o mapa, e expor apenas os métodos estritamente necessários (adiciona e a capacidade de iteração customizada).

Limitações do que Depende da Tela

Visualização de trechos de livros de Robert Martin (Agile Principles... e Clean Architecture) e menção ao paper original de Barbara Liskov de 1988 na tela compartilhada.

Acompanhamento visual da estrutura de métodos públicos de um Map/TreeMap na IDE durante a explicação de quais métodos são herdados indevidamente.

Alterações e escrita interativa do código na tela refatorando ContadorPalavras, incluindo a criação do record ContagemPalavra e a implementação manual do Iterator.

5 Perguntas com Respostas

Quem é a autora original do princípio de substituição e qual contribuição importante dela para a computação é citada?

Resposta: Barbara Liskov. Ela também é citada como criadora dos Abstract Data Types (tipos de dados abstratos / encapsulamento como private).

Qual é a definição intuitiva e simplificada de subtipo segundo a teoria de Barbara Liskov discutida no vídeo?

Resposta: Um subtipo deve prover todo o comportamento dos objetos do supertipo (classe mãe), podendo adicionar comportamentos extras, mas sem tirar nenhum comportamento existente.

Por que Set não é considerado um subtipo de List (e vice-versa)?

Resposta: Porque o comportamento de adição difere; o Set não permite elementos duplicados e descarta duplicatas, enquanto a List permite a inserção duplicada, violando a consistência comportamental esperada.

Qual design pattern ou diretriz de design o livro Effective Java (de Joshua Bloch) recomenda em detrimento do uso excessivo de herança?

Resposta: Favorecer a composição em vez da herança.

Como a refatoração final removeu o vazamento de detalhes de implementação (como o uso de entrySet do Map) em ContadorPalavras?

Resposta: Através de um encapsulamento forte, criando um record chamado ContagemPalavra e implementando a interface Iterable com um Iterator customizado dentro da própria classe do contador.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
