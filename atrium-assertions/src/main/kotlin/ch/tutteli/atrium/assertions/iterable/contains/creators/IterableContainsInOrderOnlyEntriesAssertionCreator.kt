package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by holding a group of assertions,
 * created by an assertion creator lambda.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *              expected entries have to appear in the specified order and where an entry is identified by holding a
 *              group of assertions, created by an assertion creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *        decorate the description (an [ITranslatable]) which is used for the [IAssertionGroup].
 */
class IterableContainsInOrderOnlyEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    searchBehaviour: IterableContainsInOrderOnlySearchBehaviour
) : IterableContainsInOrderOnlyAssertionCreator<E, T, AssertionPlant<E>.() -> Unit>(searchBehaviour) {

    override fun createEntryAssertion(iterableAsList: List<E>, searchCriterion: AssertionPlant<E>.() -> Unit, template: ((Boolean) -> IAssertion) -> IAssertionGroup): IAssertionGroup {
        val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, iterableAsList)
        return template(createEntryFeatureAssertion(explanatoryAssertions))
    }

    private fun createEntryFeatureAssertion(explanatoryAssertions: List<IAssertion>): (Boolean) -> IAssertion
        = { found -> createEntryAssertion(explanatoryAssertions, found) }

    override fun matches(actual: E, searchCriterion: AssertionPlant<E>.() -> Unit): Boolean
        = allCreatedAssertionsHold(actual, searchCriterion)
}
