package dev.jagella.cotuba.support;

public final class HtmlSeguro {
    private HtmlSeguro() {}
    public static String escapar(String texto) {
        return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
    public static String documento(String titulo, String corpo) {
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/><title>" + escapar(titulo)
                + "</title></head><body>" + corpo + "</body></html>";
    }
}
