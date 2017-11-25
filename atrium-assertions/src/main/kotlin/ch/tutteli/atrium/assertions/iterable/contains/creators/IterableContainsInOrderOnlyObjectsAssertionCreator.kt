package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.reporting.RawString

class IterableContainsInOrderOnlyObjectsAssertionCreator<E, T : Iterable<E>>(
    decorator: IterableContainsInOrderOnlySearchBehaviour
) : IterableContainsInOrderOnlyAssertionCreator<E, T, E>(decorator) {

    override fun createEntryAssertion(iterableAsList: List<E>, searchCriterion: E, template: ((Boolean) -> IAssertion) -> IAssertionGroup): IAssertionGroup
        = template(createEntryFeatureAssertion(searchCriterion))

    private fun createEntryFeatureAssertion(searchCriterion: E): (Boolean) -> IAssertion
        = { found -> BasicAssertion(DescriptionAnyAssertion.TO_BE, searchCriterion ?: RawString.NULL, found) }

    override fun matches(actual: E, searchCriterion: E): Boolean
        = actual == searchCriterion
}
