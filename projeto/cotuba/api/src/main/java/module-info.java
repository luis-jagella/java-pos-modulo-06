module cotuba.api {
    requires transitive org.jmolecules.ddd;
    requires transitive org.jmolecules.architecture.hexagonal;
    exports dev.jagella.cotuba.domain;
    exports dev.jagella.cotuba.ports;
    exports dev.jagella.cotuba.spi;
    exports dev.jagella.cotuba.request;
    exports dev.jagella.cotuba.usecase;
    opens dev.jagella.cotuba.usecase to weld.se.shaded;
    opens dev.jagella.cotuba.ports to weld.se.shaded;
}
