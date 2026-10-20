module cotuba.markdown {
    requires cotuba.api;
    requires weld.se.shaded;
    requires org.commonmark;
    opens dev.jagella.cotuba.markdown to weld.se.shaded;
}
