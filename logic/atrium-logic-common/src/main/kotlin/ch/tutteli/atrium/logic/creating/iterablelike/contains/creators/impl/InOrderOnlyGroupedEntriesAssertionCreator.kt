package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.containsBuilder
import ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.entriesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.butOnly
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.inAnyOrder
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.kbox.identity

class InOrderOnlyGroupedEntriesAssertionCreator<E : Any, T : IterableLike>(
    converter: (T) -> Iterable<E?>,
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E?, T, (Expect<E>.() -> Unit)?>(converter, searchBehaviour),
    InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> by InOrderOnlyEntriesMatcher() {

    override fun Expect<List<E?>>.addSublistAssertion(groupOfSearchCriteria: List<(Expect<E>.() -> Unit)?>) {
        _logic.containsBuilder(::identity)._logic.inAnyOrder._logic.butOnly._logicAppend {
            entriesInAnyOrderOnly(groupOfSearchCriteria)
        }
    }
}
