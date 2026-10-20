package verificacao;

// Fixture negativa: DEVE falhar ao compilar porque o pacote não é exportado.
import dev.jagella.cotuba.pdf.GeradorPdf;

public class Prova { GeradorPdf acessoProibido; }
