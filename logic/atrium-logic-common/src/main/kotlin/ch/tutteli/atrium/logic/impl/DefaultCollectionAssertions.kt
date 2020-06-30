package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.CollectionAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.*

class DefaultCollectionAssertions : CollectionAssertions {

    override fun <T : Collection<*>> isEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS, EMPTY) { it.isEmpty() }

    override fun <T : Collection<*>> isNotEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS_NOT, EMPTY) { it.isNotEmpty() }

    override fun <T : Collection<*>> size(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, Int> =
        container.manualFeature(SIZE) { size }
}