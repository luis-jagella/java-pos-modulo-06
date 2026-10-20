package dev.jagella.cotuba.domain;

import java.util.Objects;

public record CapituloBruto(String origem, String markdown) {
    public CapituloBruto { Objects.requireNonNull(origem); Objects.requireNonNull(markdown); }
}
