module cotuba.pdf {
    requires cotuba.application;
    requires html2pdf;
    requires styled.xml.parser;
    opens dev.jagella.cotuba.pdf to weld.se.shaded;
}
