package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.iterable.contains._domain
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour

class InOrderOnlyGroupedEntriesAssertionCreator<E : Any, in T : Iterable<E?>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E?, T, (Expect<E>.() -> Unit)?>(searchBehaviour),
    InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> by InOrderOnlyEntriesMatcher() {

    override fun Expect<List<E?>>.createSublistAssertion(groupOfSearchCriteria: List<(Expect<E>.() -> Unit)?>) {
        addAssertion(
            _domain.containsBuilder
                ._domain.inAnyOrder
                ._domain.only
                ._domain.entries(groupOfSearchCriteria)
        )
    }
}
