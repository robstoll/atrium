package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ENTRY_WHICH_IS

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries have
 * to appear in the [Iterable] but in any order -- an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the subject of the assertion for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected
 *   entries have to appear in the [Iterable] but in any order -- an entry is identified by an expected
 *   object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
class InAnyOrderOnlyValuesAssertionCreator<E, T : IterableLike>(
    converter: (T) -> Iterable<E>,
    searchBehaviour: InAnyOrderOnlySearchBehaviour
) : InAnyOrderOnlyAssertionCreator<E, T, E>(converter, searchBehaviour) {

    override fun createAssertionForSearchCriterionAndRemoveMatchFromList(
        searchCriterion: E,
        list: MutableList<E?>
    ): Pair<Boolean, Assertion> {
        val found: Boolean = list.remove(searchCriterion)
        return found to assertionBuilder.createDescriptive(AN_ENTRY_WHICH_IS, searchCriterion) { found }
    }
}
