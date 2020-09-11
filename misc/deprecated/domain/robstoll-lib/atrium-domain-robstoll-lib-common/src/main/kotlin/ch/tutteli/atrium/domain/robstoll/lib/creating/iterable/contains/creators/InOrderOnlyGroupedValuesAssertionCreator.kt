package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl.iterable
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour

class InOrderOnlyGroupedValuesAssertionCreator<E, in T : Iterable<E>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E, T, E>(searchBehaviour),
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher() {

    override fun Expect<List<E>>.createSublistAssertion(groupOfSearchCriteria: List<E>) {
        val inAnyOrderOnly = iterable.contains.searchBehaviours.inAnyOrderOnly(
            iterable.contains.searchBehaviours.inAnyOrder(
                iterable.containsBuilder(this)
            )
        )
        addAssertion(iterable.contains.valuesInAnyOrderOnly(inAnyOrderOnly, groupOfSearchCriteria))
    }
}
