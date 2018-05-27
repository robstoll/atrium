package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion

class InOrderOnlyValueMatcher<E> : InOrderOnlyMatcher<E, E> {

    override fun matches(actual: E, searchCriterion: E): Boolean
        = actual == searchCriterion

    override fun entryAssertionCreator(subjectProvider: () -> List<E>, searchCriterion: E): (() -> Boolean) -> Assertion
        = { found ->
        AssertImpl.builder.descriptive
            .withTest(found)
            .withDescriptionAndRepresentation(DescriptionAnyAssertion.TO_BE, searchCriterion ?: RawString.NULL)
            .build()
        }
}
