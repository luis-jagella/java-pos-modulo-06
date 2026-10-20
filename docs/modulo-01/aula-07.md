# Aula 07 — Visão Geral de Práticas de Código

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado: Princípios de Software Design e Qualidade de Código
Introdução e Contextualização

Esta aula representa a segunda parte do módulo, dando continuidade aos conceitos iniciais de System Design, engenharia de software, camadas físicas e lógicas de bancos de dados e documentação. O foco principal agora transita para o nível tático: Software Design e os princípios fundamentais para a escrita de códigos de alta qualidade. O instrutor ressalta que o domínio de Design Patterns só é plenamente compreendido quando se dominam os princípios subjacentes de estruturação de código, fazendo uma analogia com a teoria musical (assim como é preciso entender escalas e harmonia antes de criar acordes complexos).

O Impacto do Código Ruim e o Débito Técnico

Estatísticas de Produtividade: Estudos indicam que aproximadamente 42% do tempo semanal de um desenvolvedor é gasto lidando com problemas técnicos e código de baixa qualidade. Em termos de mercado global, isso representa um custo multibilionário.

A Teoria da Janela Partida (Windows Broken): Aplicada à engenharia de software, a teoria demonstra que ambientes e códigos desorganizados convidam a mais degradação e erros. O instrutor cita o exemplo clássico dos carros no Bronx: um veículo intacto deixado na rua permanece seguro por mais tempo do que um veículo com uma janela quebrada, que rapidamente é totalmente vandalizado. No dia a dia, ver código ruim diminui o rigor ético e profissional do desenvolvedor ("efeito pia suja").

Débito Técnico e Legado Prematuro: O débito técnico acumulado torna o software excessivamente lento e caro a longo prazo. O "legado prematuro" é definido como código que se torna ilegível ou inviável de manter em menos de 10 anos. Relatórios da indústria (como o Chaos Report e estudos da McKinsey e Oxford) apontam que apenas cerca de 31% dos projetos de software são considerados plenamente bem-sucedidos (dentro do prazo, orçamento e escopo esperado), enquanto a maioria enfrenta sérios desafios ou falhas.

O Papel da Arquitetura e das Metodologias Ágeis: O instrutor critica a tendência de algumas metodologias ágeis de ignorarem a figura do arquiteto de software em prol de papéis estritamente gerenciais, transformando o "ágil" em modelos em cascata disfarçados de ciclos curtos, o que frequentemente prejudica a visão sistêmica e a qualidade de longo prazo.

Retorno sobre o Investimento (ROI) de Código Limpo

Vender a necessidade de refatoração e código limpo para cargos executivos (C-level) é um desafio histórico. No entanto, métricas de mercado (como o Developer Experience e relatórios da DORA) provam que equipes com alta cobertura de testes e código limpo apresentam:

Redução drástica na frequência e no tempo de resolução de bugs.

Maior velocidade (delivery) de novas funcionalidades a médio e longo prazo.

Redução no esforço de onboarding de novos engenheiros na equipe.

Princípios Fundamentais de Code Design

Keep It Simple, Stupid (KISS) / Simplicidade: Inspirado em Leonardo da Vinci ("A simplicidade é o último grau de sofisticação") e em princípios da engenharia naval americana da década de 1960. O código deve ser escrito prioritariamente para ser compreendido por seres humanos, e não apenas por computadores.

YAGNI (You Aren't Gonna Need It): O perigo de tentar prever o futuro (just in case). Engenheiros frequentemente criam estruturas complexas antecipando necessidades que nunca se concretizam. Citação de Donald Knuth: "A otimização prematura é a raiz de todo o mal".

Don't Repeat Yourself (DRY): O código e o conhecimento organizacional não devem ser duplicados. Evitar a obsessão por tipos primitivos soltos (ex: passar Strings ou ints sem validação para conceitos de negócio como CPF ou dinheiro).

Separação de Responsabilidades e Organização: Importância da divisão clara entre camadas e funcionalidades (Package by Feature versus Package by Layer).

Perigos da Herança Inadequadas: Alerta contra o uso excessivo e incorreto de herança hierárquica profunda apenas para reuso de código (analogia bem-humorada comparada a heranças incorretas na modelagem).

Princípios SOLID, Coesão e Acoplamento:

SRP (Single Responsibility Principle): Uma classe deve ter apenas um motivo para mudar.

OCP (Open/Closed Principle): Aberto para extensão, fechado para modificação.

LSP (Liskov Substitution Principle): Subtipos devem ser substituíveis por seus tipos base.

ISP (Interface Segregation Principle): Interfaces específicas são superiores a interfaces genéricas e monolíticas.

DIP (Dependency Inversion Principle): Dependa de abstrações, e não de implementações concretas.

Coesão e Acoplamento: Buscar alta coesão e baixo acoplamento. Evitar o anti-padrão do God Object (classes gigantescas que centralizam regras de múltiplos domínios).

Encapsulamento: Evitar o uso indiscriminado de getters e setters que expõem o estado interno e criam modelos de domínio anêmicos.

Limitações da Aula

Por se tratar de um formato de áudio (palestra/aula gravada), não é possível visualizar diretamente os exemplos de código exibidos na tela ou nos slides originais, exigindo acompanhamento conceitual.

Perguntas de Revisão (Arquivo 1)

Pergunta: Por que o estudo das teorias musicais foi utilizado como analogia na introdução sobre Design Patterns?

Resposta: Para ilustrar que, assim na música é preciso entender harmonia e escalas em vez de apenas memorizar acordes isolados, em desenvolvimento de software é essencial dominar os princípios fundamentais de design e arquitetura antes de aplicar padrões de projeto de forma mecânica.

Pergunta: O que diz a teoria da "Janela Partida" (Windows Broken) quando aplicada ao desenvolvimento de software?

Resposta: Ela demonstra que ambientes de código desorganizados e sujos incentivam a deterioração contínua e o relaxamento dos padrões de qualidade pela equipe, assim como uma janela quebrada em um prédio ou carro atrai novos atos de vandalismo.

Pergunta: Qual é o principal risco apontado pelo princípio YAGNI (You Aren't Gonna Need It)?

Resposta: O risco de introduzir complexidade e engenharia excessiva (over-engineering) ao tentar prever e implementar funcionalidades para o futuro que podem nunca ser necessárias.

Pergunta: O que caracteriza o anti-padrão conhecido como God Object?

Resposta: É uma classe monolítica excessivamente grande que centraliza lógica e responsabilidades de múltiplos domínios, violando o princípio da responsabilidade única (SRP).

Pergunta: Segundo os dados apresentados na aula, qual é o impacto aproximado do débito técnico na rotina dos desenvolvedores?

Resposta: Os desenvolvedores gastam em média cerca de 42% do seu tempo semanal lidando com problemas técnicos, refatorações corretivas e débito técnico acumulado.

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
