package dev.jagella.cotuba.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class EbookTest {
    @Test void protegeColecaoEBuilder() {
        var caps = new ArrayList<Capitulo>();
        caps.add(new Capitulo("01.md", "Introdução", "# Introdução", "<h1>Introdução</h1>"));
        var livro = Ebook.builder().metadados(new Metadados("Teste", "Autor")).formato(Formato.PDF).capitulos(caps).build();
        caps.clear();
        assertEquals(1, livro.capitulos().size());
        assertThrows(UnsupportedOperationException.class, () -> livro.capitulos().clear());
        var modificado = livro.capitulos().getFirst().comHtml("novo");
        assertNotEquals(modificado.html(), livro.capitulos().getFirst().html());
    }
    @Test void validaFormatoEMetadados() {
        assertEquals(Formato.EPUB, Formato.de("ePuB"));
        assertThrows(IllegalArgumentException.class, () -> Formato.de("mobi"));
        assertThrows(IllegalArgumentException.class, () -> new Metadados("", "Autor"));
        assertThrows(IllegalArgumentException.class, () -> Ebook.builder().metadados(new Metadados("T", "A")).formato(Formato.PDF).capitulos(java.util.List.of()).build());
    }
}
