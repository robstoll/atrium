module ch.tutteli.atrium.domain.api {
    requires transitive ch.tutteli.atrium.core.api;
    requires            kotlin.stdlib;

    exports ch.tutteli.atrium.domain.assertions.composers;
    exports ch.tutteli.atrium.domain.creating;
    exports ch.tutteli.atrium.domain.creating.any.typetransformation;
    exports ch.tutteli.atrium.domain.creating.any.typetransformation.creators;
    exports ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers;
    exports ch.tutteli.atrium.domain.creating.basic.contains;
    exports ch.tutteli.atrium.domain.creating.changers;
    exports ch.tutteli.atrium.domain.creating.charsequence.contains;
    exports ch.tutteli.atrium.domain.creating.charsequence.contains.checkers;
    exports ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    exports ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.creating.collectors;
    exports ch.tutteli.atrium.domain.creating.feature.extract;
    exports ch.tutteli.atrium.domain.creating.feature.extract.creators;
    exports ch.tutteli.atrium.domain.creating.iterable.contains;
    exports ch.tutteli.atrium.domain.creating.iterable.contains.checkers;
    exports ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    exports ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.creating.throwable.thrown;
    exports ch.tutteli.atrium.domain.creating.throwable.thrown.creators;
    exports ch.tutteli.atrium.domain.creating.throwable.thrown.providers;
    exports ch.tutteli.atrium.domain.utils;
}
