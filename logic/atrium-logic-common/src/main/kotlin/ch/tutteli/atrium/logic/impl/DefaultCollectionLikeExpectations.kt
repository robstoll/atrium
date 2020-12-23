package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.CollectionLikeAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.typeutils.CollectionLike
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.*

class DefaultCollectionLikeAssertions : CollectionLikeAssertions {

    override fun <T : CollectionLike> isEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion =
        container.createDescriptiveAssertion(IS, EMPTY) { converter(it).isEmpty() }

    override fun <T : CollectionLike> isNotEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion =
        container.createDescriptiveAssertion(IS_NOT, EMPTY) { converter(it).isNotEmpty() }

    override fun <T : CollectionLike> size(container: AssertionContainer<T>, converter: (T) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<T, Int> =
        container.manualFeature(SIZE) { converter(this).size }
}
