# Módulo 06 — Software Design e System Design

Repositório de estudo da pós-graduação **Java Applied Engineering**.

Estrutura conferida no portal: **12 aulas no Módulo 01, 15 vídeos de Projeto Prático e 2 podcasts**, além da avaliação. Os checks abaixo representam anotações e práticas registradas no Git; não alteram o progresso no ambiente virtual.

## Branches

| Branch | Responsabilidade |
| --- | --- |
| `main` | Índice, notas consolidadas e acompanhamento |
| [`modulo-01-software-system-design`](https://github.com/luis-jagella/java-pos-modulo-06/tree/modulo-01-software-system-design) | Estudos e exercícios das 12 aulas teóricas |
| [`projeto-pratico`](https://github.com/luis-jagella/java-pos-modulo-06/tree/projeto-pratico) | Implementação acompanhando os 15 vídeos do projeto |
| `feat/<tema>` | Experimentos derivados da branch correspondente, quando necessário |

O portal chama a seção prática de **Projeto Prático**; ela não foi renomeada como “Módulo 02”. Podcasts ficam junto às notas, sem uma branch só para cada episódio.

## Módulo 01 — Aulas teóricas

Títulos completos conferidos nos cabeçalhos das aulas. A numeração preserva a separação entre conteúdo conceitual e aplicação prática de Design Patterns.

- [x] [Aula 01 — Software Design & System Design — Introdução](docs/modulo-01/aula-01.md).
- [x] [Aula 02 — Princípios Básicos de System Design](docs/modulo-01/aula-02.md).
- [x] [Aula 03 — System Design e Documentação](docs/modulo-01/aula-03.md).
- [x] [Aula 04 — Introdução a Banco de Dados na Perspectiva de System Design](docs/modulo-01/aula-04.md).
- [x] [Aula 05 — Comunicação entre Componentes (System Design)](docs/modulo-01/aula-05.md).
- [x] [Aula 06 — Exemplos e Visão de Geral de Cases de System Design](docs/modulo-01/aula-06.md).
- [x] [Aula 07 — Visão Geral de Práticas de Código](docs/modulo-01/aula-07.md).
- [x] [Aula 08 — Visão Geral das Práticas de Código na Prática](docs/modulo-01/aula-08.md).
- [x] [Aula 09 — Design Patterns](docs/modulo-01/aula-09.md).
- [x] [Aula 10 — Design Patterns — Visão Prática](docs/modulo-01/aula-10.md).
- [x] [Aula 11 — Visão Geral sobre DDD](docs/modulo-01/aula-11.md).
- [x] [Aula 12 — Conhecendo o jMolecules](docs/modulo-01/aula-12.md).

As anotações serão consolidadas no [índice da teoria](docs/modulo-01/README.md). Para registrar uma aula, use o [modelo de notas](docs/modelo-aula.md).

## Projeto Prático — 15 vídeos

Os cabeçalhos identificam resoluções de **14 tarefas em 15 vídeos**: a tarefa 13 possui duas partes. A lista lateral usa o mesmo título genérico para todos. Requisitos conferidos no quadro de tarefas indicado pelo guia. Os checks desta seção indicam notas registradas; a execução prática tem seu próprio acompanhamento.

- [x] [Projeto — Vídeo 01: Ambiente e diagnóstico do Cotuba](docs/projeto-pratico/video-01.md).
- [x] [Projeto — Vídeo 02: SRP e coesão](docs/projeto-pratico/video-02.md).
- [x] [Projeto — Vídeo 03: DDD tático](docs/projeto-pratico/video-03.md).
- [x] [Projeto — Vídeo 04: DIP e injeção com CDI/Weld](docs/projeto-pratico/video-04.md).
- [x] [Projeto — Vídeo 05: OCP e geração HTML](docs/projeto-pratico/video-05.md).
- [x] [Projeto — Vídeo 06: Imutabilidade e Builder](docs/projeto-pratico/video-06.md).
- [x] [Projeto — Vídeo 07: C4 do Cotubify](docs/projeto-pratico/video-07.md).
- [x] [Projeto — Vídeo 08: jMolecules e ArchUnit](docs/projeto-pratico/video-08.md).
- [x] [Projeto — Vídeo 09: Plugins com ServiceLoader](docs/projeto-pratico/video-09.md).
- [x] [Projeto — Vídeo 10: LSP e composição](docs/projeto-pratico/video-10.md).
- [x] [Projeto — Vídeo 11: ISP e segregação de plugins](docs/projeto-pratico/video-11.md).
- [x] [Projeto — Vídeo 12: Escalabilidade do Cotubify](docs/projeto-pratico/video-12.md).
- [x] [Projeto — Vídeo 13: Módulos Maven e JPMS — parte 1](docs/projeto-pratico/video-13.md).
- [x] [Projeto — Vídeo 14: Módulos Maven e JPMS — parte 2](docs/projeto-pratico/video-14.md).
- [x] [Projeto — Vídeo 15: Resiliência de pagamentos](docs/projeto-pratico/video-15.md).

Veja o [plano do projeto](docs/projeto-pratico/README.md). O código é acompanhado na branch `projeto-pratico`; documentação de aula não equivale a implementação validada.

## Podcasts e avaliação

- [x] [Podcast 01 — Fazendo as pazes com o legado — conceitos](docs/podcasts/podcast-01.md).
- [x] [Podcast 02 — Fazendo as pazes com o legado — ferramentas e prática](docs/podcasts/podcast-02.md).
- [x] [Preparar revisão da disciplina — 30 perguntas e roteiro](docs/revisao-final.md).

Os episódios serão registrados em [docs/podcasts](docs/podcasts/README.md). O resultado da avaliação só deve ser registrado quando confirmado pelo aluno.

## Entrega prática

A implementação está na branch `projeto-pratico`, no [PR #1](https://github.com/luis-jagella/java-pos-modulo-06/pull/1), sem merge automático. Veja os [comandos de execução](https://github.com/luis-jagella/java-pos-modulo-06/blob/projeto-pratico/projeto/README.md) e a [validação local](https://github.com/luis-jagella/java-pos-modulo-06/blob/projeto-pratico/projeto/VALIDACAO.md). O Cotuba CLI está implementado; o Cotubify é modelagem de arquitetura, não um serviço de pagamentos em produção.

## Materiais disponíveis no portal

- Indicação de leitura.
- Material autoral 01 e 02.
- Slides.
- Guia do Projeto Prático 01 e 02.

Os dois guias de projeto foram lidos. O primeiro explica o uso do Trello; o segundo aponta o [quadro da disciplina](https://trello.com/b/XamAmgsf/projeto-pratico-unipds-software-design-e-system-design), com 14 tarefas (08–11 opcionais). As notas utilizam o resumo enviado, análises dos áudios e legendas do player, com a origem indicada em cada arquivo. Veja também a [revisão técnica](docs/revisao-tecnica.md). Áudios, PDFs e URLs de sessão ficam fora do Git.

## Fluxo de estudo

1. Estudar a aula e registrar conteúdo, exemplos, dúvidas e origem das notas.
2. Praticar na branch da teoria ou do projeto, conforme a seção.
3. Validar a mudança e registrar o commit.
4. Consolidar notas na `main`; integrar código por PR quando houver entrega revisável.
5. Marcar o checklist apenas depois da entrega correspondente.

## Relação com o módulo anterior

O [módulo 05 — Arquitetura de Sistemas](https://github.com/luis-jagella/java-pos-modulo-05) apresentou estilos e trade-offs. Este repositório acompanhará a disciplina de Software Design e System Design conforme sua própria sequência de aulas e projeto.
