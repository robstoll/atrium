@file:Suppress(
/* TODO remove annotation with 1.0.0 */ "DEPRECATION",
/* TODO remove annotation with 1.0.0 */ "TYPEALIAS_EXPANSION_DEPRECATION"
)

package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.inAnyOrder
import ch.tutteli.atrium.api.cc.en_GB.only
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour

@Suppress("DEPRECATION")
@Deprecated("Switch from Assert to Expect and use InOrderOnlyGroupedEntriesAssertionCreator; will be removed with 1.0.0")
class InOrderOnlyGroupedEntriesDeprecatedAssertionCreator<E : Any, in T : Iterable<E?>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedDeprecatedAssertionCreator<E?, T, (AssertionPlant<E>.() -> Unit)?>(searchBehaviour),
    InOrderOnlyDeprecatedMatcher<E?, (AssertionPlant<E>.() -> Unit)?> by InOrderOnlyEntriesDeprecatedMatcher() {

    override fun Assert<List<E?>>.createSublistAssertion(groupOfSearchCriteria: List<(AssertionPlant<E>.() -> Unit)?>) {
        val inAnyOrderOnly = contains.inAnyOrder.only
        addAssertion(
            AssertImpl.iterable.contains.entriesInAnyOrderOnlyWithAssert(
                inAnyOrderOnly,
                groupOfSearchCriteria
            )
        )
    }
}
