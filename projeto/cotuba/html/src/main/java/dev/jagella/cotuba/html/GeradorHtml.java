package dev.jagella.cotuba.html;

import dev.jagella.cotuba.config.*;
import dev.jagella.cotuba.support.HtmlSeguro;
import dev.jagella.cotuba.domain.*;
import dev.jagella.cotuba.ports.Gerador;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Locale;

@ApplicationScoped
@FormatoGerador(Formato.HTML)
public class GeradorHtml implements Gerador {
    public void gerar(Ebook ebook, Path destino) throws IOException {
        // Não reutiliza nem apaga um diretório existente.
        Files.createDirectory(destino);
        var sumario = new StringBuilder("<h1>" + HtmlSeguro.escapar(ebook.metadados().titulo()) + "</h1><p>"
                + HtmlSeguro.escapar(ebook.metadados().autor()) + "</p><ol>");
        int numero = 1;
        for (var capitulo : ebook.capitulos()) {
            var nome = String.format(Locale.ROOT, "%03d.html", numero++);
            Files.writeString(destino.resolve(nome), HtmlSeguro.documento(capitulo.titulo(), capitulo.html()),
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            sumario.append("<li><a href=\"").append(nome).append("\">")
                    .append(HtmlSeguro.escapar(capitulo.titulo())).append("</a></li>");
        }
        sumario.append("</ol>");
        Files.writeString(destino.resolve("index.html"), HtmlSeguro.documento(ebook.metadados().titulo(), sumario.toString()),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
    }
}
