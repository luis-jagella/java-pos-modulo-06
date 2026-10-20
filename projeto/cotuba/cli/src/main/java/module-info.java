module cotuba.cli {
    requires java.xml;
    requires cotuba.application;
    requires cotuba.markdown;
    requires cotuba.pdf;
    requires cotuba.epub;
    requires cotuba.html;
    requires org.apache.commons.cli;
    exports dev.jagella.cotuba.cli;
}
