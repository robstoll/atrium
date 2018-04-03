module ch.tutteli.atrium.domain.builders {

    requires transitive ch.tutteli.atrium.domain.api;

    exports ch.tutteli.atrium.domain.builders;
    exports ch.tutteli.atrium.domain.builders.assertions;
    exports ch.tutteli.atrium.domain.builders.assertions.builders;
    exports ch.tutteli.atrium.domain.builders.creating;
    exports ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders;
    exports ch.tutteli.atrium.domain.builders.creating.collectors;
    exports ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders;
    exports ch.tutteli.atrium.domain.builders.reporting;
}
