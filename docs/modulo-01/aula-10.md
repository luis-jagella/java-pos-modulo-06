# Aula 10 — Design Patterns — Visão Prática

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado da Aula: Design Patterns na Prática e Princípios Aplicados
### 1. Introdução e Motores de Injeção de Dependência

A aula inicia com uma visão geral sobre Design Patterns (padrões de projeto), ressaltando que grande parte do material pode ser estudada utilizando Java puro como base. No mundo real do desenvolvimento corporativo, utilizam-se motores robustos de gestão de dependência, sendo os principais:

Spring Framework: Utiliza suas próprias anotações e escopos configurados.

CDI (Contexts and Dependency Injection): Padrão amplamente utilizado no ecossistema Java EE/Jakarta EE.

Micronaut: Outro framework citado na discussão sobre injeção. Os nomes próprios e a evolução do suporte mencionados na análise automática não foram confirmados; não inferir compatibilidade de versões a partir do resumo.

### 2. O Padrão Singleton

O padrão mais clássico abordado é o Singleton.

Conceito: Garante que uma classe tenha apenas uma instância e forneça um ponto global de acesso a ela.

Uso prático: No contexto de frameworks modernos, definir um escopo de aplicação (como @ApplicationScoped) gerencia essa unicidade automaticamente.

Diferença de comportamento: Enquanto abordagens tradicionais exigem cuidados com concorrência na criação manual da instância, os frameworks tratam isso de forma controlada. No CDI, por padrão, os recursos costumam trabalhar de forma lazy (preguiçosa), sendo instanciados apenas no momento em que são acionados pela primeira vez, embora existam formas de configurar o carregamento antecipado (eager).

### 3. O Padrão Strategy

O padrão Strategy foi profundamente discutido como uma das ferramentas mais importantes para eliminar grandes volumes de estruturas condicionais aninhadas (if-else ou switch-case) no código.

O Problema: A criação de serviços de pagamento (ex: PaymentService) cheios de condições para verificar se o pagamento é via PayPal, cartão de crédito, boleto, etc., viola claramente o princípio da responsabilidade única e o princípio Aberto/Fechado (OCP - Open/Closed Principle), pois qualquer inclusão de nova forma de pagamento exige modificar a classe principal.

A Solução: Extração de interfaces (ex: PaymentService separado em PaymentProcessor) e criação de implementações específicas para cada regra (ex: PayPalPaymentService, CreditCardPaymentService).

Ferramentas e Mecanismos: Utilização de enums (enum), qualificadores do CDI (@Qualifier), anotações customizadas ou fábricas de métodos para selecionar dinamicamente qual estratégia injetar ou invocar em tempo de execução. Isso torna o código altamente extensível e fácil de testar unitariamente.

### 4. O Padrão Observer

O padrão Observer foi apresentado como uma alternativa elegante para desacoplar componentes através de eventos.

Contexto: Em vez de um serviço principal acionar diretamente múltiplos subsistemas de forma síncrona e fortemente acoplada (como em um sistema de notícias que precisa notificar jornais, revistas e mídias sociais diretamente), utiliza-se um mecanismo orientado a eventos.

Funcionamento: O componente dispara um evento (fire()), e os observadores interessados capturam essa alteração de estado. No CDI, isso é nativo e permite processamentos síncronos ou assíncronos, reduzindo drasticamente o acoplamento entre classes.

### 5. O Padrão Decorator

O uso de Decorators foi abordado como uma solução superior à herança tradicional para adicionar comportamentos a objetos de forma dinâmica.

Conceito: Envolver uma classe existente com outra que implementa a mesma interface, adicionando novas responsabilidades (por exemplo, adicionar o envio de e-mails ou logs em uma tarefa sem modificar a classe original do trabalhador).

Exemplo prático: A hierarquia entre Worker, Programmer e Manager, onde o gerente decora o comportamento do trabalhador delegando tarefas e adicionando novas rotinas gerenciais.

### 6. Limitações e Observações Técnicas

Limitações visuais: Durante a aula, o instrutor digita códigos ao vivo. Algumas ferramentas de autocompletar (como o GitHub Copilot) geram sugestões parciais ou ligeiramente imprecisas ("meia-boca"), exigindo correções manuais em tempo real de sintaxe e retornos de métodos.

Complexidade acidental: O instrutor enfatiza que não se deve focar excessivamente na ferramenta em si, mas sim nos conceitos fundamentais por trás dos padrões para aplicá-los organicamente quando o fluxo de código exigir.

Perguntas de Revisão (Aula 10)

Pergunta: Qual é o principal problema no código resolvido pelo padrão Strategy?

Resposta: A proliferação de grandes blocos condicionais (if-else ou switch-case), que violam o princípio Aberto/Fechado (OCP) e acumulam múltiplas responsabilidades na mesma classe.

Pergunta: Como o escopo Singleton se comporta por padrão em frameworks como o CDI?

Resposta: Não confundir Singleton com escopo de aplicação. Em CDI, o ciclo de vida depende do escopo e dos proxies; `@ApplicationScoped` e `@Singleton` são distintos. Consulte a revisão técnica antes de generalizar inicialização lazy para todo escopo.

Pergunta: Qual é a vantagem de utilizar o padrão Observer em comparação com chamadas diretas entre serviços?

Resposta: O Observer promove o desacoplamento de componentes através de eventos, permitindo que um emissor dispare uma ação sem precisar conhecer explicitamente quem são os consumidores ou receptores dessa informação.

Pergunta: Por que o padrão Decorator é frequentemente preferido em relação à herança em Java?

Resposta: Porque evita hierarquias de herança complexas e rígidas, permitindo adicionar ou modificar comportamentos em tempo de execução através da composição de objetos que compartilham a mesma interface.

Pergunta: O que são os qualificadores (qualifiers) no contexto de injeção de dependência com CDI?

Resposta: São anotações customizadas utilizadas para resolver ambiguidades quando existem múltiplas implementações para a mesma interface, permitindo que o framework saiba exatamente qual estratégia injetar.

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
