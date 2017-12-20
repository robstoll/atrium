package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.AN_ENTRY_WHICH
import ch.tutteli.atrium.assertions.FixHoldsAssertionGroup
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries have
 * to appear in the [Iterable] but in any order -- an entry is identified by holding a group of assertions
 * created by an assertion creator lambda.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected
 *              entries have to appear in the [Iterable] but in any order -- an entry is identified by holding a group
 *              of assertions created by an assertion creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *        decorate the description (an [ITranslatable]) which is used for the [IAssertionGroup].
 */
class IterableContainsInAnyOrderOnlyEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    searchBehaviour: IterableContainsInAnyOrderOnlySearchBehaviour
) : IterableContainsInAnyOrderOnlyAssertionCreator<E, T, AssertionPlant<E>.() -> Unit>(searchBehaviour) {

    override fun createAssertionForSearchCriterionAndRemoveMatchFromList(searchCriterion: AssertionPlant<E>.() -> Unit, list: MutableList<E>): Pair<Boolean, IAssertion> {
        val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, list)
        val found = removeMatch(list, searchCriterion)
        return Pair(found, createEntryAssertion(explanatoryAssertions, found))
    }

    private fun removeMatch(list: MutableList<E>, assertionCreator: AssertionPlant<E>.() -> Unit): Boolean {
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


internal fun <E : Any> createExplanatoryAssertions(assertionCreator: AssertionPlant<E>.() -> Unit, list: List<E>)
    = collectIterableAssertionsForExplanation(assertionCreator, list.firstOrNull())

internal fun createEntryAssertion(explanatoryAssertions: List<IAssertion>, found: Boolean) =
    FixHoldsAssertionGroup(ListAssertionGroupType, AN_ENTRY_WHICH, RawString.EMPTY, explanatoryAssertions, found)

internal fun <E : Any> allCreatedAssertionsHold(subject: E, assertionCreator: AssertionPlant<E>.() -> Unit): Boolean {
    val checkingPlant = AtriumFactory.newCheckingPlant(subject)
    checkingPlant.assertionCreator()
    return checkingPlant.allAssertionsHold()

}
