//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL

class InOrderOnlyValueMatcher<E> : InOrderOnlyMatcher<E, E> {

    override fun AssertionContainer<List<E>>.elementAssertionCreator(maybeElement: Option<E>, searchCriterion: E): Assertion =
        assertionBuilder.createDescriptive(TO_EQUAL, searchCriterion) {
            maybeElement.fold(falseProvider) { it == searchCriterion }
        }
}
