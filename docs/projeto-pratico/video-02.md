# Projeto — Vídeo 02: SRP e coesão

> Tarefa 02. Fonte: áudio, analisado pelo Gemini. Notas de estudo, não transcrição literal nem reprodução do código visual.

Visão Geral e Aplicação do Single Responsibility Principle (SRP)

O segundo arquivo de áudio aprofunda a refatoração do projeto Cotuba aplicando o Single Responsibility Principle (Princípio da Responsabilidade Única - SRP), um dos pilares dos princípios SOLID cunhados por Robert C. Martin (Uncle Bob). A aula esclarece equívocos comuns sobre o SRP, destacando que o princípio não se resume a "ter apenas um método por classe", mas sim a garantir que uma classe deve ter um, e apenas um, motivo para mudar (relacionado aos atores ou interessados que solicitam modificações).

Evolução Histórica do Princípio da Responsabilidade Única

A discussão teórica aborda a evolução do conceito ao longo do tempo:

Origem em C++ (Anos 90): Inicialmente tratado no contexto de coesão como o relacionamento funcional entre os elementos de um módulo.

Definição em Livros (2002): Robert C. Martin formalizou o princípio associando-o diretamente à resistência a mudanças indesejadas e ao acoplamento.

Refinamento Moderno (Clean Architecture, 2017): O conceito foi redefinido de forma mais precisa: "Um módulo deve ser responsável por um, e apenas um, ator" (onde "ator" representa um grupo de interessados ou stakeholders da organização).

Processo Prático de Refatoração do Cotuba

Para corrigir os problemas estruturais identificados no primeiro módulo, o código monolítico da classe Main foi decomposto em componentes especializados, separando responsabilidades distintas:

Leitura de Opções de Linha de Comando:

Criação da classe responsável exclusivamente por interpretar os parâmetros de entrada (-d para diretório, -f para formato, -o para arquivo de saída e -v para modo verboso) utilizando a biblioteca Common CLI.

Isola completamente a lógica de interface de linha de comando da regra de negócio de geração de e-books.

Renderização de Markdown:

Extração da lógica de leitura e conversão de arquivos Markdown para HTML para uma classe dedicada (RenderizadorMarkdown ou equivalente).

Elimina a duplicação de código que existia anteriormente entre os geradores.

Geração de Saídas (PDF e EPUB):

Separação dos geradores em classes independentes (GeradorPdf e GeradorEpub), permitindo que alterações nas bibliotecas de geração de arquivos não afetem o restante do sistema.

Integração na Classe Main:

A classe Main passa a atuar apenas como um ponto de entrada e orquestrador leve, delegando a execução para as classes especializadas recém-criadas.

Perguntas de Revisão

Como o Princípio da Responsabilidade Única (SRP) é formalizado na arquitetura moderna de software?

Resposta: Uma classe ou módulo deve ser responsável por um, e apenas um, ator (grupo de interessados que motivam mudanças no software).

Qual biblioteca é utilizada no projeto Cotuba para realizar o tratamento e parsing dos argumentos de linha de comando?

Resposta: A biblioteca Common CLI.

Quais foram as principais classes/componentes extraídos da classe monolítica Main durante o processo de refatoração?

Resposta: O leitor de opções de linha de comando, o renderizador de Markdown e os geradores específicos para os formatos PDF e EPUB.

Por que ter apenas um método em uma classe não garante o cumprimento do SRP?

Resposta: Porque o SRP está relacionado aos motivos de mudança (atores) e à coesão funcional, e não apenas à contagem física de métodos ou linhas de código.

Qual é o principal benefício prático de aplicar o SRP na separação entre a leitura de parâmetros e a geração de e-books?

Resposta: Alterações na forma como os comandos via terminal são recebidos não impactam a lógica de geração de arquivos, facilitando a manutenção e os testes unitários.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
