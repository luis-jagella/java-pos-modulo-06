package dev.jagella.cotuba.markdown;

import dev.jagella.cotuba.domain.*;
import dev.jagella.cotuba.ports.Renderizador;
import jakarta.enterprise.context.ApplicationScoped;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@ApplicationScoped
public class CommonMarkRenderizador implements Renderizador {
    public Capitulo renderizar(CapituloBruto bruto) {
        var document = Parser.builder().build().parse(bruto.markdown());
        var titulo = new StringBuilder();
        for (Node node = document.getFirstChild(); node != null; node = node.getNext()) {
            if (node instanceof Heading heading && heading.getLevel() == 1) {
                heading.accept(new AbstractVisitor() {
                    @Override public void visit(Text text) { titulo.append(text.getLiteral()); }
                    @Override public void visit(Code code) { titulo.append(code.getLiteral()); }
                });
                break;
            }
        }
        if (titulo.isEmpty()) titulo.append(bruto.origem());
        var html = HtmlRenderer.builder().escapeHtml(true).sanitizeUrls(true).build().render(document);
        return new Capitulo(bruto.origem(), titulo.toString(), bruto.markdown(), html);
    }
}
