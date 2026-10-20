# Exercícios — Módulo 01

Branch de trabalho: `modulo-01-software-system-design`.

As 12 aulas teóricas estão documentadas em [docs/modulo-01](../../docs/modulo-01/README.md), sincronizadas com a `main`. Esta branch preserva o espaço para exercícios conceituais; o código Cotuba fica na branch `projeto-pratico` para não misturar as duas seções do portal.

## Convenção

- Criar uma pasta `aula-NN` quando houver exercício real.
- Identificar o objetivo e o vínculo com a aula.
- Registrar requisitos, como executar e como verificar.
- Distinguir código apresentado pelo professor de experimentos autorais.
- Manter as notas consolidadas em [docs/modulo-01](../../docs/modulo-01/README.md).

## Exercícios de consolidação

1. **Aulas 02–03:** desenhe contexto e containers de um sistema que você conhece. Explique quais fronteiras são lógicas e quais são processos ou serviços separados. Registre uma decisão em ADR, com uma opção rejeitada e seu custo.
2. **Aulas 04–06:** uma consulta ficou lenta durante um pico. Liste quais métricas faltam antes de propor cache, réplicas ou troca de banco. Diferencie o sintoma observado de uma hipótese de causa.
3. **Aulas 07–10:** no Cotuba, encontre quem conhece a biblioteca de PDF e quem apenas depende do contrato de geração. Explique o que mudaria ao adicionar outro formato e por que DI, sozinha, não comprova DIP.
4. **Aulas 11–12:** identifique entidade, objeto de valor e agregado em um domínio do seu trabalho. Escreva uma invariante de negócio e uma regra estrutural testável; explique por que são testes diferentes.

Referências da prática: [implementação](https://github.com/luis-jagella/java-pos-modulo-06/blob/projeto-pratico/projeto/README.md), [validação](https://github.com/luis-jagella/java-pos-modulo-06/blob/projeto-pratico/projeto/VALIDACAO.md) e [PR #1](https://github.com/luis-jagella/java-pos-modulo-06/pull/1). A stack conferida no material é Java 25/Maven; não há exercício executável separado inventado para cada aula teórica.
