module ch.tutteli.atrium.core.api {
    requires transitive ch.tutteli.kbox;
    requires            kotlin.stdlib;
    requires            kotlin.reflect;

    exports ch.tutteli.atrium.assertions;
    exports ch.tutteli.atrium.assertions.builders;
    exports ch.tutteli.atrium.assertions.builders.common;
    exports ch.tutteli.atrium.core;
    exports ch.tutteli.atrium.core.migration;
    exports ch.tutteli.atrium.core.polyfills;
    exports ch.tutteli.atrium.creating;
    exports ch.tutteli.atrium.creating.feature;
    exports ch.tutteli.atrium.reporting;
    exports ch.tutteli.atrium.reporting.erroradjusters;
    exports ch.tutteli.atrium.reporting.text;
    exports ch.tutteli.atrium.reporting.translating;

    // TODO 0.17.0 or 0.18.0 remove once DefaultComponentFactoryContainer is internal
    exports ch.tutteli.atrium.creating.impl to
        ch.tutteli.atrium.logic,
        ch.tutteli.atrium.api.infix.en_GB;
}
