package dev.jagella.cotuba.request;

import dev.jagella.cotuba.domain.Formato;
import java.nio.file.Path;

public record Parametros(Path diretorio, Formato formato, Path destino, boolean verboso) {}
