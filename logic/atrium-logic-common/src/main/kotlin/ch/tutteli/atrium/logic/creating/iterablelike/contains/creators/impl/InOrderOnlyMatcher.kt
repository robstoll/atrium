package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.ifWithinBound

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

    private fun <E, SC> createEntryAssertionTemplate(
        maybeSubject: Option<List<E>>,
        index: Int,
        searchCriterion: SC,
        entryWithIndex: DescriptionIterableAssertion,
        matches: (E, SC) -> Boolean
    ): ((() -> Boolean) -> Assertion) -> AssertionGroup {
        return { createEntryFeatureAssertion ->
            val list = maybeSubject.getOrElse { emptyList() }
            val (found, entryRepresentation) = list.ifWithinBound(index, {
                val entry = list[index]
                matches(entry, searchCriterion) to entry
            }, {
                false to DescriptionIterableAssertion.SIZE_EXCEEDED
            })
            val description = TranslatableWithArgs(entryWithIndex, index)
            assertionBuilder.feature
                .withDescriptionAndRepresentation(description, entryRepresentation)
                .withAssertion(createEntryFeatureAssertion { found })
                .build()
        }
    }

}
