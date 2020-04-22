@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.allCreatedAssertionsHold
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createEntryAssertion
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createExplanatoryAssertions

@Suppress("DEPRECATION")
@Deprecated("Switch from Assert to Expect and use InOrderOnlyEntriesMatcher; will be removed with 1.0.0")
class InOrderOnlyEntriesDeprecatedMatcher<E : Any> : InOrderOnlyDeprecatedMatcher<E?, (AssertionPlant<E>.() -> Unit)?> {

    override fun matches(actual: E?, searchCriterion: (AssertionPlant<E>.() -> Unit)?): Boolean =
        allCreatedAssertionsHold(actual, searchCriterion)

    override fun entryAssertionCreator(
        subjectProvider: () -> List<E?>,
        searchCriterion: (AssertionPlant<E>.() -> Unit)?
    ): (() -> Boolean) -> Assertion {
        val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, subjectProvider())
        return { found -> createEntryAssertion(explanatoryAssertions, found()) }
    }
}
