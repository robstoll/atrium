package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE

class InOrderOnlyValueMatcher<E> : InOrderOnlyMatcher<E, E> {

    override fun matches(actual: E, searchCriterion: E): Boolean = actual == searchCriterion

    override fun entryAssertionCreator(
        maybeSubject: Option<List<E>>,
        searchCriterion: E
    ): (() -> Boolean) -> Assertion = { found ->
        ExpectImpl.builder.createDescriptive(TO_BE, searchCriterion, found)
    }
}
