package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE

class InOrderOnlyValueMatcher<E> : InOrderOnlyMatcher<E, E> {

    override fun matches(actual: E, searchCriterion: E): Boolean = actual == searchCriterion

    override fun entryAssertionCreator(
        maybeSubject: Option<List<E>>,
        searchCriterion: E
    ): (() -> Boolean) -> Assertion = { found ->
        assertionBuilder.createDescriptive(TO_BE, searchCriterion, found)
    }
}
