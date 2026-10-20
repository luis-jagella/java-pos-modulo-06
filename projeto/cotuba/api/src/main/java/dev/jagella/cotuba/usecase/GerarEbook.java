package dev.jagella.cotuba.usecase;

import dev.jagella.cotuba.domain.Ebook;
import dev.jagella.cotuba.request.Parametros;
import java.io.IOException;

@org.jmolecules.architecture.hexagonal.PrimaryPort
public interface GerarEbook { Ebook gerar(Parametros parametros) throws IOException; }
