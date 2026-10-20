package dev.jagella.cotuba.domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

@AggregateRoot
public record Ebook(@Identity UUID id, Metadados metadados, Formato formato, List<Capitulo> capitulos) {
    public Ebook {
        Objects.requireNonNull(id); Objects.requireNonNull(metadados);
        Objects.requireNonNull(formato);
        capitulos = List.copyOf(capitulos);
        if (capitulos.isEmpty()) throw new IllegalArgumentException("Livro sem capítulos");
    }
    public static Builder builder() { return new Builder(); }
    public static final class Builder {
        private Metadados metadados;
        private Formato formato;
        private List<Capitulo> capitulos;
        public Builder metadados(Metadados valor) { metadados = valor; return this; }
        public Builder formato(Formato valor) { formato = valor; return this; }
        public Builder capitulos(List<Capitulo> valor) { capitulos = List.copyOf(valor); return this; }
        public Ebook build() { return new Ebook(UUID.randomUUID(), metadados, formato, capitulos); }
    }
}
