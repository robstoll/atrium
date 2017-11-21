package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInOrderOnlyEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    decorator: IterableContainsInOrderOnlyDecorator
) : IterableContainsInOrderOnlyAssertionCreator<E, T, IAssertionPlant<E>.() -> Unit>(decorator) {

    override fun createEntryAssertion(iterableAsList: List<E>, expected: IAssertionPlant<E>.() -> Unit, template: ((Boolean) -> IAssertion) -> IAssertionGroup): IAssertionGroup {
        val explanatoryAssertions = createExplanatoryAssertions(expected, iterableAsList)
        return template(createEntryFeatureAssertion(explanatoryAssertions))
    }

    private fun createEntryFeatureAssertion(explanatoryAssertions: List<IAssertion>): (Boolean) -> IAssertion
        = { found -> createEntryAssertion(explanatoryAssertions, found) }

    override fun holds(actual: E, expected: IAssertionPlant<E>.() -> Unit): Boolean
        = allCreatedAssertionsHold(actual, expected)
}
