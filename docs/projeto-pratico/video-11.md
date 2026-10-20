# Projeto — Vídeo 11: ISP e segregação de plugins

> Tarefa 11. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas: projeto-11.vtt
Conceitos Explicados

Princípio da Segregação de Interfaces (ISP): O "I" do SOLID. Estabelece que classes (clientes) não devem ser forçadas a depender de métodos que não utilizam. Evita a criação de interfaces "gordas" (fat interfaces).

Coesão e Motivos para Mudança: Interfaces coesas agrupam comportamentos que pertencem ao mesmo contexto ou objetivo. Quando uma interface acumula responsabilidades distintas para momentos diferentes, ela viola a coesão e gera acoplamento indesejado nas classes que a implementam.

Mito de que "Toda interface deve ter apenas um método": Esclarecimento de que a regra do ISP não significa obrigatoriedade absoluta de um único método por interface, mas sim que métodos devem ser segregados quando pertencem a momentos, fluxos ou responsabilidades completamente diferentes e independentes.

Refatorações

Segregação da Interface CotubaPlugin: A interface única CotubaPlugin (que continha os métodos aposRenderizacao e aposGeracao) foi quebrada em duas novas interfaces focadas:

CotubaPluginAposRenderizacao (contendo apenas o método de pós-renderização).

CotubaPluginAposGeracao (contendo apenas o método de pós-geração).

Atualização de Serviços e Renderizadores: O CotubaService passou a utilizar a interface de pós-geração, enquanto o renderizador de Markdown passou a utilizar a interface de pós-renderização.

Atualização dos Plugins Concretos:

O plugin de tema (PluginTema) foi atualizado para implementar apenas CotubaPluginAposRenderizacao, eliminando métodos vazios ou retornos artificiais.

O plugin de estatísticas (PluginEstatisticasEbook) foi atualizado para implementar apenas CotubaPluginAposGeracao.

Problemas e Correções Realmente Mencionados

Problema: A interface CotubaPlugin original exigia que qualquer plugin implementasse tanto aposRenderizacao quanto aposGeracao. Como um plugin específico (como o de tema ou o de estatísticas) tipicamente só precisava de uma das ações, os desenvolvedores eram obrigados a implementar o outro método retornando vazio, gerando blocos vazios ou código morto (mau cheiro de código / code smell).

Correção: Dividir a interface "gorda" em interfaces menores e específicas (ISP), fazendo com que cada plugin implemente estritamente o contrato da funcionalidade que lhe diz respeito.

Limitações do que Depende da Tela

Exibição de diagramas conceituais retirados do livro Clean Architecture na tela compartilhada, mostrando classes clientes conectadas a múltiplas operações.

Acompanhamento visual da alteração dos nomes de arquivos e interfaces (CotubaPlugin dividido em CotubaPluginAposRenderizacao e CotubaPluginAposGeracao) na IDE.

Execução dos comandos de build (mvn install), geração de EPUB e PDF via terminal, e abertura visual dos arquivos gerados para confirmar o funcionamento correto do tema CSS e do plugin de estatísticas.

5 Perguntas com Respostas

O que dita o Princípio da Segregação de Interfaces (ISP)?

Resposta: Que as classes não devem ser forçadas a depender de métodos que elas não utilizam.

Qual era o problema de design na interface original CotubaPlugin antes da refatoração?

Resposta: Ela acumulava duas responsabilidades distintas (pós-renderização e pós-geração), forçando os plugins a implementarem métodos vazios ou desnecessários para o seu propósito.

Em quantas e quais interfaces a interface CotubaPlugin foi dividida?

Resposta: Foi dividida em duas: CotubaPluginAposRenderizacao e CotubaPluginAposGeracao.

Segundo a discussão no vídeo, é uma regra obrigatória que toda interface deva ter exatamente um único método?

Resposta: Não. A segregação deve ocorrer quando há objetivos, momentos e conceitos diferentes; se os métodos forem altamente coesos e sempre usados juntos, não há problema em ter mais de um.

Quais plugins específicos foram refatorados para utilizar as novas interfaces segregadas?

Resposta: O plugin de tema (PluginTema, focado em pós-renderização) e o plugin de estatísticas do ebook (PluginEstatisticasEbook, focado em pós-geração).

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
