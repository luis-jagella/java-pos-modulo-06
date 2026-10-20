# Projeto — Vídeo 09: Plugins com ServiceLoader

> Tarefa 09. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas: projeto-09.vtt (Plugins SPI e ServiceLoader)
1. Conceitos Explicados

Mecanismo de Plugins: Capacidade de estender o comportamento de uma aplicação (exemplos citados: IDEs como VS Code e IntelliJ, Jenkins, GitHub Actions) adicionando novas funcionalidades sem precisar recompilar ou reinstalar o software principal.

Princípios SOLID Aplicados: Ênfase no Princípio Open-Closed (OCP) (aberto para extensão, fechado para modificação) e no Princípio da Substituição de Liskov (LSP).

ServiceLoader do Java: Mecanismo nativo embutido na JDK (java.util.ServiceLoader) que permite descobrir e carregar implementações de serviços de forma dinâmica a partir de JARs presentes no classpath.

Elementos de uma SPI (Service Provider Interface):

Service Provider Interface (o contrato/interface base).

Service Provider (a implementação concreta localizada em um projeto externo).

Service Loader (a classe da JDK responsável por carregar as implementações).

Exemplos Nativos e de Mercado que Utilizam SPI:

JDBC: java.sql.Driver e o gerenciador DriverManager.getConnection(), que localiza drivers de banco de dados (como MySQL Connector/J) através de arquivos descritores em META-INF/services/.

JPA / Jakarta Persistence: jakarta.persistence.spi.PersistenceProvider implementado por provedores como o Hibernate.

CDI / Injeção de Dependências: jakarta.enterprise.inject.se.SeContainerInitializer implementado pelo Weld.

Manipulação de HTML com Jsoup: Uso da biblioteca Jsoup para efetuar parsing de fragmentos de HTML (parseBodyFragment) e extrair o conteúdo de texto puro (.text()) removendo tags.

Processamento e Estatísticas de Texto: Contagem de ocorrências de palavras utilizando TreeMap para manter a ordenação alfabética, limpeza de pontuação por meio de expressões regulares (regex) e conversão de caracteres para letras minúsculas (lower case).

Empacotamento Maven (Fat JAR e Shade Plugin): Utilização do maven-shade-plugin para gerar um JAR executável contendo as dependências necessárias, aplicando exclusões de artefatos desnecessários para evitar conflitos no classpath.

2. Refatorações Realizadas

Criação da interface CotuBaPlugin contendo os métodos de gancho (hooks): aposRenderizacao(String html) e aposGeracao(Ebook ebook).

Inserção de chamadas ao ServiceLoader.load(CotuBaPlugin.class) dentro do renderizador de Markdown (logo após a renderização do HTML do capítulo) e dentro do CotuBaService (após a conclusão da geração do e-book).

Criação de dois projetos Maven externos e independentes: tema-css e estatisticas-ebook.

Implementação do plugin de tema CSS (tema-css): implementação de CotuBaPlugin, criação do arquivo descritor META-INF/services/br.com.unipds.cotuba.plugin.CotuBaPlugin com o nome qualificado da classe, e injeção de uma tag <style> com CSS básico nos capítulos.

Implementação do plugin de estatísticas (estatisticas-ebook): uso do Jsoup para extrair texto dos capítulos, limpeza de pontuação, conversão para minúsculas e contagem de palavras com TreeMap.

Configuração do maven-shade-plugin no projeto de estatísticas para gerar o fat JAR excluindo dependências transitivas indesejadas do Cotuba.

3. Problemas e Correções Mencionados

Nome Genérico de Interface: A interface inicial chamava-se apenas Plugin, sendo considerada pouco intuitiva. Correção: Renomeada para CotuBaPlugin para explicitar o contexto.

Obrigação de Implementar Métodos Indesejados: Como a interface possui os métodos aposRenderizacao e aposGeracao, plugins que focam em apenas um dos ganchos (como o de estatísticas) precisavam declarar o outro método vazio. O instrutor aponta isso como um ponto de melhoria para tarefas futuras.

Ausência do JAR no Repositório Local: Ao tentar compilar o projeto externo tema-css, o Maven falhou por não encontrar o JAR do Cotuba no repositório local. Correção: Execução prévia do comando mvn install no projeto Cotuba para publicá-lo em ~/.m2/repository.

Limpeza de Texto e Caracteres Indesejados: As palavras contadas inicialmente continham pontuações, parênteses, colchetes e emojis. Correção: Aplicação de limpeza por expressão regular baseada em espaços em branco e remoção de pontuação, além da conversão para minúsculas.

Formatação de Saída no Console: Falta de quebra de linha na impressão das estatísticas de palavras, corrigida com o uso de \n no System.out.printf.

4. Limitações do que Depende da Tela

O instrutor demonstra o fluxo abrindo múltiplas janelas do editor (IntelliJ) para gerenciar os projetos separados (Cotuba, tema-css, estatisticas-ebook), alternando entre terminais via linha de comando para executar comandos Maven (mvn clean package, mvn install, execução de JAR via classpath java -cp), visualizando o resultado visual dos arquivos gerados em leitores de ePub/PDF (Calibre e Apple Books) com o CSS aplicado, e inspecionando a listagem de contagem de palavras impressa no console.

5. Perguntas e Respostas

Q1: O que é uma Service Provider Interface (SPI) e qual é o papel do ServiceLoader do Java?

R: SPI é um contrato (interface) que define um serviço a ser implementado externamente; o ServiceLoader é a classe utilitária da JDK que vasculha o classpath (especificamente em diretórios META-INF/services/) para carregar dinamicamente essas implementações.

Q2: Cite exemplos de tecnologias ou especificações em Java citadas no vídeo que utilizam o mecanismo de SPI.

R: O JDBC (java.sql.Driver), a Jakarta Persistence (jakarta.persistence.spi.PersistenceProvider usado pelo Hibernate) e o CDI (SeContainerInitializer usado pelo Weld).

Q3: Qual é a principal vantagem de adotar um mecanismo de plugins baseado em SPI no Cotuba?

R: Permitir que parceiros de negócio ou desenvolvedores estendam o comportamento da aplicação (como adicionar temas CSS ou coletar estatísticas) sem a necessidade de modificar, recompilar ou reinstalar o código-fonte principal.

Q4: Por que foi necessário executar o comando mvn install no projeto Cotuba antes de compilar os projetos de plugins externos?

R: Porque o Maven gerencia dependências locais através do repositório do usuário (~/.m2/repository), e como o Cotuba não é uma biblioteca pública do repositório central, seu artefato precisava ser instalado localmente para ser reconhecido pelos projetos externos.

Q5: Qual biblioteca externa foi empregada no projeto de plugin para realizar o parsing de fragmentos de HTML e extração de texto?

R: A biblioteca Jsoup (através de Jsoup.parseBodyFragment() combinado com .text()).

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
