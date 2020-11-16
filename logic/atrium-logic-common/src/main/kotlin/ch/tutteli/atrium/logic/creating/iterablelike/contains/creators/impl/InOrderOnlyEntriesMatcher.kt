package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.impl.allCreatedAssertionsHold
import ch.tutteli.atrium.logic.impl.createEntryAssertion
import ch.tutteli.atrium.logic.impl.createExplanatoryAssertionGroup

class InOrderOnlyEntriesMatcher<E : Any> : InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> {

    override fun matches(actual: E?, searchCriterion: (Expect<E>.() -> Unit)?): Boolean =
        allCreatedAssertionsHold(actual, searchCriterion)

    override fun entryAssertionCreator(
        maybeSubject: Option<List<E?>>,
        searchCriterion: (Expect<E>.() -> Unit)?
    ): (() -> Boolean) -> Assertion {
        val explanatoryGroup = createExplanatoryAssertionGroup(searchCriterion, maybeSubject.getOrElse { emptyList() })
        return { found -> createEntryAssertion(explanatoryGroup, found()) }
    }
}
