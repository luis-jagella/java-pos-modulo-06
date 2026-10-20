# Projeto prático — Cotuba / Cotubify

Branch: `projeto-pratico`. Implementação de estudo própria, orientada pelas 14 tarefas da disciplina. As [15 aulas práticas](../docs/projeto-pratico/README.md) têm notas separadas na `main`.

## O que foi implementado

- CLI para converter capítulos Markdown ordenados em PDF, EPUB e site HTML com índice.
- Metadados UTF-8 em `ebook.properties`, domínio imutável, Builder e cópias defensivas.
- DIP e injeção por construtor com CDI/Weld; seleção de geradores por qualificador e `AnnotationLiteral`.
- jMolecules, testes de DDD, regras hexagonais, ausência de ciclos e domínio sem dependências de I/O/framework.
- SPI segregada por gancho; plugins externos de tema CSS e estatísticas. Contador encapsula um mapa por composição.
- Sete módulos Maven com descritores JPMS; pacotes concretos dos geradores não são exportados.
- [C4 e três ADRs](arquitetura/README.md) para escala e resiliência do Cotubify. São modelagem, **não** API web, banco, broker ou gateway reais.

## Organização

| Módulo | Responsabilidade |
| --- | --- |
| `cotuba/api` | Domínio, DTO de entrada, portas e contratos de plugins |
| `cotuba/application` | Caso de uso, qualificadores e suporte de HTML |
| `cotuba/markdown` | Arquivos, propriedades e CommonMark |
| `cotuba/pdf`, `cotuba/epub`, `cotuba/html` | Implementações dos formatos |
| `cotuba/cli` | Bootstrap CDI, argumentos e JAR executável |
| `plugins/tema-css`, `plugins/estatisticas` | Projetos Maven independentes do reactor Cotuba |

O domínio tem anotações DDD, mas não usa `Path`, CDI ou bibliotecas de formatos. O caminho de saída pertence à aplicação e é passado ao gerador. Como a resolução prática evolui para portas e adaptadores, usamos `ensureHexagonal()` em vez da regra clássica de camadas, cuja direção de dependências é diferente.

## Pré-requisitos e build

Java **25** e Maven **3.9+** no terminal. Confira `java -version` e `mvn -version`; ambos precisam apontar para a JDK correta. Não basta ter uma JRE ou estar com a JDK 17 ativa.

Todos os comandos abaixo partem da raiz do repositório:

```bash
mvn -B -ntp -f projeto/cotuba/pom.xml clean install
mvn -B -ntp -f projeto/plugins/tema-css/pom.xml clean verify
mvn -B -ntp -f projeto/plugins/estatisticas/pom.xml clean verify
```

O `install` publica os contratos no repositório Maven **local**, para os projetos externos dos plugins; não publica artefatos na internet.

## Executar sem plugins

```bash
java -jar projeto/cotuba/cli/target/cotuba-cli-1.0-SNAPSHOT.jar --help
java -jar projeto/cotuba/cli/target/cotuba-cli-1.0-SNAPSHOT.jar -d projeto/exemplo -f pdf -o projeto/cotuba/cli/target/exemplo.pdf
java -jar projeto/cotuba/cli/target/cotuba-cli-1.0-SNAPSHOT.jar -d projeto/exemplo -f epub -o projeto/cotuba/cli/target/exemplo.epub
java -jar projeto/cotuba/cli/target/cotuba-cli-1.0-SNAPSHOT.jar -d projeto/exemplo -f html -o projeto/cotuba/cli/target/exemplo-site
```

`-d/--dir` seleciona os Markdown; `-f/--format` aceita `pdf`, `epub` e `html`; `-o/--output` define o destino; `-v/--verbose` detalha falhas. Padrões: diretório atual, PDF e `book.<formato>`. O destino precisa ser **novo**; a CLI recusa arquivos e diretórios existentes. Para rodar novamente, escolha outro destino. Erros retornam código 1, sucesso/ajuda código 0.

## Plugins via classpath — Windows PowerShell

