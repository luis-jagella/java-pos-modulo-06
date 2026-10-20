package dev.jagella.cotuba.estatisticas;

import dev.jagella.cotuba.domain.Ebook;
import dev.jagella.cotuba.spi.PluginGeracao;
import org.jsoup.Jsoup;

public class Estatisticas implements PluginGeracao {
    public void aposGeracao(Ebook livro) {
        var contador = new ContadorPalavras(); int caracteres = 0;
        for (var capitulo : livro.capitulos()) {
            var texto = Jsoup.parseBodyFragment(capitulo.html()).text();
            caracteres += texto.length();
            for (var palavra : texto.split("[^\\p{L}\\p{N}]+")) if (!palavra.isBlank()) contador.adicionar(palavra);
        }
        System.out.printf("Estatísticas: %d palavras, %d caracteres%n", contador.total(), caracteres);
        for (var entrada : contador) System.out.printf("%s: %d%n", entrada.getKey(), entrada.getValue());
    }
}
