package dev.jagella.cotuba.markdown;

import dev.jagella.cotuba.domain.Metadados;
import dev.jagella.cotuba.ports.LeitorMetadados;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Properties;

@ApplicationScoped
public class MetadadosProperties implements LeitorMetadados {
    public Metadados ler(Path diretorio) throws IOException {
        var arquivo = diretorio.resolve("ebook.properties");
        var dados = new Properties();
        if (Files.exists(arquivo)) try (var leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) { dados.load(leitor); }
        return new Metadados(dados.getProperty("titulo", "Livro"), dados.getProperty("autor", "Autor"));
    }
}
