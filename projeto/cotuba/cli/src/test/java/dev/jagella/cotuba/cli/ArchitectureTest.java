package dev.jagella.cotuba.cli;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.jmolecules.archunit.JMoleculesDddRules;
import org.jmolecules.archunit.JMoleculesArchitectureRules;
import org.junit.jupiter.api.Test;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

class ArchitectureTest {
    private final com.tngtech.archunit.core.domain.JavaClasses classes = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS).importPackages("dev.jagella.cotuba");
    @Test void regrasDdd() { JMoleculesDddRules.all().check(classes); }
    @Test void regraDddDetectaEntidadeSemIdentidade() {
        var invalida = new ClassFileImporter().importClasses(EntidadeSemIdentidade.class);
        org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class, () -> JMoleculesDddRules.all().check(invalida));
    }
    @org.jmolecules.ddd.annotation.Entity
    static class EntidadeSemIdentidade { String titulo; }
    @Test void regrasHexagonais() { JMoleculesArchitectureRules.ensureHexagonal().check(classes); }
    @Test void importaTodosOsModulos() {
        for (var nome : java.util.List.of("domain.Ebook", "application.CotubaService", "markdown.CapitulosEmDisco", "pdf.GeradorPdf", "epub.GeradorEpub", "html.GeradorHtml", "cli.Main"))
            org.junit.jupiter.api.Assertions.assertTrue(classes.contain("dev.jagella.cotuba." + nome), nome);
    }
    @Test void dominioSemInfraestrutura() {
        noClasses().that().resideInAPackage("..domain..").should().dependOnClassesThat()
                .resideInAnyPackage("java.nio..", "java.io..", "jakarta..", "org.commonmark..", "com.itextpdf..", "..application..", "..cli..").check(classes);
    }
    @Test void aplicacaoSemImplementacoes() {
        noClasses().that().resideInAPackage("..application..").should().dependOnClassesThat()
                .resideInAnyPackage("..markdown..", "..pdf..", "..epub..", "..html..", "..cli..").check(classes);
    }
    @Test void pacotesSemCiclos() { slices().matching("dev.jagella.cotuba.(*)..").should().beFreeOfCycles().check(classes); }
}
