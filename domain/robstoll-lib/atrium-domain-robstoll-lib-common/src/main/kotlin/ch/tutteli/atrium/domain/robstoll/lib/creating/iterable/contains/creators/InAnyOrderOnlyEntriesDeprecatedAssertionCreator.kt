package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.allCreatedAssertionsHold
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createEntryAssertion
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createExplanatoryAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries have
 * to appear in the [Iterable] but in any order -- an entry is identified by holding a group of assertions
 * created by an assertion creator lambda.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected
 *   entries have to appear in the [Iterable] but in any order -- an entry is identified by holding a group
 *   of assertions created by an assertion creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
@Deprecated("Switch from Assert to Expect and use InAnyOrderOnlyEntriesAssertionCreator; will be removed with 1.0.0")
class InAnyOrderOnlyEntriesDeprecatedAssertionCreator<E : Any, in T : Iterable<E?>>(
    searchBehaviour: InAnyOrderOnlySearchBehaviour
) : InAnyOrderOnlyAssertionCreator<E, T, (AssertionPlant<E>.() -> Unit)?>(searchBehaviour) {

    override fun createAssertionForSearchCriterionAndRemoveMatchFromList(searchCriterion: (AssertionPlant<E>.() -> Unit)?, list: MutableList<E?>): Pair<Boolean, Assertion> {
        @Suppress("DEPRECATION")
        val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, list)
        val found = removeMatch(list, searchCriterion)
        @Suppress("DEPRECATION")
        return found to createEntryAssertion(explanatoryAssertions, found)
    }

    private fun removeMatch(list: MutableList<E?>, assertionCreator: (AssertionPlant<E>.() -> Unit)?): Boolean {
        val itr = list.iterator()
        while (itr.hasNext()) {
            @Suppress("DEPRECATION")
            if (allCreatedAssertionsHold(itr.next(), assertionCreator)) {
                itr.remove()
                return true
            }
        }
        return false
    }
}
