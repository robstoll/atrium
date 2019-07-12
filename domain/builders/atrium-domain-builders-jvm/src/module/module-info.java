module ch.tutteli.atrium.domain.builders {
    requires transitive ch.tutteli.atrium.domain.api;
    requires transitive ch.tutteli.atrium.core.api;
    requires static     ch.tutteli.atrium.translations.en_GB;
    requires            ch.tutteli.kbox;
    requires            kotlin.stdlib;

    exports ch.tutteli.atrium.domain.builders;
    exports ch.tutteli.atrium.domain.builders.assertions.builders;
    exports ch.tutteli.atrium.domain.builders.creating;
    exports ch.tutteli.atrium.domain.builders.creating.changers;
    exports ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders;
    exports ch.tutteli.atrium.domain.builders.creating.collectors;
    exports ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders;
    exports ch.tutteli.atrium.domain.builders.reporting;
    exports ch.tutteli.atrium.domain.builders.utils;

    provides ch.tutteli.atrium.reporting.ReporterFactory
        with ch.tutteli.atrium.domain.builders.reporting.impl.DefaultReporterFactory;
}
