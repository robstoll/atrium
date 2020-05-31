package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createEntryAssertionTemplate
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

interface InOrderOnlyMatcher<E, SC> {
    fun matches(actual: E, searchCriterion: SC): Boolean

    fun entryAssertionCreator(maybeSubject: Option<List<E>>, searchCriterion: SC): (() -> Boolean) -> Assertion

    fun Expect<List<E>>.createSingleEntryAssertion(
        currentIndex: Int,
        searchCriterion: SC,
        translatableIndex: DescriptionIterableAssertion
    ) {
        val template = createEntryAssertionTemplate(
            this.maybeSubject,
            currentIndex,
            searchCriterion,
            translatableIndex,
            ::matches
        )
        addAssertion(template(entryAssertionCreator(this.maybeSubject, searchCriterion)))
    }
}
