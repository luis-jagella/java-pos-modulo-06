# Projeto — Vídeo 13: Módulos Maven e JPMS — parte 1

> Tarefa 13. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas
Conceitos Explicados

Modularização em Java: Divisão de um monólito em múltiplos módulos para promover encapsulamento, separação de responsabilidades e independência de compilação.

Módulos Maven vs. JPMS (Java Platform Module System): Diferença entre a modularização estrutural gerenciada pelo Maven (múltiplos módulos/projetos pai-filho a partir do Java 4/modernos) e o sistema de módulos nativo da plataforma Java introduzido no Java 9 (module-info.java).

Princípios de Design de Componentes/Pacotes (Robert C. Martin / Clean Architecture):

Reuse/Release Equivalence Principle (REP): A granularidade do reuso é a granularidade do release (você consome o JAR inteiro).

Common Reuse Principle (CRP): Classes em um componente são reutilizadas juntas.

Common Closure Principle (CCP): O equivalente ao Single Responsibility para componentes (agrupar classes que mudam pelo mesmo motivo).

Acyclic Dependencies Principle (ADP): Proibição de ciclos de dependência entre componentes ou módulos.

Modularidade vs. Microsserviços: Contraste entre sistemas distribuídos (interprocesso via rede) e monólitos modulares (intraprocesso na mesma JVM), destacando que a modularidade trata de organização e responsabilidade, não apenas de estética.

Visibilidade de Pacotes no Java Tradicional (ClassPath): Limitação do classpath tradicional onde qualquer classe pública é visível para todo o projeto, dificultando o isolamento de componentes internos em frameworks.

Refatorações

Conversão do projeto monolítico em um projeto multi-módulos Maven estruturado com um POM pai (packaging pom).

Criação dos submódulos: cotuba-domain, cotuba-application, cotuba-cli, cotuba-plugin, cotuba-config, cotuba-markdown, cotuba-pdf, cotuba-epub, cotuba-html e plugins de tema/estatísticas.

Migração física de diretórios e classes para cada submódulo correspondente.

Ajuste de dependências transitivas no Maven e remoção de acoplamentos indevidos (ex: o domínio deixando de depender de bibliotecas externas de infraestrutura).

Configuração do empacotamento fat-jar no módulo de linha de comando (cotuba-cli) utilizando Service Loader para carregar os plugins.

Problemas e Correções Mencionado(s)

Problema: O plugin de tema dependia diretamente do serviço principal (cotuba-service), quebrando princípios de arquitetura e permitindo acesso a classes internas indevidas.

Correção: Isolação do contrato de plugins em um módulo dedicado (cotuba-plugin).

Problema: Presença de ciclos de dependência entre pacotes (ex: domínio tentando instanciar a classe Main).

Correção: O Maven e os testes de arquitetura detectaram imediatamente a referência cíclica e interromperam a compilação.

Problema: Falta de arquivos de configuração do CDI (META-INF/services ou beans.xml) nos novos módulos isolados, gerando erros de injeção de dependência (Unsatisfied dependency types) em tempo de execução.

Correção: Distribuição correta dos arquivos de configuração e metadados de serviços para os submódulos apropriados.

Problema: Dependências globais declaradas no POM pai poluíam todos os submódulos (inclusive o domínio puro).

Correção: Limpeza do POM pai e alocação pontual das dependências estritas apenas nos módulos que realmente as utilizam.

Limitações do que Depende da Tela

Durante a refatoração ao vivo dos módulos Maven, ocorreram erros de compilação intermitentes na IDE e problemas com versões antigas em cache no diretório local .m2, exigindo comandos manuais de limpeza (mvn clean compile, mvn install) e ajustes de dependências transitivas que não podiam ser visualizados integralmente sem a execução do terminal.

5 Perguntas com Respostas

Qual é a principal diferença prática abordada entre módulos Maven e o JPMS?

Resposta: Os módulos Maven organizam o projeto em múltiplos artefatos (JARs) e controlam a árvore de compilação/dependências no build, enquanto o JPMS (introduzido no Java 9) atua no nível da JVM controlando a visibilidade estrita de pacotes em tempo de execução.

O que dita o Princípio de Equivalência Reuso/Release (REP) formulado por Robert C. Martin?

Resposta: Que a granularidade do reuso é a granularidade do release; ou seja, não é possível reutilizar apenas uma fração de uma biblioteca ou JAR, você consome o artefato como um todo.

Por que o Maven interrompe a compilação quando detecta uma dependência cíclica entre módulos?

Resposta: Porque dependências cíclicas violam o Princípio das Dependências Acíclicas (ADP), gerando forte acoplamento e impossibilitando a ordenação lógica de compilação.

Qual é o papel do módulo cotuba-cli na arquitetura multi-módulos final?

Resposta: Ele atua como o componente concreto Main, dependendo dos demais módulos de infraestrutura, aplicação e domínio para consolidar a execução e gerar o fat-jar executável.

O que motivou a distribuição de arquivos de configuração (como META-INF/services ou beans) para os submódulos após a quebra em multi-módulos?

Resposta: O contêiner CDI precisava que cada módulo isolado explicitasse suas configurações e serviços para conseguir encontrá-los e injetá-los corretamente no classpath.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
