package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion

interface InOrderOnlyMatcher<E, SC> {
    fun matches(actual: E, searchCriterion: SC): Boolean

    fun entryAssertionCreator(iterableAsList: List<E>, searchCriterion: SC): (Boolean) -> Assertion
}
