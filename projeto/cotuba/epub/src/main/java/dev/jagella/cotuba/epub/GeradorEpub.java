package dev.jagella.cotuba.epub;

import dev.jagella.cotuba.config.*;
import dev.jagella.cotuba.support.HtmlSeguro;
import dev.jagella.cotuba.domain.*;
import dev.jagella.cotuba.ports.Gerador;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.zip.*;

@ApplicationScoped
@FormatoGerador(Formato.EPUB)
public class GeradorEpub implements Gerador {
    private static final String MIME = "application/epub+zip";

    public void gerar(Ebook livro, Path destino) throws IOException {
        try (var zip = new ZipOutputStream(Files.newOutputStream(destino, StandardOpenOption.CREATE_NEW))) {
            // EPUB exige mimetype como primeira entrada sem compressão.
            byte[] mime = MIME.getBytes(StandardCharsets.US_ASCII);
            var crc = new CRC32(); crc.update(mime);
            var entry = new ZipEntry("mimetype"); entry.setMethod(ZipEntry.STORED);
            entry.setSize(mime.length); entry.setCompressedSize(mime.length); entry.setCrc(crc.getValue());
            zip.putNextEntry(entry); zip.write(mime); zip.closeEntry();
            escrever(zip, "META-INF/container.xml", "<?xml version=\"1.0\"?><container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\"><rootfiles><rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/></rootfiles></container>");
            var manifest = new StringBuilder(); var spine = new StringBuilder(); var nav = new StringBuilder();
            int numero = 1;
            for (var capitulo : livro.capitulos()) {
                String id = "cap" + numero++, arquivo = id + ".xhtml";
                manifest.append("<item id=\"").append(id).append("\" href=\"").append(arquivo).append("\" media-type=\"application/xhtml+xml\"/>");
                spine.append("<itemref idref=\"").append(id).append("\"/>");
                nav.append("<li><a href=\"").append(arquivo).append("\">").append(HtmlSeguro.escapar(capitulo.titulo())).append("</a></li>");
                escrever(zip, "OEBPS/" + arquivo, xhtml(capitulo.titulo(), capitulo.html()));
            }
            escrever(zip, "OEBPS/nav.xhtml", xhtml("Sumário", "<nav xmlns:epub=\"http://www.idpf.org/2007/ops\" epub:type=\"toc\"><h1>Sumário</h1><ol>" + nav + "</ol></nav>"));
            escrever(zip, "OEBPS/content.opf", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><package xmlns=\"http://www.idpf.org/2007/opf\" version=\"3.0\" unique-identifier=\"id\"><metadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\"><dc:identifier id=\"id\">urn:uuid:" + livro.id()
                    + "</dc:identifier><dc:title>" + HtmlSeguro.escapar(livro.metadados().titulo()) + "</dc:title><dc:creator>" + HtmlSeguro.escapar(livro.metadados().autor())
                    + "</dc:creator><dc:language>pt-BR</dc:language><meta property=\"dcterms:modified\">" + java.time.Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS)
                    + "</meta></metadata><manifest><item id=\"nav\" href=\"nav.xhtml\" media-type=\"application/xhtml+xml\" properties=\"nav\"/>" + manifest + "</manifest><spine>" + spine + "</spine></package>");
        }
    }
    private static String xhtml(String titulo, String corpo) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"pt-BR\"><head><title>"
                + HtmlSeguro.escapar(titulo) + "</title></head><body>" + corpo + "</body></html>";
    }
    private static void escrever(ZipOutputStream zip, String nome, String conteudo) throws IOException {
        zip.putNextEntry(new ZipEntry(nome)); zip.write(conteudo.getBytes(StandardCharsets.UTF_8)); zip.closeEntry();
    }
}
