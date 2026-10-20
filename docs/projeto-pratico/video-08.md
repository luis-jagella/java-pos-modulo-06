# Projeto — Vídeo 08: jMolecules e ArchUnit

> Tarefa 08. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas: projeto-08.vtt (jMolecules, ArchUnit e Arquitetura Hexagonal)
1. Conceitos Explicados

Bill of Materials (BOM) no Maven: Mecanismo para gerenciar versões de dependências de forma centralizada (semelhante ao funcionamento do Spring Boot), dispensando a declaração explícita de versões para cada dependência individual do jMolecules.

jMolecules: Biblioteca utilizada para anotar e expressar conceitos de Domain-Driven Design (DDD) diretamente no código Java, aplicando anotações como @AggregateRoot, @Entity, @ValueObject, @Service e @Repository.

Abstrações de IO e Vazamento de Infraestrutura: Explicação sobre o tipo Path (do Java NIO), que possui implementações específicas de sistema operacional/arquitetura (Unix Path, jrt interno do JDK, zip path para leitura de JARs/ZIPs). Destaca-se que Path é um detalhe de Entrada/Saída (IO) e não deve vazar para as entidades de domínio puras.

Trade-offs de System Design (Escalabilidade vs Memória): Discussão sobre o impacto de retornar byte[] gerados em memória em vez de gravar diretamente em arquivos no disco. Apresenta-se o risco de estouro de pilha (OutOfMemoryError) caso vários autores editem e-books pesados com imagens (ex: 10 usuários simultâneos gerando e-books de 2GB) concorrentemente.

Evolução da Arquitetura e Organização de Código: Transição de arquiteturas em camadas lógicas (estritas vs relaxadas, baseadas nos livros de Eric Evans de 2003 e Vaughn Vernon de 2013) para o Princípio da Inversão de Dependência (DIP) e a Arquitetura Hexagonal (Ports and Adapters).

Portas e Adaptadores: Definição de portas primárias (driving ports / in), portas secundárias (driven ports / out), adaptadores primários (primary adapters) e adaptadores secundários (secondary adapters).

Organização de Pacotes (Package by Layer vs Package by Component): Baseado nas referências de Simon Brown (modelo C4 e monólitos modulares). Utilização de arquivos package-info.java no Java para declarar anotações jMolecules a nível de pacote (ex: @Application, @PrimaryAdapter, @SecondaryPort).

Fitness Functions e ArchUnit: Conceito extraído de Fundamentals of Software Architecture sobre funções de aptidão automatizadas. O ArchUnit é utilizado para testar se o código-fonte cumpre as restrições arquiteturais definidas (estilo hexagonal, DDD, ausência de dependências cíclicas).

2. Refatorações Realizadas

Criação da branch git tarefa/08/jMolecules-Archunit.

Inclusão do BOM do jMolecules na seção <dependencyManagement> do arquivo pom.xml.

Anotação das classes de domínio com jMolecules: Ebook anotado com @AggregateRoot, Chapter e Markdown como @Entity, e EbookFormat como @ValueObject.

Remoção do atributo Path das entidades de domínio (Markdown, Ebook), substituindo por atributos textuais puros (como o nome do arquivo) e isolando a manipulação de caminhos/IO na camada de infraestrutura/geradores.

Reestruturação dos pacotes do projeto para atender à Arquitetura Hexagonal: pacotes para application, domain, cli (adapters/in), config/support, ports/out, e infra/adapters/out (geradores HTML, PDF, ePUB, repositório Markdown e leitor de propriedades).

Extração de interface para o caso de uso (CotuBaUseCases) a partir de CotuBaService para satisfazer as regras de portas de entrada (Primary Ports).

Adição de testes de arquitetura (ArchitectureTest) com ArchUnit validando regras do jMolecules, arquitetura hexagonal e ausência de ciclos (cycle checks / slices).

3. Problemas e Correções Mencionados

Vazamento de Infraestrutura no Domínio: A presença inicial de Path dentro de Markdown e Ebook misturava detalhes de IO com o domínio. Correção: Remoção de Path das entidades e passagem do arquivo de saída diretamente na camada de serviço/geração.

Problemas com Gerador de Código (Annotation Processor): O builder gerado automaticamente pelo jMolecules (Record Builder) gerava classes no pacote antigo até que fosse executado um mvn clean compile para recompilar os arquivos no novo pacote estruturado.

Visibilidade de Método na Classe Principal: O método executar() na classe Main (cli) não era público, o que impedia o acesso adequado; o problema foi contornado ajustando o pacote de teste ou a visibilidade.

Mensagens de Erro do ArchUnit: O instrutor apontou que as mensagens de erro geradas pelo jMolecules/ArchUnit contêm bastante ruído textual, mas apontam com precisão violações reais (ex: adaptadores primários acessando diretamente a camada de aplicação sem passar por uma porta de entrada).

Validação por Falhas Propositais: Demonstração do funcionamento das Fitness Functions forçando falhas de teste (ex: remover @Identity de uma entidade ou introduzir dependência cíclica intencional).

4. Limitações do que Depende da Tela

O instrutor e a colega conduzem a tarefa compartilhando a tela do ambiente de desenvolvimento (IntelliJ IDEA), navegando visualmente pela árvore de pacotes do projeto Maven, editando arquivos pom.xml e classes Java, visualizando logs de erro no terminal e inspecionando o arquivo PDF final gerado após a execução dos testes.

5. Perguntas e Respostas

Q1: Qual é o objetivo de incluir um Bill of Materials (BOM) do jMolecules no arquivo pom.xml?

R: Centralizar e gerenciar as versões das dependências do jMolecules no projeto Maven sem precisar declarar versões individuais para cada artefato (semelhante ao funcionamento do Spring Boot).

Q2: Por que o uso de Path (do Java NIO) foi removido das classes de domínio (Markdown e Ebook)?

R: Porque Path é uma abstração de Entrada/Saída (IO) e infraestrutura; mantê-lo nas entidades gerava vazamento de detalhes de infraestrutura para dentro do modelo de domínio puro.

Q3: O que são e para que servem os arquivos package-info.java no contexto do jMolecules?

R: São arquivos especiais de pacotes no Java utilizados para declarar anotações a nível de pacote (como @Application, @PrimaryAdapter, @SecondaryPort), permitindo que o jMolecules e o ArchUnit validem o estilo arquitetural.

Q4: O que o ArchUnit valida ao ser integrado com as regras do jMolecules no projeto?

R: Valida se o código segue regras estritas de arquitetura hexagonal e DDD (ex: adaptadores conversam apenas com portas, entidades possuem identificadores, agregados respeitam limites de associação por ID, e ausência de dependências cíclicas).

Q5: Qual é o risco de System Design associado a retornar arrays de bytes (byte[]) na geração de e-books em vez de gravar diretamente em arquivos no disco?

R: O risco de esgotamento de memória (OutOfMemoryError) em cenários de alta concorrência com múltiplos autores gerando e-books pesados simultaneamente (ex: arquivos de 2GB), pois todo o conteúdo precisaria ser mantido na memória RAM.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
