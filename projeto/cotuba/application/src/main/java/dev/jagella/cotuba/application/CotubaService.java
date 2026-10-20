package dev.jagella.cotuba.application;

import dev.jagella.cotuba.domain.*;
import dev.jagella.cotuba.ports.*;
import dev.jagella.cotuba.spi.*;
import dev.jagella.cotuba.config.FormatoLiteral;
import dev.jagella.cotuba.request.Parametros;
import dev.jagella.cotuba.usecase.GerarEbook;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ServiceLoader;
import org.jmolecules.ddd.annotation.Service;

@Service
@ApplicationScoped
public class CotubaService implements GerarEbook {
    private final Capitulos repositorio;
    private final LeitorMetadados metadados;
    private final Renderizador renderizador;
    private final Instance<Gerador> geradores;

    @Inject
    public CotubaService(Capitulos repositorio, LeitorMetadados metadados, Renderizador renderizador,
                         @Any Instance<Gerador> geradores) {
        this.repositorio = repositorio; this.metadados = metadados;
        this.renderizador = renderizador; this.geradores = geradores;
    }

    public Ebook gerar(Parametros parametros) throws IOException {
        var capitulos = new ArrayList<Capitulo>();
        var plugins = ServiceLoader.load(PluginRenderizacao.class).stream().map(ServiceLoader.Provider::get).toList();
        for (var bruto : repositorio.ler(parametros.diretorio())) {
            var capitulo = renderizador.renderizar(bruto);
            for (var plugin : plugins)
                capitulo = capitulo.comHtml(Objects.requireNonNull(plugin.aposRenderizacao(capitulo.html()), "Plugin retornou null"));
            capitulos.add(capitulo);
        }
        var livro = Ebook.builder().metadados(metadados.ler(parametros.diretorio()))
                .capitulos(capitulos).formato(parametros.formato()).build();
        var selecionado = geradores.select(new FormatoLiteral(livro.formato()));
        if (!selecionado.isResolvable()) throw new IllegalStateException("Gerador ausente ou ambíguo: " + livro.formato());
        selecionado.get().gerar(livro, parametros.destino());
        for (var plugin : ServiceLoader.load(PluginGeracao.class)) plugin.aposGeracao(livro);
        return livro;
    }
}
