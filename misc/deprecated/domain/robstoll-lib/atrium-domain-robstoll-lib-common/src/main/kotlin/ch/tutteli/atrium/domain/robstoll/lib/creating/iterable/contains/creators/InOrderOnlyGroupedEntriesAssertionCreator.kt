package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.inAnyOrder
import ch.tutteli.atrium.api.fluent.en_GB.only
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour

class InOrderOnlyGroupedEntriesAssertionCreator<E : Any, in T : Iterable<E?>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E?, T, (Expect<E>.() -> Unit)?>(searchBehaviour),
    InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> by InOrderOnlyEntriesMatcher() {

    override fun Expect<List<E?>>.createSublistAssertion(groupOfSearchCriteria: List<(Expect<E>.() -> Unit)?>) {
        val inAnyOrderOnly = contains.inAnyOrder.only
        addAssertion(ExpectImpl.iterable.contains.entriesInAnyOrderOnly(inAnyOrderOnly, groupOfSearchCriteria))
    }
}
