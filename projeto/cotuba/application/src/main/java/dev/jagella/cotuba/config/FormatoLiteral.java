package dev.jagella.cotuba.config;

import dev.jagella.cotuba.domain.Formato;
import jakarta.enterprise.util.AnnotationLiteral;

public final class FormatoLiteral extends AnnotationLiteral<FormatoGerador> implements FormatoGerador {
    private final Formato formato;
    public FormatoLiteral(Formato formato) { this.formato = formato; }
    @Override public Formato value() { return formato; }
}
