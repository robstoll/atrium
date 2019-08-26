@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *   expected entries have to appear in the specified order and where an entry is identified by an
 *   expected object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
@Deprecated("Please open an issue if you used this class; will be removed with 1.0.0")
class IterableContainsInOrderOnlyObjectsAssertionCreator<E, T : Iterable<E?>>(
    searchBehaviour: IterableContainsInOrderOnlySearchBehaviour
) : IterableContainsInOrderOnlyAssertionCreator<E, T, E>(searchBehaviour) {

    override fun createEntryAssertion(iterableAsList: List<E?>, searchCriterion: E, template: ((Boolean) -> Assertion) -> AssertionGroup): AssertionGroup
        = template(createEntryFeatureAssertion(searchCriterion))

    private fun createEntryFeatureAssertion(searchCriterion: E): (Boolean) -> Assertion
        = { found -> AssertImpl.builder.createDescriptive(TO_BE, searchCriterion) { found } }

    override fun matches(actual: E?, searchCriterion: E): Boolean
        = actual == searchCriterion
}
