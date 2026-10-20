package dev.jagella.cotuba.estatisticas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContadorPalavrasTest {
    @Test void encapsulaMapaEEntregaSnapshotImutavel() {
        var contador = new ContadorPalavras();
        contador.adicionar("Java"); contador.adicionar("java");
        var snapshot = contador.iterator();
        contador.adicionar("Arquitetura");
        var entrada = snapshot.next();
        assertEquals("java", entrada.getKey()); assertEquals(2, entrada.getValue());
        assertFalse(snapshot.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> entrada.setValue(-10));
        assertThrows(UnsupportedOperationException.class, snapshot::remove);
        assertThrows(IllegalArgumentException.class, () -> contador.adicionar(" "));
        assertEquals(3, contador.total());
    }
}
