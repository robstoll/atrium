package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.creators.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by expected objects (equality comparison).
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 * @param S The type of the elements of the iterable, used as search criterion.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where expected entries
 *   can appear in any order and are identified by expected objects (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
@Deprecated("Please open an issue if you used this class, will be removed with 1.0.0")
class IterableContainsInAnyOrderObjectsAssertionCreator<S, T : Iterable<S>>(
    searchBehaviour: IterableContainsInAnyOrderSearchBehaviour,
    checkers: List<ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.Checker>
) : ContainsObjectsAssertionCreator<T, S, IterableContainsInAnyOrderSearchBehaviour, ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.Checker>(searchBehaviour, checkers),
    IterableContains.Creator<T, S> {

    override val descriptionContains = DescriptionIterableAssertion.CONTAINS
    override val descriptionNumberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES

    override fun search(plant: AssertionPlant<T>, searchCriterion: S): Int
        = plant.subject.filter({ it == searchCriterion }).size
}
