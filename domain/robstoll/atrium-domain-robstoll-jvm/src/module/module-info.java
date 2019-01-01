module ch.tutteli.atrium.domain.robstoll {
    requires transitive ch.tutteli.atrium.domain.api;
    requires            ch.tutteli.atrium.domain.robstoll.lib;
    requires            kotlin.stdlib;

    exports ch.tutteli.atrium.domain.robstoll.assertions.composers;
    exports ch.tutteli.atrium.domain.robstoll.creating;
    exports ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.failurehandlers;
    exports ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.creating.collectors;
    exports ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.providers;

    provides ch.tutteli.atrium.domain.assertions.composers.AssertionComposer
        with ch.tutteli.atrium.domain.robstoll.assertions.composers.AssertionComposerImpl;

    provides ch.tutteli.atrium.domain.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.creators.AnyTypeTransformationAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.FailureHandlerFactory
        with ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.failurehandlers.FailureHandlerFactoryImpl;

    provides ch.tutteli.atrium.domain.creating.AnyAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.AnyAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.BigDecimalAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.BigDecimalAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.charsequence.contains.checkers.CheckerFactory
        with ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.checkers.CheckerFactoryImpl;

    provides ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.creators.CharSequenceContainsAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory
        with ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactoryImpl;

    provides ch.tutteli.atrium.domain.creating.CharSequenceAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.CharSequenceAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.CollectionAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.CollectionAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
        with ch.tutteli.atrium.domain.robstoll.creating.collectors.AssertionCollectorImpl;

    provides ch.tutteli.atrium.domain.creating.collectors.NonThrowingAssertionCollectorForExplanation
        with ch.tutteli.atrium.domain.robstoll.creating.collectors.NonThrowingAssertionCollectorForExplanationImpl;

    provides ch.tutteli.atrium.domain.creating.collectors.ThrowingAssertionCollectorForExplanation
        with ch.tutteli.atrium.domain.robstoll.creating.collectors.ThrowingAssertionCollectorForExplanationImpl;

    provides ch.tutteli.atrium.domain.creating.ComparableAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.ComparableAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.FeatureAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.FeatureAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.FloatingPointAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.FloatingPointAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.iterable.contains.checkers.CheckerFactory
        with ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.checkers.CheckerFactoryImpl;

    provides ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.creators.IterableContainsAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.SearchBehaviourFactory
        with ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.searchbehaviours.SearchBehaviourFactoryImpl;

    provides ch.tutteli.atrium.domain.creating.IterableAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.IterableAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.ListAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.ListAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.MapAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.MapAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.creators.ThrowableThrownAssertionsImpl;

    provides ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory
        with ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactoryImpl;

    provides ch.tutteli.atrium.domain.creating.ThrowableAssertions
        with ch.tutteli.atrium.domain.robstoll.creating.ThrowableAssertionsImpl;
}
