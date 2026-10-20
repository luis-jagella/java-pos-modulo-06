package dev.jagella.cotuba.domain;

import java.util.Objects;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public record Capitulo(@Identity String origem, String titulo, String markdown, String html) {
    public Capitulo {
        Objects.requireNonNull(origem); Objects.requireNonNull(titulo);
        Objects.requireNonNull(markdown); Objects.requireNonNull(html);
    }
    public Capitulo comHtml(String novoHtml) { return new Capitulo(origem, titulo, markdown, novoHtml); }
}
