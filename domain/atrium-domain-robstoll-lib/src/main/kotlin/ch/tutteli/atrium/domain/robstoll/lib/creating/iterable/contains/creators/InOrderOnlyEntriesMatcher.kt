package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant

class InOrderOnlyEntriesMatcher<E: Any> : InOrderOnlyMatcher<E?, (AssertionPlant<E>.() -> Unit)?> {

    override fun matches(actual: E?, searchCriterion: (AssertionPlant<E>.() -> Unit)?): Boolean
        = allCreatedAssertionsHold(actual, searchCriterion)

    override fun entryAssertionCreator(
        subjectProvider: () -> List<E?>,
        searchCriterion: (AssertionPlant<E>.() -> Unit)?
    ): (() -> Boolean) -> Assertion {
        val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, subjectProvider())
        return { found -> createEntryAssertion(explanatoryAssertions, found()) }
    }
}
