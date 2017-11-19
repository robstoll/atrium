package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.AN_ENTRY_WHICH
import ch.tutteli.atrium.assertions.FixHoldsAssertionGroup
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

class IterableContainsInAnyOrderOnlyEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    decorator: IterableContainsInAnyOrderOnlyDecorator
) : IterableContainsInAnyOrderOnlyAssertionCreator<E, T, IAssertionPlant<E>.() -> Unit>(decorator) {

    override fun createAssertionForExpectedAndRemoveMatchFromList(expected: IAssertionPlant<E>.() -> Unit, list: MutableList<E>): Pair<Boolean, IAssertion> {
        val explanatoryAssertions = collectIterableAssertionsForExplanation(expected, list.firstOrNull())
        val found = removeMatch(list, expected)
        return Pair(found, FixHoldsAssertionGroup(ListAssertionGroupType, AN_ENTRY_WHICH, RawString(""), explanatoryAssertions, found))
    }

    private fun removeMatch(list: MutableList<E>, assertionCreator: IAssertionPlant<E>.() -> Unit): Boolean {
        val itr = list.iterator()
        while (itr.hasNext()) {
            val checkingPlant = AtriumFactory.newCheckingPlant(itr.next())
            checkingPlant.assertionCreator()
            if (checkingPlant.allAssertionsHold()) {
                itr.remove()
                return true
            }
        }
        return false
    }
}
