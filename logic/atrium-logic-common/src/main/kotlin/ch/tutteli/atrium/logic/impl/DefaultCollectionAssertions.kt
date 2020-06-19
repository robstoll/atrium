package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.CollectionAssertions
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.*

class DefaultCollectionAssertions : BaseAssertions(),
    CollectionAssertions {
    override fun isEmpty(container: AssertionContainer<Collection<*>>): Assertion =
        container.createDescriptiveAssertion(IS, EMPTY) { isEmpty() }

    override fun isNotEmpty(container: AssertionContainer<Collection<*>>): Assertion =
        container.createDescriptiveAssertion(IS_NOT, EMPTY) { isNotEmpty() }

    override fun <T : Collection<*>> size(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, Int> =
        container.manualFeature(SIZE) { size }
}