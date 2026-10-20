# Projeto Prático — Cotuba e Cotubify

Fonte de requisitos: [quadro público da disciplina](https://trello.com/b/XamAmgsf/projeto-pratico-unipds-software-design-e-system-design), indicado pelo Guia do Projeto Prático 02. O Guia 01 contém orientações genéricas de Trello. Não é necessário copiar o quadro para estudar.

O Cotuba gera e-books a partir de Markdown. O Cotubify é o cenário de arquitetura para uma plataforma de autopublicação; os exercícios de C4, escala e pagamentos são **modelagem**, não uma plataforma de produção implementada.

## Aulas e tarefas

| Vídeo | Tarefa | Tema | Notas |
| --- | --- | --- | --- |
| 01 | 01 | Ambiente e diagnóstico do Cotuba | [Resumo](video-01.md) |
| 02 | 02 | SRP e coesão | [Resumo](video-02.md) |
| 03 | 03 | DDD tático | [Resumo](video-03.md) |
| 04 | 04 | DIP e injeção com CDI/Weld | [Resumo](video-04.md) |
| 05 | 05 | OCP e geração HTML | [Resumo](video-05.md) |
| 06 | 06 | Imutabilidade e Builder | [Resumo](video-06.md) |
| 07 | 07 | C4 do Cotubify | [Resumo](video-07.md) |
| 08 | 08 (opcional) | jMolecules e ArchUnit | [Resumo](video-08.md) |
| 09 | 09 (opcional) | Plugins com ServiceLoader | [Resumo](video-09.md) |
| 10 | 10 (opcional) | LSP e composição | [Resumo](video-10.md) |
| 11 | 11 (opcional) | ISP e segregação de plugins | [Resumo](video-11.md) |
| 12 | 12 | Escalabilidade do Cotubify | [Resumo](video-12.md) |
| 13 | 13 | Módulos Maven e JPMS — parte 1 | [Resumo](video-13.md) |
| 14 | 13 | Módulos Maven e JPMS — parte 2 | [Resumo](video-14.md) |
| 15 | 14 | Resiliência de pagamentos | [Resumo](video-15.md) |

## Entregas e validação

- [x] Guias e requisitos conferidos.
- [ ] CLI: PDF, EPUB e site HTML com metadados e capítulos ordenados.
- [ ] Domínio imutável, DIP, CDI e seleção por qualificador.
- [ ] jMolecules/ArchUnit, plugins, composição e interfaces segregadas.
- [ ] Módulos Maven e JPMS, execução e testes documentados.
- [ ] C4, ADRs de geração assíncrona, cache e pagamentos idempotentes.

Implementação na branch `projeto-pratico`. A base indicada pela disciplina é [unipds-projetos/modulo5-projeto-cotuba](https://github.com/unipds-projetos/modulo5-projeto-cotuba), com Java 25/Maven. Não se deve copiar a rotina legada que apaga recursivamente o destino de saída; a nossa prática deve recusar sobrescritas destrutivas.
