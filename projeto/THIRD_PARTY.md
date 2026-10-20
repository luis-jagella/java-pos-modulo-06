# Origem e dependências

Implementação de estudo própria, orientada pelos requisitos do [quadro da disciplina](https://trello.com/b/XamAmgsf/projeto-pratico-unipds-software-design-e-system-design). A base de comparação indicada pela aula é [unipds-projetos/modulo5-projeto-cotuba](https://github.com/unipds-projetos/modulo5-projeto-cotuba), commit consultado `5bff429e316dc23e610ee3948cc91594fbdcacb6`. Não foram copiados os arquivos da apostila nem republicadas mídias do curso.

Diferenças deliberadas: implementação independente, EPUB mínimo com ZIP/JDK (em vez de epublib), saída sem sobrescrita, tratamento de Markdown cru como texto e bloqueio de recursos externos no PDF. A estrutura Maven agrupa domínio, portas e SPI em `api`; as implementações de plugins continuam em projetos externos.

As dependências mantêm suas licenças originais. Especial atenção: **iText/pdfHTML usa licenciamento AGPL ou comercial**, conforme a opção aplicável. Não trate o JAR combinado como se todas as dependências fossem permissivas; confira os termos antes de distribuir ou oferecer uma versão como serviço. Este repositório não concede direitos sobre dependências de terceiros nem sobre o material da pós.

Referências oficiais: [iText licensing](https://itextpdf.com/how-buy/legal), [CommonMark Java](https://github.com/commonmark/commonmark-java), [Weld](https://weld.cdi-spec.org/), [jMolecules](https://github.com/xmolecules/jmolecules), [ArchUnit](https://www.archunit.org/), [jsoup](https://jsoup.org/).
