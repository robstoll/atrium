module ch.tutteli.atrium.domain.robstoll.lib {

    requires ch.tutteli.atrium.domain.builders;
    requires ch.tutteli.atrium.api.cc.en_GB;
    requires static ch.tutteli.atrium.translations.en_GB;

    exports ch.tutteli.atrium.domain.robstoll.lib.assertions;
    exports ch.tutteli.atrium.domain.robstoll.lib.assertions.composers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.builders;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.collectors;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.builders;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators;
    exports ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.providers;
}
