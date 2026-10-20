package dev.jagella.cotuba.domain;

import java.util.Locale;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum Formato {
    PDF, EPUB, HTML;

    public static Formato de(String valor) {
        try { return valueOf(valor.toUpperCase(Locale.ROOT)); }
        catch (IllegalArgumentException ex) { throw new IllegalArgumentException("Formato inválido: " + valor); }
    }
}
