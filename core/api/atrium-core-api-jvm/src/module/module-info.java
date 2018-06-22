module ch.tutteli.atrium.core.api {

    requires transitive kotlin.stdlib;
    requires ch.tutteli.kbox;

    exports ch.tutteli.atrium.assertions;
    exports ch.tutteli.atrium.assertions.builders;
    exports ch.tutteli.atrium.checking;
    exports ch.tutteli.atrium.core;
    exports ch.tutteli.atrium.core.migration;
    exports ch.tutteli.atrium.creating;
    exports ch.tutteli.atrium.reporting;
    exports ch.tutteli.atrium.reporting.translating;
}
