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
        val explanatoryAssertions = createExplanatoryAssertions(expected, list)
        val found = removeMatch(list, expected)
        return Pair(found, createEntryAssertion(explanatoryAssertions, found))
    }

    private fun removeMatch(list: MutableList<E>, assertionCreator: IAssertionPlant<E>.() -> Unit): Boolean {
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


internal fun <E : Any> createExplanatoryAssertions(assertionCreator: IAssertionPlant<E>.() -> Unit, list: List<E>)
    = collectIterableAssertionsForExplanation(assertionCreator, list.firstOrNull())

internal fun createEntryAssertion(explanatoryAssertions: List<IAssertion>, found: Boolean) =
    FixHoldsAssertionGroup(ListAssertionGroupType, AN_ENTRY_WHICH, RawString(""), explanatoryAssertions, found)

internal fun <E : Any> allCreatedAssertionsHold(subject: E, assertionCreator: IAssertionPlant<E>.() -> Unit): Boolean {
    val checkingPlant = AtriumFactory.newCheckingPlant(subject)
    checkingPlant.assertionCreator()
    return checkingPlant.allAssertionsHold()

}
