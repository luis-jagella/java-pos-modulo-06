package dev.jagella.cotuba.markdown;

import dev.jagella.cotuba.domain.CapituloBruto;
import dev.jagella.cotuba.ports.Capitulos;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CapitulosEmDisco implements Capitulos {
    public List<CapituloBruto> ler(Path diretorio) throws IOException {
        if (!Files.isDirectory(diretorio)) throw new IllegalArgumentException("Diretório inválido: " + diretorio);
        var capitulos = new ArrayList<CapituloBruto>();
        try (var arquivos = Files.list(diretorio)) {
            for (var arquivo : arquivos.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".md")).sorted().toList())
                capitulos.add(new CapituloBruto(arquivo.getFileName().toString(), Files.readString(arquivo, StandardCharsets.UTF_8)));
        }
        if (capitulos.isEmpty()) throw new IllegalArgumentException("Não foram encontrados capítulos .md");
        return List.copyOf(capitulos);
    }
}
