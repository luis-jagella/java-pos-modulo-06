# Projeto — Vídeo 03: DDD tático

> Tarefa 03. Fonte: áudio, analisado pelo Gemini. Notas de estudo, não transcrição literal nem reprodução do código visual.

Resumo da Aula: Aplicação Prática de DDD Tático (Projeto Cotuba - Parte 3)

Nesta aula, o foco é a aplicação dos conceitos de Domain-Driven Design (DDD) Tático no desenvolvimento do projeto Cotuba (um gerador de e-books). Os instrutores discutem a modelagem do código para representar fielmente o domínio do negócio, utilizando referências clássicas da literatura de DDD, como os livros de Eric Evans (2003) e Vaughn Vernon (2013).

Conceitos e Abordagens Principais

DDD Tático vs. Estratégico: A aula concentra-se no nível tático, mais próximo ao código, trabalhando com conceitos como Value Objects, Aggregates, Entities, Repositories e Services (Capítulos 5 a 12 do livro de Vaughn Vernon).

Linguagem Ubíqua: Reforça-se a importância de todos no time (desenvolvedores, PMs, etc.) falarem a mesma língua centrada no negócio, garantindo que termos como e-book, capítulo e formato estejam claros no código.

Modelagem orientada ao domínio: Em vez de projetar o banco de dados primeiro, o código é modelado para refletir as regras de negócio reais.

Exemplos e Decisões de Design

Criação do Value Object FormatoEbook: Identificou-se que o formato do e-book (PDF ou EPUB) é um valor imutável que dita comportamentos diferentes no sistema, sendo tratado como um Value Object (análogo a um Enum).

Tratamento de Exceções: Ajustou-se o tratamento de erros para lançar exceções amigáveis (como IllegalArgumentException traduzidas para mensagens claras ao usuário) quando um formato inválido é passado via linha de comando.

Evolução das Entidades e Agregados:

O E-book foi tratado inicialmente como um agregado contendo uma lista de capítulos, título e autor.

O Capítulo ganhou uma identidade própria e estruturada (título e conteúdo em Markdown/HTML).

Refatoração da Arquitetura (Repositórios e Serviços):

Introdução de classes de repositório para buscar arquivos Markdown de diretórios.

Criação da classe de serviço (CotubaService) para isolar a lógica de negócio e orquestrar a geração dos e-books, separando a responsabilidade da interface de usuário (CLI).

Introdução de uma classe de parâmetros (ParametrosCotuba) para encapsular os argumentos de entrada e limpar a assinatura dos métodos.

Ferramentas e Limitações Mencionadas

Ferramentas: Java, Git (gerenciamento de branches por tarefa), bibliotecas de leitura de arquivos e manipulação de HTML/Markdown.

Limitações e Problemas Encontrados:

O código inicial sofria de forte acoplamento na classe Main, misturando interface de linha de comando com regras de negócio.

Ocorreram erros de compilação e exceções em testes de integração (como NullPointerException e problemas com o arquivo properties) que exigiram refatorações incrementais e blocos try-catch para garantir a robustez.

Limitações na leitura de caracteres especiais e acentuação em arquivos de propriedades (exigindo cuidados com codificação UTF-8).

Perguntas de Revisão

Qual é a principal diferença entre o DDD estratégico e o DDD tático abordado no projeto?

Resposta: O DDD estratégico foca na divisão de domínios, subdomínios e contextos delimitados em grande escala, enquanto o DDD tático foca na proximidade com o código, utilizando blocos de construção como Value Objects, Entities, Aggregates, Repositories e Services.

Por que o formato do e-book (PDF ou EPUB) foi modelado como um Value Object (ou Enum) em vez de uma simples String?

Resposta: Porque o formato possui um conjunto fixo e imutável de valores válidos no domínio, garantindo validação em tempo de execução e evitando o uso de strings soltas que poderiam gerar exceções inesperadas.

Qual é o papel da classe de serviço (CotubaService) na arquitetura refinada durante a aula?

Resposta: O serviço serve para centralizar e isolar as regras de negócio e a orquestração do sistema, descarregando a interface de usuário (CLI) da responsabilidade de processar diretamente os dados e gerar os arquivos.

Como foi resolvido o problema de passar múltiplos parâmetros dispersos entre os métodos da aplicação?

Resposta: Criando uma classe específica para centralizar os parâmetros de execução (ParametrosCotuba), o que limpa as assinaturas dos métodos e melhora a organização do código.

O que motivou a criação de um repositório de arquivos Markdown no projeto?

Resposta: A necessidade de separar a responsabilidade de leitura, busca e mapeamento dos arquivos físicos do disco em relação à lógica de criação dos capítulos do e-book.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
