package ch.tutteli.atrium.domain.robstoll.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseCollectingAssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionContainer
import ch.tutteli.atrium.creating.MaybeSubject
import ch.tutteli.atrium.domain.creating.collectors.NonThrowingAssertionCollectorForExplanation
import ch.tutteli.atrium.domain.creating.collectors.ThrowingAssertionCollectorForExplanation
import ch.tutteli.atrium.domain.robstoll.lib.creating.collectors.AssertionCollectorForExplanationImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.collectors._collectAndThrowIfNothingCollected
import ch.tutteli.atrium.reporting.translating.Translatable

class NonThrowingAssertionCollectorForExplanationImpl : NonThrowingAssertionCollectorForExplanation {

    override fun <T> collect(
        maybeSubject: MaybeSubject<T>,
        assertionCreator: (CollectingAssertionContainer<T>.() -> Unit)?
    ): List<Assertion> = _collectAndThrowIfNothingCollected(false, maybeSubject, assertionCreator)

    override fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> collect(
        warningCannotEvaluate: Translatable,
        maybeSubject: MaybeSubject<T>,
        collectingPlantFactory: (() -> T) -> C,
        assertionCreator: (C.() -> Unit)?
    ): List<Assertion> = AssertionCollectorForExplanationImpl(false, collectingPlantFactory)
        .collect(warningCannotEvaluate, maybeSubject, assertionCreator)
}

class ThrowingAssertionCollectorForExplanationImpl : ThrowingAssertionCollectorForExplanation {

    override fun <T> collect(
        maybeSubject: MaybeSubject<T>,
        assertionCreator: (CollectingAssertionContainer<T>.() -> Unit)?
    ): List<Assertion> =_collectAndThrowIfNothingCollected(true, maybeSubject, assertionCreator)

    override fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> collect(
        warningCannotEvaluate: Translatable,
        maybeSubject: MaybeSubject<T>,
        collectingPlantFactory: (() -> T) -> C,
        assertionCreator: (C.() -> Unit)?
    ): List<Assertion> = AssertionCollectorForExplanationImpl(true, collectingPlantFactory)
        .collect(warningCannotEvaluate, maybeSubject, assertionCreator)
}
