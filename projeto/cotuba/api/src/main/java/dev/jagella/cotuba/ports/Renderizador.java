package dev.jagella.cotuba.ports;

import dev.jagella.cotuba.domain.Capitulo;
import dev.jagella.cotuba.domain.CapituloBruto;

public interface Renderizador { Capitulo renderizar(CapituloBruto bruto); }
