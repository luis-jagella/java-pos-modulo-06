package dev.jagella.cotuba.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Metadados(String titulo, String autor) {
    public Metadados {
        if (titulo == null || titulo.isBlank() || autor == null || autor.isBlank())
            throw new IllegalArgumentException("Título e autor são obrigatórios");
    }
}
