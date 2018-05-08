package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.toSubjectProvider
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

interface InOrderOnlyMatcher<E, SC> {
    fun matches(actual: E, searchCriterion: SC): Boolean

    fun entryAssertionCreator(subjectProvider: () -> List<E>, searchCriterion: SC): (Boolean) -> Assertion

    fun CollectingAssertionPlant<List<E>>.createSingleEntryAssertion(
        currentIndex: Int,
        searchCriterion: SC,
        translatableIndex: DescriptionIterableAssertion
    ) {
        val template = createEntryAssertionTemplate(
            this.toSubjectProvider(),
            currentIndex,
            searchCriterion,
            translatableIndex,
            ::matches
        )
        addAssertion(template(entryAssertionCreator(this.toSubjectProvider(), searchCriterion)))
    }
}
