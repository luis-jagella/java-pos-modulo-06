module cotuba.estatisticas {
    requires cotuba.api;
    requires org.jsoup;
    provides dev.jagella.cotuba.spi.PluginGeracao with dev.jagella.cotuba.estatisticas.Estatisticas;
}
