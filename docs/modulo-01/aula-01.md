# Aula 01 — Software Design & System Design: Introdução

> Origem: resumo da aula fornecido pelo aluno, gerado a partir do áudio. Não é transcrição literal nem análise independente do vídeo completo. O resumo identifica Otávio Santana como instrutor desta aula; o nome cadastrado no portal para a disciplina pode ser diferente.

## Objetivo

Compreender o desenvolvimento de software de ponta a ponta e o motivo da existência de seus componentes. O resumo organiza a discussão em engenharia de software, System Design, arquitetura e Software Design.

## Do “como” ao “porquê”

No início, aprender sintaxe, classes, interfaces e ferramentas ocupa boa parte do esforço. Com a experiência, cresce a necessidade de explicar por que uma solução atende ao negócio, quanto custa mantê-la e como evoluir sem acumular complexidade desnecessária.

Sistemas podem permanecer por décadas enquanto pessoas, equipes e demandas mudam. Reescritas frequentes não são uma solução gratuita: custam dinheiro, tempo e conhecimento de negócio.

## Evolução da complexidade

O resumo apresenta quatro contextos históricos:

1. **Hardware limitado e caro:** economia de recursos e redução de duplicação tinham grande peso. São citados mainframes e normalização de dados. Os valores históricos de memória mencionados no resumo são ilustrativos e não foram verificados; não há uma relação causal única entre custo de hardware e normalização.
2. **Maior capacidade computacional:** computadores pessoais e evolução do processamento ampliaram as possibilidades de implementação. Lei de Moore não deve ser confundida com uma garantia de desempenho de qualquer programa ou de processamento distribuído.
3. **Distribuição e equipes maiores:** banco, aplicação e integrações, como pagamentos, passam a compor um ecossistema com diferentes responsabilidades e equipes.
4. **Excesso de opções:** frameworks, padrões e estilos também podem introduzir complexidade acidental. Escolher ferramentas sem entender o problema pode consumir mais esforço que resolvê-lo.

O ponto é contextualizar decisões: uma restrição importante em uma época pode não ter o mesmo peso em outra.

## Conceitos e níveis de atuação

As fronteiras abaixo são uma organização didática da aula, não cargos exclusivos ou uma sequência rígida de trabalho.

| Conceito | Pergunta central | Exemplos de decisões |
| --- | --- | --- |
| Engenharia de Software | Como combinar teoria, prática e viabilidade para resolver o problema? | Processo de desenvolvimento, feedback, qualidade e custo |
| System Design | Como o sistema completo e seus componentes colaboram? | Limites, integrações, fluxo de dados e requisitos globais |
| Arquitetura de Software | Que estrutura e decisões sustentam a evolução? | Modularidade, distribuição, qualidades e restrições |
| Software Design | Como transformar responsabilidades em código? | Classes, interfaces, colaboração e padrões de projeto |

System Design envolve entendimento do problema e visão do ecossistema; arquitetura equilibra decisões estruturais, tecnologia e organização; Software Design detalha as colaborações. As atividades se influenciam continuamente.

## Pessoas e organização

Comunicação, cultura, preferências técnicas e colaboração influenciam o resultado. A Lei de Conway relaciona o desenho dos sistemas à estrutura de comunicação das organizações que os produzem. É uma lente de análise, não uma garantia de correspondência exata em todos os casos.

Tecnologia isolada não corrige fronteiras mal definidas, decisões pouco comunicadas ou desalinhamento entre equipes.

## Evitar arquitetura por tendência

O resumo usa Clean Architecture para discutir aplicação proporcional de conceitos. Um problema simples não justifica automaticamente muitas camadas e abstrações. O mesmo vale para microsserviços e outras escolhas: é preciso explicar o ganho e o custo no contexto.

Isso não torna a técnica ruim. A questão é se ela ajuda a lidar com regras, mudanças e riscos reais.

## Referências mencionadas no resumo

- *Modern Software Engineering*, David Farley.
- *Clean Architecture*, Robert C. Martin.
- *Refactoring*, Martin Fowler.
- Literatura IEEE sobre engenharia e arquitetura; o resumo não informa uma norma ou edição específica.

Os títulos são referências relatadas, não obras integralmente consultadas para esta anotação.

## Aplicação ao nosso projeto — complemento autoral

Antes de escolher framework, banco ou camadas:

1. Descrever o problema e o resultado esperado.
2. Identificar usuários, componentes externos e fluxos principais.
3. Levantar qualidades e restrições importantes.
4. Comparar uma solução simples com alternativas mais complexas.
5. Registrar o motivo e os custos da escolha.

A aula é introdutória: não foi identificado código a reproduzir a partir do resumo enviado. A implementação do projeto continua aguardando seus guias e vídeos.

## Perguntas de revisão

1. **Por que aprender o “porquê”?** Para avaliar adequação ao negócio, custos e consequências, além de saber usar uma ferramenta.
2. **O que System Design considera?** O conjunto de componentes, integrações e requisitos do sistema.
3. **Onde Software Design atua?** No detalhamento de responsabilidades e colaborações que se tornam código.
4. **Como organização influencia arquitetura?** Seus canais e barreiras de comunicação podem moldar as fronteiras do sistema.
5. **O que é complexidade acidental?** Dificuldade introduzida pela solução e pelas escolhas de implementação, além daquela inerente ao problema.
6. **Quando aplicar uma arquitetura conhecida?** Quando seus benefícios justificarem os custos para o problema concreto.

## Pontos a confirmar

- Contexto e valores exatos dos exemplos históricos de hardware.
- Quais documentos IEEE foram citados.
- Referências ou exemplos visuais ausentes do resumo de áudio.
