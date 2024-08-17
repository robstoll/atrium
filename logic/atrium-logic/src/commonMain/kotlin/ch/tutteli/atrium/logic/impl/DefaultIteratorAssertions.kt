package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.IteratorAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_HAVE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_HAVE
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.A_NEXT_ELEMENT
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.SIZE_EXCEEDED

class DefaultIteratorAssertions : IteratorAssertions {
    override fun <E, T : Iterator<E>> hasNext(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_HAVE, A_NEXT_ELEMENT) { it.hasNext() }

    override fun <E, T : Iterator<E>> hasNotNext(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(NOT_TO_HAVE, A_NEXT_ELEMENT) { !it.hasNext() }

    override fun <E, T : Iterator<E>> next(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E> =
        container.extractFeature
            .methodCall("next")
            .withRepresentationForFailure(SIZE_EXCEEDED)
            .withFeatureExtraction {
                val iterator = it.iterator()
                Option.someIf(iterator.hasNext()) { iterator.next() }
            }
            .withoutOptions()
            .build()
}
