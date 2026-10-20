package dev.jagella.cotuba.cli;

import dev.jagella.cotuba.usecase.GerarEbook;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Main {
    public static void main(String[] args) { System.exit(executar(args)); }

    public static int executar(String[] args) {
        boolean verboso = false;
        try {
            var parametros = new LeitorCli().ler(args);
            if (parametros == null) return 0;
            verboso = parametros.verboso();
            try (var container = SeContainerInitializer.newInstance().initialize()) {
                container.select(GerarEbook.class).get().gerar(parametros);
                System.out.println("Arquivo gerado com sucesso: " + parametros.destino());
            }
            return 0;
        } catch (Exception ex) {
            System.err.println("Falha: " + ex.getMessage());
            if (verboso) ex.printStackTrace(System.err);
            return 1;
        }
    }
}
