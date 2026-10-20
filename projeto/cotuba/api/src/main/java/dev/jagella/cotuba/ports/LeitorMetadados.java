package dev.jagella.cotuba.ports;

import dev.jagella.cotuba.domain.Metadados;
import java.io.IOException;
import java.nio.file.Path;

public interface LeitorMetadados { Metadados ler(Path diretorio) throws IOException; }
