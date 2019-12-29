@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll

import ch.tutteli.atrium.core.polyfills.registerService

@Suppress("unused" /* here in order that the code is executed when module is loaded */)
private val register = run {

    registerService<ch.tutteli.atrium.domain.assertions.composers.AssertionComposer> { ch.tutteli.atrium.domain.robstoll.assertions.composers.AssertionComposerImpl() }
    registerService<ch.tutteli.atrium.domain.creating.AnyAssertions> { ch.tutteli.atrium.domain.robstoll.creating.AnyAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.CharSequenceAssertions> { ch.tutteli.atrium.domain.robstoll.creating.CharSequenceAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.CollectionAssertions> { ch.tutteli.atrium.domain.robstoll.creating.CollectionAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.ComparableAssertions> { ch.tutteli.atrium.domain.robstoll.creating.ComparableAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.FeatureAssertions> { ch.tutteli.atrium.domain.robstoll.creating.FeatureAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.FloatingPointAssertions> { ch.tutteli.atrium.domain.robstoll.creating.FloatingPointAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.Fun0Assertions> { ch.tutteli.atrium.domain.robstoll.creating.Fun0AssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.IterableAssertions> { ch.tutteli.atrium.domain.robstoll.creating.IterableAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.ListAssertions> { ch.tutteli.atrium.domain.robstoll.creating.ListAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.MapAssertions> { ch.tutteli.atrium.domain.robstoll.creating.MapAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.MapEntryAssertions> { ch.tutteli.atrium.domain.robstoll.creating.MapEntryAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.NewFeatureAssertions> { ch.tutteli.atrium.domain.robstoll.creating.NewFeatureAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.PairAssertions> { ch.tutteli.atrium.domain.robstoll.creating.PairAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.ThrowableAssertions> { ch.tutteli.atrium.domain.robstoll.creating.ThrowableAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.any.typetransformation.creators.AnyTypeTransformationAssertions> { ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.creators.AnyTypeTransformationAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.FailureHandlerFactory> { ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.failurehandlers.FailureHandlerFactoryImpl() }
    registerService<ch.tutteli.atrium.domain.creating.changers.FeatureExtractor> { ch.tutteli.atrium.domain.robstoll.creating.changers.FeatureExtractorImpl() }
    registerService<ch.tutteli.atrium.domain.creating.changers.SubjectChanger> { ch.tutteli.atrium.domain.robstoll.creating.changers.SubjectChangerImpl() }
    registerService<ch.tutteli.atrium.domain.creating.charsequence.contains.checkers.CheckerFactory> { ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.checkers.CheckerFactoryImpl() }
    registerService<ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions> { ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.creators.CharSequenceContainsAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory> { ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactoryImpl() }
    registerService<ch.tutteli.atrium.domain.creating.collectors.AssertionCollector> { ch.tutteli.atrium.domain.robstoll.creating.collectors.AssertionCollectorImpl() }
    registerService<ch.tutteli.atrium.domain.creating.collectors.NonThrowingAssertionCollectorForExplanation> { ch.tutteli.atrium.domain.robstoll.creating.collectors.NonThrowingAssertionCollectorForExplanationImpl() }
    registerService<ch.tutteli.atrium.domain.creating.collectors.ThrowingAssertionCollectorForExplanation> { ch.tutteli.atrium.domain.robstoll.creating.collectors.ThrowingAssertionCollectorForExplanationImpl() }
    registerService<ch.tutteli.atrium.domain.creating.feature.extract.creators.FeatureExtractorCreatorFactory> { ch.tutteli.atrium.domain.robstoll.creating.feature.extract.creators.FeatureExtractorCreatorFactoryImpl() }
    registerService<ch.tutteli.atrium.domain.creating.iterable.contains.checkers.CheckerFactory> { ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.checkers.CheckerFactoryImpl() }
    registerService<ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions> { ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.creators.IterableContainsAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.SearchBehaviourFactory> { ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.searchbehaviours.SearchBehaviourFactoryImpl() }
    registerService<ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions> { ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.creators.ThrowableThrownAssertionsImpl() }
    registerService<ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory> { ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactoryImpl() }
}
