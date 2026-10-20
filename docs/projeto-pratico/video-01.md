# Projeto — Vídeo 01: Ambiente e diagnóstico do Cotuba

> Tarefa 01. Fonte: áudio, analisado pelo Gemini. Notas de estudo, não transcrição literal nem reprodução do código visual.

Visão Geral e Contexto do Projeto Cotuba

O primeiro arquivo de áudio aborda a apresentação do projeto prático principal da disciplina de Software Design e System Design, intitulado Cotuba. O projeto consiste em um gerador de e-books nos formatos PDF e EPUB a partir de arquivos Markdown. Trata-se de uma aplicação real baseada no livro "Desbravando Solid" publicado pela Casa do Código em 2022, escrito pelo professor Otávio Santana.

A disciplina é dividida em duas partes principais: as primeiras seis aulas focadas em System Design e as seis últimas focadas em Software Design. A dinâmica pedagógica consiste em disponibilizar tarefas divididas em categorias (essenciais, intermediárias e desafios), incentivando os alunos a tentarem resolver os problemas de forma autônoma antes de conferirem as resoluções comentadas em vídeo.

Configuração do Ambiente e Tecnologias Utilizadas

Para iniciar o projeto, os estudantes devem acessar o repositório oficial na conta da UNIPDS e realizar um fork para seus próprios ambientes. O ecossistema técnico do projeto abrange:

Linguagem Java: Versão 25 (última LTS no momento do desenvolvimento).

Gerenciador de Dependências: Apache Maven (pom.xml).

Formatos de Saída: PDF e EPUB.

Bibliotecas Principais:

iText: Utilizada para a geração de arquivos PDF a partir de HTML.

EpubLib: Utilizada para a criação e manipulação de arquivos EPUB.

CommonMark: Biblioteca para o parsing e conversão de arquivos Markdown em HTML.

Common CLI: Biblioteca para o tratamento e leitura de opções de linha de comando (CommandLine).

Execução Inicial e Análise do Código

O projeto base possui uma estrutura inicial contendo o diretório cotuba (código-fonte principal), a pasta de testes e a apostila de design. Ao executar os testes iniciais via Maven (mvn test), verifica-se que a suíte de testes passa com sucesso. No entanto, a arquitetura inicial do projeto apresenta problemas significativos de design, concentrando toda a lógica de negócio, leitura de parâmetros de linha de comando, conversão de Markdown e geração de arquivos em uma única classe central (a classe Main).

Limitações e Problemas Identificados na Classe Principal (Main)

Violação de Princípios de Projeto: A classe Main acumula múltiplas responsabilidades (lê parâmetros de linha de comando, manipula o sistema de arquivos, converte Markdown e gera os formatos PDF/EPUB).

Duplicação de Código: Lógicas semelhantes de manipulação de diretórios e arquivos aparecem duplicadas entre os diferentes geradores de saída.

Dificuldade de Manutenção: A adição de novos formatos de saída ou modificações nas opções de linha de comando exigem alterações diretas na classe principal, aumentando o acoplamento.

Perguntas de Revisão

Qual é o objetivo principal do software Cotuba desenvolvido na disciplina?

Resposta: Funcionar como um gerador de e-books nos formatos PDF e EPUB a partir de arquivos Markdown.

Quais bibliotecas principais são utilizadas para a renderização dos formatos finais de e-book?

Resposta: A biblioteca iText para a geração de PDFs e a biblioteca EpubLib para a geração de arquivos EPUB.

Qual versão do Java é especificada no arquivo de configuração do Maven (pom.xml) do projeto?

Resposta: Java 25 (a versão LTS utilizada como padrão no ambiente da disciplina).

Qual é o principal problema arquitetural identificado na classe Main do código inicial do Cotuba?

Resposta: A concentração de múltiplas responsabilidades (leitura de parâmetros, parsing de Markdown, manipulação de arquivos e geração de e-books) em uma única classe, gerando forte acoplamento.

Como é feita a conversão dos arquivos de texto para que possam ser processados pelas bibliotecas de geração de e-books?

Resposta: Os arquivos em formato Markdown são convertidos para HTML utilizando a biblioteca CommonMark antes de serem renderizados em PDF ou EPUB.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
