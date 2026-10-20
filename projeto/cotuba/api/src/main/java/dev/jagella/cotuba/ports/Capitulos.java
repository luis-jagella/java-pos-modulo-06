package dev.jagella.cotuba.ports;

import dev.jagella.cotuba.domain.CapituloBruto;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface Capitulos { List<CapituloBruto> ler(Path diretorio) throws IOException; }
