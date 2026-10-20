package dev.jagella.cotuba.ports;

import dev.jagella.cotuba.domain.Ebook;
import java.io.IOException;
import java.nio.file.Path;

public interface Gerador { void gerar(Ebook ebook, Path destino) throws IOException; }
