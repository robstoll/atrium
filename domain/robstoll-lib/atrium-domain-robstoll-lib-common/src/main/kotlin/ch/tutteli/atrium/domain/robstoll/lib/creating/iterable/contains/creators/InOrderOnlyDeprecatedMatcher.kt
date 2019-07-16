package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createEntryAssertionTemplate
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

@Deprecated("Switch from Assert to Expect and use InOrderOnlyMatcher; will be removed with 1.0.0")
interface InOrderOnlyDeprecatedMatcher<E, SC> {
    fun matches(actual: E, searchCriterion: SC): Boolean

    fun entryAssertionCreator(subjectProvider: () -> List<E>, searchCriterion: SC): (() -> Boolean) -> Assertion

    fun CollectingAssertionPlant<List<E>>.createSingleEntryAssertion(
        currentIndex: Int,
        searchCriterion: SC,
        translatableIndex: DescriptionIterableAssertion
    ) {
        @Suppress("DEPRECATION") val template = createEntryAssertionTemplate(
            this.subjectProvider,
            currentIndex,
            searchCriterion,
            translatableIndex,
            ::matches
        )
        addAssertion(template(entryAssertionCreator(this.subjectProvider, searchCriterion)))
    }
}
