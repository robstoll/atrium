package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.containsBuilder
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.inAnyOrder
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.butOnly
import ch.tutteli.kbox.identity

class InOrderOnlyGroupedValuesAssertionCreator<E, T : IterableLike>(
    converter: (T) -> Iterable<E>,
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E, T, E>(converter, searchBehaviour),
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher() {

    override fun Expect<List<E>>.addSublistAssertion(groupOfSearchCriteria: List<E>) {
        _logic.containsBuilder(::identity)._logic.inAnyOrder._logic.butOnly._logicAppend {
            valuesInAnyOrderOnly(groupOfSearchCriteria)
        }
    }
}
