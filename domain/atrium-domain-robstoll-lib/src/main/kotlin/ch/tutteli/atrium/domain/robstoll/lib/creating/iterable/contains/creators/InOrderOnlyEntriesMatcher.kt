package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion

class InOrderOnlyEntriesMatcher<E: Any> : InOrderOnlyMatcher<E?, (AssertionPlant<E>.() -> Unit)?> {

    override fun matches(actual: E?, searchCriterion: (AssertionPlant<E>.() -> Unit)?): Boolean
        = allCreatedAssertionsHold(actual, searchCriterion)

    override fun entryAssertionCreator(
        iterableAsList: List<E?>,
        searchCriterion: (AssertionPlant<E>.() -> Unit)?
    ): (Boolean) -> Assertion {
        val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, iterableAsList)
        return { found -> createEntryAssertion(explanatoryAssertions, found) }
    }
}
