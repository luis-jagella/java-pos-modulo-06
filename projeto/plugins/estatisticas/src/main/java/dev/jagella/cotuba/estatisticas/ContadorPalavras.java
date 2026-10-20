package dev.jagella.cotuba.estatisticas;

import java.util.*;

public final class ContadorPalavras implements Iterable<Map.Entry<String, Integer>> {
    private final Map<String, Integer> contagem = new TreeMap<>();
    public void adicionar(String palavra) {
        if (palavra == null || palavra.isBlank()) throw new IllegalArgumentException("Palavra vazia");
        contagem.merge(palavra.toLowerCase(Locale.ROOT), 1, Integer::sum);
    }
    public int total() { return contagem.values().stream().mapToInt(Integer::intValue).sum(); }
    public Iterator<Map.Entry<String, Integer>> iterator() {
        return Collections.unmodifiableMap(new TreeMap<>(contagem)).entrySet().iterator();
    }
}
