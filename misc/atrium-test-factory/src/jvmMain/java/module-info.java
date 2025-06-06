module ch.tutteli.atrium.testfactory.junit {
    requires ch.tutteli.atrium.core.api;
    requires ch.tutteli.atrium.logic;

    requires kotlin.stdlib;
    requires org.junit.jupiter.api;

    exports ch.tutteli.atrium.testfactories;
    exports ch.tutteli.atrium.testfactories.expect;
    exports ch.tutteli.atrium.testfactories.expect.root;
    exports ch.tutteli.atrium.testfactories.expect.grouped;
}
