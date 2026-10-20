package dev.jagella.cotuba.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.*;
import java.util.zip.ZipFile;
import static org.junit.jupiter.api.Assertions.*;

class CotubaIntegrationTest {
    @TempDir Path temp;
    private Path entrada() throws Exception {
        var dir = Files.createDirectory(temp.resolve("entrada"));
        Files.writeString(dir.resolve("02.md"), "# Segundo\n\nJava e arquitetura.");
        Files.writeString(dir.resolve("01.md"), "# Primeiro **capítulo**\n\nAcentuação e <script>alert(1)</script>.");
        Files.writeString(dir.resolve("ebook.properties"), "titulo=Prática & design\nautor=Luís\n");
        return dir;
    }
    private int gerar(Path dir, String formato, Path out) {
        return Main.executar(new String[]{"-d", dir.toString(), "-f", formato, "-o", out.toString()});
    }
    @Test void geraPdfComTexto() throws Exception {
        var out = temp.resolve("livro.pdf");
        assertEquals(0, gerar(entrada(), "pdf", out));
        try (var reader = new com.itextpdf.kernel.pdf.PdfReader(out.toString()); var pdf = new com.itextpdf.kernel.pdf.PdfDocument(reader)) {
            String texto = com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor.getTextFromPage(pdf.getPage(1));
            assertTrue(texto.contains("Prática & design"));
            assertTrue(texto.contains("Primeiro capítulo"));
        }
    }
    @Test void geraEpubComOrdemEMetadados() throws Exception {
        var out = temp.resolve("livro.epub");
        assertEquals(0, gerar(entrada(), "epub", out));
        try (var zip = new ZipFile(out.toFile())) {
            var mime = zip.entries().nextElement();
            assertEquals("mimetype", mime.getName());
            assertEquals(java.util.zip.ZipEntry.STORED, mime.getMethod());
            assertTrue(new String(zip.getInputStream(zip.getEntry("OEBPS/content.opf")).readAllBytes(), java.nio.charset.StandardCharsets.UTF_8).contains("Prática &amp; design"));
            for (var entries = zip.entries(); entries.hasMoreElements();) {
                var e = entries.nextElement();
                if (e.getName().endsWith(".xhtml") || e.getName().endsWith(".opf") || e.getName().endsWith(".xml")) {
                    var factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
                    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                    factory.newDocumentBuilder().parse(zip.getInputStream(e));
                }
            }
        }
    }
    @Test void geraSiteSemSobrescrever() throws Exception {
        var dir = entrada(); var out = temp.resolve("site");
        assertEquals(0, gerar(dir, "html", out));
        var index = Files.readString(out.resolve("index.html"));
        assertTrue(index.contains("href=\"001.html\""));
        assertTrue(index.indexOf("Primeiro") < index.indexOf("Segundo"));
        assertTrue(Files.readString(out.resolve("001.html")).contains("&lt;script&gt;"));
        var antes = Files.readString(out.resolve("001.html"));
        assertEquals(1, gerar(dir, "html", out));
        assertEquals(antes, Files.readString(out.resolve("001.html")));
    }
    @Test void preservaArquivoExistente() throws Exception {
        var out = temp.resolve("existente.pdf"); Files.writeString(out, "não apagar");
        assertEquals(1, gerar(entrada(), "pdf", out));
        assertEquals("não apagar", Files.readString(out));
    }
    @Test void rejeitaEntradaVaziaFormatoInvalidoEArgumentos() throws Exception {
        assertEquals(1, gerar(Files.createDirectory(temp.resolve("vazio")), "pdf", temp.resolve("vazio.pdf")));
        assertFalse(Files.exists(temp.resolve("vazio.pdf")));
        assertEquals(1, Main.executar(new String[]{"-f", "mobi"}));
        assertEquals(1, Main.executar(new String[]{"--inexistente"}));
        assertEquals(1, Main.executar(new String[]{"lixo"}));
        assertEquals(0, Main.executar(new String[]{"--help"}));
    }
}
