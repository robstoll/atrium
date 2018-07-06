package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.inAnyOrder
import ch.tutteli.atrium.api.cc.en_GB.only
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour

class InOrderOnlyGroupedValuesAssertionCreator<E, in T : Iterable<E>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E, T, E>(searchBehaviour),
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher()
{
    override fun Assert<List<E>>.createSublistAssertion(groupOfSearchCriteria: List<E>) {
        val inAnyOrderOnly = contains.inAnyOrder.only
        addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(inAnyOrderOnly, groupOfSearchCriteria))
    }
}
