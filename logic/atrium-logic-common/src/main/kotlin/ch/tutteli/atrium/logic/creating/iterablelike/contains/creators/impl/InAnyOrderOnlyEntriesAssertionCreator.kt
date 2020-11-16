package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.impl.allCreatedAssertionsHold
import ch.tutteli.atrium.logic.impl.createEntryAssertion
import ch.tutteli.atrium.logic.impl.createExplanatoryAssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries have
 * to appear in the [Iterable] but in any order -- an entry is identified by holding a group of assertions
 * created by an assertion creator lambda.
 *
 * @param E The type of the elements of the [Iterable] the [converter] is going to create.
 * @param T The type of the subject of the assertion for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected
 *   entries have to appear in the [Iterable] but in any order -- an entry is identified by holding a group
 *   of assertions created by an assertion creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
class InAnyOrderOnlyEntriesAssertionCreator<E : Any, T : IterableLike>(
    converter: (T) -> Iterable<E?>,
    searchBehaviour: InAnyOrderOnlySearchBehaviour
) : InAnyOrderOnlyAssertionCreator<E?, T, (Expect<E>.() -> Unit)?>(converter, searchBehaviour) {

    override fun createAssertionForSearchCriterionAndRemoveMatchFromList(
        searchCriterion: (Expect<E>.() -> Unit)?,
        list: MutableList<E?>
    ): Pair<Boolean, Assertion> {
        val explanatoryAssertions = createExplanatoryAssertionGroup(searchCriterion, list)
        val found = removeMatch(list, searchCriterion)
        return found to createEntryAssertion(explanatoryAssertions, found)
    }

    private fun removeMatch(list: MutableList<E?>, assertionCreator: (Expect<E>.() -> Unit)?): Boolean {
        val itr = list.iterator()
        while (itr.hasNext()) {
            if (allCreatedAssertionsHold(itr.next(), assertionCreator)) {
                itr.remove()
                return true
            }
        }
        return false
    }
}
