package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.creators.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.collectIterableAssertionsForExplanationWithFirst
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createHasElementAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by expected objects (equality comparison).
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 * @param SC The type of the elements of the iterable, used as search criteria.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where expected entries
 *   can appear in any order and are identified by expected objects (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
class InAnyOrderValuesAssertionCreator<SC, in T : Iterable<SC>>(
    searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableContains.Checker>
) : ContainsObjectsAssertionCreator<T, SC, InAnyOrderSearchBehaviour, IterableContains.Checker>(
    searchBehaviour,
    checkers
), IterableContains.Creator<T, SC> {

    override val descriptionContains = DescriptionIterableAssertion.CONTAINS
    override val descriptionNumberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES
    override val groupDescription = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS

    override fun getAssertionGroupType(): AssertionGroupType {
        return if (searchBehaviour is NotSearchBehaviour) {
            DefaultSummaryAssertionGroupType
        } else {
            DefaultListAssertionGroupType
        }
    }

    override fun search(plant: AssertionPlant<T>, searchCriterion: SC): Int
        = plant.subject.filter { it == searchCriterion }.size

    override fun decorateAssertion(plant: AssertionPlant<T>, featureAssertion: Assertion): List<Assertion> {
        return if (searchBehaviour is NotSearchBehaviour) {
            listOf(featureAssertion, createHasElementAssertion(plant.subject))
        } else {
            listOf(featureAssertion)
        }
    }
}