```powershell
java -cp 'projeto/cotuba/cli/target/cotuba-cli-1.0-SNAPSHOT.jar;projeto/plugins/tema-css/target/tema-css-1.0-SNAPSHOT.jar;projeto/plugins/estatisticas/target/estatisticas-1.0-SNAPSHOT-all.jar' dev.jagella.cotuba.cli.Main -d projeto/exemplo -f html -o projeto/cotuba/cli/target/site-com-plugins
```

Em Linux/macOS, substitua `;` por `:` no classpath. `java -jar` ignora essa composição; por isso a classe principal é chamada explicitamente. O CSS aparece nos capítulos e as estatísticas no console. Plugins executam código com os mesmos privilégios da CLI: só carregue JARs confiáveis.

## JPMS de verdade

O fat JAR é uma conveniência de classpath e **não preserva os limites de módulos no runtime**. Para executar com encapsulamento JPMS, use os JARs separados:

```bash
mvn -q -f projeto/cotuba/pom.xml -pl cli dependency:copy-dependencies -DincludeScope=runtime -Dmdep.overWriteSnapshots=true
mvn -q -f projeto/plugins/estatisticas/pom.xml dependency:copy-dependencies -DincludeScope=runtime
```

PowerShell (uma linha):

```powershell
java --module-path 'projeto/cotuba/cli/target/dependency;projeto/cotuba/cli/target/original-cotuba-cli-1.0-SNAPSHOT.jar;projeto/plugins/tema-css/target/tema-css-1.0-SNAPSHOT.jar;projeto/plugins/estatisticas/target/estatisticas-1.0-SNAPSHOT.jar;projeto/plugins/estatisticas/target/dependency' --module cotuba.cli/dev.jagella.cotuba.cli.Main -d projeto/exemplo -f html -o projeto/cotuba/cli/target/site-jpms
```

Em Linux/macOS, use `:`. Não inclua o JAR `-all` dos plugins nem o fat JAR da CLI nesse module path. Os plugins declaram `provides`; a aplicação declara `uses`. Os arquivos `META-INF/services` são mantidos para compatibilidade com a execução por classpath. Aberturas de pacotes para o Weld são pontuais; os pacotes de implementação não são exportados.

## Verificação reproduzível

Após os três builds:

```bash
bash projeto/scripts/smoke.sh
```

No Windows, use Git Bash com a JDK 25 e Maven no PATH. O script gera em uma pasta temporária nova, verifica PDF/EPUB/HTML, plugins nos dois modos e uma **compilação que deve falhar** ao tentar importar o gerador PDF não exportado. A fixture fica fora dos módulos Maven de produção.

Os testes cobrem imutabilidade, validação, acentos, ordem de capítulos, XML interno do EPUB, preservação do destino, DDD, hexagonal e ciclos. Uma entidade propositalmente inválida comprova que a regra DDD detecta falta de identidade. O [CI](../.github/workflows/java.yml) repete build e smoke tests em Linux.

## Limites conhecidos

- EPUB mínimo, sem empacotamento de imagens/fontes nem declaração de conformidade por EPUBCheck. Testamos ZIP/XML e conteúdo, não todos os leitores.
- PDF sem carregamento de recursos externos; o objetivo é estudar design, não reproduzir toda a diagramação editorial.
- Markdown cru é escapado e URLs inseguras são filtradas, mas a CLI não é um sandbox para fontes e plugins não confiáveis.
- Em falha de escrita, pode restar uma saída parcial; nada é apagado recursivamente. Revise o destino e use outro nome para tentar novamente.
- Avisos de módulos automáticos, SLF4J sem provider e APIs `Unsafe` de dependências não são falhas do build. Não os suprima escondendo testes.
- Consulte [origem e licenças de terceiros](THIRD_PARTY.md), especialmente o licenciamento do iText, antes de distribuir um aplicativo combinado.

## Validação desta entrega

Veja [VALIDACAO.md](VALIDACAO.md) para os comandos efetivamente executados e os resultados. Conteúdo de avaliação do portal não foi respondido nem submetido.
