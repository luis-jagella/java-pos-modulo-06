module cotuba.application {
    requires transitive cotuba.api;
    requires transitive weld.se.shaded;
    exports dev.jagella.cotuba.application;
    exports dev.jagella.cotuba.config;
    exports dev.jagella.cotuba.support;
    opens dev.jagella.cotuba.application to weld.se.shaded;
    uses dev.jagella.cotuba.spi.PluginRenderizacao;
    uses dev.jagella.cotuba.spi.PluginGeracao;
}
