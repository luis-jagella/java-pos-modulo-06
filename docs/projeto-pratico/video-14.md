# Projeto — Vídeo 14: Módulos Maven e JPMS — parte 2

> Tarefa 13. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

Notas Detalhadas - Tarefa 13: Modularização Parte 2 (JPMS)
Conceitos Explicados

JPMS (Java Platform Module System): Sistema de modularização do Java (a partir do Java 9) baseado na definição do arquivo module-info.java na raiz de cada módulo, compilado para module-info.class.

Directivas do module-info:

requires: declaração de dependência de outros módulos.

exports: liberação explícita de pacotes específicos para outros módulos (garantindo encapsulamento forte em nível de pacote, mesmo que as classes sejam públicas).

opens: abertura de módulos ou pacotes para permitir acesso via reflection.

uses e provides: mecanismo moderno do JPMS (substituindo o antigo META-INF/services) para trabalhar com o padrão Service Provider Interface (SPI) e Service Loader.

Class Path vs. Module Path: Contraste entre a execução tradicional baseada em classpath (-cp ou -classpath) e a execução estrita baseada em module path (-p ou --module-path), que valida rigorosamente descritores, duplicidades e visibilidade.

Automatic-Module-Name: Nome de módulo atribuído automaticamente pelo JPMS a bibliotecas legadas sem module-info.java, baseado no nome do arquivo JAR ou em uma declaração prévia no manifesto (MANIFEST.MF) feita pelo autor da biblioteca.

Refatorações

Criação de arquivos module-info.java em múltiplos módulos do projeto Cotuba (domain, application, config, markdown, epub, pdf, html, cli, estatísticas-ebook, tema-plugin).

Ajuste de visibilidade estrutural exportando pacotes específicos (como ports.in, ports.out, DTOs e pacotes de suporte).

Problemas e Correções Realmente Mencionados

Problema: Falhas de compilação iniciais ao introduzir o module-info.java devido à invisibilidade de pacotes de outros módulos. Correção: Inclusão de diretivas exports explícitas para cada pacote necessário.

Problema: Bibliotecas de terceiros sem descritor de módulo ou nome automático (como o iText / HTML PDF) gerando nomes de módulo baseados unicamente no nome do JAR. Constatação: Identificado como uma prática deficiente por parte dos autores da biblioteca.

Problema: Erro de descritor de módulo inválido (Invalid module descriptor) ao rodar com o Module Path devido a entradas incorretas no META-INF/services da biblioteca kxml (utilizada internamente pelo epublib). Correção: Realização de um fork do projeto da biblioteca para limpar dependências antigas de XML e corrigir o manifesto/serviços.

Problema: Conflitos de pacotes duplicados ao gerar Fat Jars combinando código modularizado e bibliotecas legadas. Constatação: O formato Fat Jar entra em conflito com o conceito do JPMS de módulos isolados no runtime; a alternativa recomendada seria o uso do Maven Assembly Plugin para gerar pacotes organizados por diretórios de bibliotecas com scripts de execução (.sh/.bat), preservando o Module Path.

Problema: Falhas de reflection em frameworks baseados em CDI/Weld (como manipulação de instâncias e criação de proxies). Correção: Abertura dos módulos utilizando a diretiva opens.

Limitações do que Depende da Tela

O instrutor faz referência constante à visualização de elementos na tela da IDE, como a árvore de arquivos (src/main/java), o painel de External Libraries, os erros exibidos no console durante a compilação e o uso de recursos de autocompletar da IDE para preencher as dependências no module-info.java.

5 Perguntas com Respostas

O que substitui a declaração tradicional em META-INF/services no JPMS para o gerenciamento de serviços (Service Loader)?

Resposta: As diretivas uses e provides declaradas dentro do arquivo module-info.java.

O que acontece se uma classe for pública, mas o pacote que a contém não for explicitamente exportado no module-info.java?

Resposta: Ela não poderá ser importada nem utilizada por outros módulos, pois o JPMS impõe um encapsulamento forte em nível de pacote.

Por que frameworks de CDI (como o Weld) geram falhas em tempo de execução quando executados com o JPMS?

Resposta: Porque eles dependem fortemente de reflection, gerenciamento dinâmico de instâncias e criação de proxies, exigindo a abertura explícita com a diretiva opens.

Qual é o conflito conceitual entre o uso de um Fat Jar e o JPMS?

Resposta: O Fat Jar compacta todas as classes e recursos em um único arquivo JAR, descaracterizando o isolamento de módulos e os descritores (module-info.class) exigidos pelo Module Path no runtime.

O que é o Automatic-Module-Name?

Resposta: É uma especificação inserida no arquivo MANIFEST.MF de um JAR legado (sem module-info.java) que permite ao JPMS reconhecê-lo com um nome de módulo estável.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
