package dev.jagella.cotuba.cli;

import dev.jagella.cotuba.request.Parametros;
import dev.jagella.cotuba.domain.Formato;
import org.apache.commons.cli.*;
import java.nio.file.Path;

public class LeitorCli {
    private final Options options = new Options().addOption("d", "dir", true, "Diretório dos Markdown")
            .addOption("f", "format", true, "pdf, epub ou html")
            .addOption("o", "output", true, "Novo arquivo/diretório de saída")
            .addOption("v", "verbose", false, "Detalhar erros").addOption("h", "help", false, "Ajuda");
    public Parametros ler(String[] args) throws ParseException {
        var cmd = new DefaultParser().parse(options, args);
        if (!cmd.getArgList().isEmpty()) throw new IllegalArgumentException("Argumentos inesperados: " + cmd.getArgList());
        if (cmd.hasOption("help")) { ajuda(); return null; }
        var formato = Formato.de(cmd.getOptionValue("format", "pdf"));
        return new Parametros(Path.of(cmd.getOptionValue("dir", ".")), formato,
                Path.of(cmd.getOptionValue("output", "book." + formato.name().toLowerCase(java.util.Locale.ROOT))), cmd.hasOption("verbose"));
    }
    public void ajuda() { new HelpFormatter().printHelp("cotuba", options); }
}
