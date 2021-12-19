package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.CollectionLikeAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.CollectionLike
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation.SIZE
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation.EMPTY

class DefaultCollectionLikeAssertions : CollectionLikeAssertions {

    override fun <T : CollectionLike> isEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion =
        container.createDescriptiveAssertion(TO_BE, EMPTY) { converter(it).isEmpty() }

    override fun <T : CollectionLike> isNotEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion =
        container.createDescriptiveAssertion(NOT_TO_BE, EMPTY) { converter(it).isNotEmpty() }

    override fun <T : CollectionLike> size(container: AssertionContainer<T>, converter: (T) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<T, Int> =
        container.manualFeature(SIZE) { converter(this).size }
}
