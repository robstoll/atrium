package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *              expected entries have to appear in the specified order and where an entry is identified by an
 *              expected object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *        decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
class IterableContainsInOrderOnlyObjectsAssertionCreator<E, T : Iterable<E>>(
    searchBehaviour: IterableContainsInOrderOnlySearchBehaviour
) : IterableContainsInOrderOnlyAssertionCreator<E, T, E>(searchBehaviour) {

    override fun createEntryAssertion(iterableAsList: List<E>, searchCriterion: E, template: ((Boolean) -> Assertion) -> AssertionGroup): AssertionGroup
        = template(createEntryFeatureAssertion(searchCriterion))

    private fun createEntryFeatureAssertion(searchCriterion: E): (Boolean) -> Assertion
        = { found -> BasicDescriptiveAssertion(DescriptionAnyAssertion.TO_BE, searchCriterion ?: RawString.NULL, found) }

    override fun matches(actual: E, searchCriterion: E): Boolean
        = actual == searchCriterion
}
