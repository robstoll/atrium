package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInOrderOnlyEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    decorator: IterableContainsInOrderOnlySearchBehaviour
) : IterableContainsInOrderOnlyAssertionCreator<E, T, IAssertionPlant<E>.() -> Unit>(decorator) {

    override fun createEntryAssertion(iterableAsList: List<E>, searchCriterion: IAssertionPlant<E>.() -> Unit, template: ((Boolean) -> IAssertion) -> IAssertionGroup): IAssertionGroup {
        val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, iterableAsList)
        return template(createEntryFeatureAssertion(explanatoryAssertions))
    }

    private fun createEntryFeatureAssertion(explanatoryAssertions: List<IAssertion>): (Boolean) -> IAssertion
        = { found -> createEntryAssertion(explanatoryAssertions, found) }

    override fun matches(actual: E, searchCriterion: IAssertionPlant<E>.() -> Unit): Boolean
        = allCreatedAssertionsHold(actual, searchCriterion)
}
