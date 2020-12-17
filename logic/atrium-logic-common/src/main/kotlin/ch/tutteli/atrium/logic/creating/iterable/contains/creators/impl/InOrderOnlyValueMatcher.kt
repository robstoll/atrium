package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE

class InOrderOnlyValueMatcher<E> : InOrderOnlyMatcher<E, E> {

    override fun elementAssertionCreator(maybeElement: Option<E>, searchCriterion: E): Assertion =
        assertionBuilder.createDescriptive(TO_BE, searchCriterion) {
            maybeElement.fold(falseProvider) { it == searchCriterion }
        }
}
