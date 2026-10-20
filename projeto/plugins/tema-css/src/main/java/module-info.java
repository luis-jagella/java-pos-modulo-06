module cotuba.tema {
    requires cotuba.api;
    provides dev.jagella.cotuba.spi.PluginRenderizacao with dev.jagella.cotuba.tema.TemaCss;
}
