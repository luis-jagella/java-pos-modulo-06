package dev.jagella.cotuba.pdf;

import dev.jagella.cotuba.config.*;
import dev.jagella.cotuba.support.HtmlSeguro;
import dev.jagella.cotuba.domain.*;
import dev.jagella.cotuba.ports.Gerador;
import jakarta.enterprise.context.ApplicationScoped;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.styledxmlparser.resolver.resource.IResourceRetriever;
import java.io.*;
import java.net.URL;
import java.nio.file.*;

@ApplicationScoped
@FormatoGerador(Formato.PDF)
public class GeradorPdf implements Gerador {
    public void gerar(Ebook ebook, Path destino) throws IOException {
        var corpo = new StringBuilder("<h1>" + HtmlSeguro.escapar(ebook.metadados().titulo()) + "</h1><p>"
                + HtmlSeguro.escapar(ebook.metadados().autor()) + "</p>");
        for (var capitulo : ebook.capitulos()) corpo.append(capitulo.html());
        var config = new ConverterProperties().setResourceRetriever(new IResourceRetriever() {
            public InputStream getInputStreamByUrl(URL url) throws IOException { throw new IOException("Recursos externos desabilitados"); }
            public byte[] getByteArrayByUrl(URL url) throws IOException { throw new IOException("Recursos externos desabilitados"); }
        });
        // CREATE_NEW impede sobrescrever arquivos e seguir um link simbólico existente.
        try (var out = Files.newOutputStream(destino, StandardOpenOption.CREATE_NEW)) {
            HtmlConverter.convertToPdf(HtmlSeguro.documento(ebook.metadados().titulo(), corpo.toString()), out, config);
        }
    }
}
