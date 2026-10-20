package dev.jagella.cotuba.tema;

import dev.jagella.cotuba.spi.PluginRenderizacao;

public class TemaCss implements PluginRenderizacao {
    public String aposRenderizacao(String html) { return "<style>h1 { color: #174ea6; } body { font-family: sans-serif; }</style>" + html; }
}
