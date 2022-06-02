package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.IteratorAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_HAVE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_HAVE
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.A_NEXT_ELEMENT

class DefaultIteratorAssertions : IteratorAssertions {
    override fun <E, T : Iterator<E>> hasNext(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_HAVE, A_NEXT_ELEMENT) { it.hasNext() }

    override fun <E, T : Iterator<E>> hasNotNext(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(NOT_TO_HAVE, A_NEXT_ELEMENT) { !it.hasNext() }
}
