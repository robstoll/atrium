module ch.tutteli.atrium.domain.robstoll.lib {
    requires        ch.tutteli.atrium.api.fluent.en_GB;
    requires        ch.tutteli.atrium.domain.builders;
    requires static ch.tutteli.atrium.translations.en_GB;
    requires        kotlin.stdlib;
    requires        ch.tutteli.niok;

    //TODO remove with 1.0.0
    requires        ch.tutteli.atrium.api.cc.en_GB;

    exports ch.tutteli.atrium.domain.robstoll.lib.assertions.composers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.changers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.builders;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.collectors;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.feature.extract.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.builders;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.providers;
}
