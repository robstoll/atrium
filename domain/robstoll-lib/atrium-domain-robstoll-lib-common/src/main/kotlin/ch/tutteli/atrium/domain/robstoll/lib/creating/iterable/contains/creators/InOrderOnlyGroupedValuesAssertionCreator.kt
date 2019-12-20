package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.iterable.contains._domain
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour

class InOrderOnlyGroupedValuesAssertionCreator<E, in T : Iterable<E>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E, T, E>(searchBehaviour),
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher() {

    override fun Expect<List<E>>.createSublistAssertion(groupOfSearchCriteria: List<E>) {
        addAssertion(
            _domain.containsBuilder
                ._domain.inAnyOrder
                ._domain.only
                ._domain.values(groupOfSearchCriteria)
        )
    }
}
