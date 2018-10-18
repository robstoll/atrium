@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ENTRY_WHICH_IS

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries have
 * to appear in the [Iterable] but in any order -- an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected
 *   entries have to appear in the [Iterable] but in any order -- an entry is identified by an expected
 *   object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
@Deprecated("Please open an issue if you used this class, will be removed with 1.0.0")
class IterableContainsInAnyOrderOnlyObjectsAssertionCreator<E, T : Iterable<E?>>(
    searchBehaviour: IterableContainsInAnyOrderOnlySearchBehaviour
) : IterableContainsInAnyOrderOnlyAssertionCreator<E, T, E>(searchBehaviour) {

    override fun createAssertionForSearchCriterionAndRemoveMatchFromList(searchCriterion: E, list: MutableList<E?>): Pair<Boolean, Assertion> {
        val found: Boolean = list.remove(searchCriterion)
        return found to AssertImpl.builder.descriptive
            .withTest { found }
            .withDescriptionAndNullableRepresentation(AN_ENTRY_WHICH_IS, searchCriterion)
            .build()
    }
}
