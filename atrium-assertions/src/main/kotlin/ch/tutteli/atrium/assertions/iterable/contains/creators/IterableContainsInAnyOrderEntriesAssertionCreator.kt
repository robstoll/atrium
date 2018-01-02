package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.AN_ENTRY_WHICH
import ch.tutteli.atrium.assertions.basic.contains.creators.ContainsAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by holding a group of assertions, created by an assertion creator lambda.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *           decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where expected entries
 *              can appear in any order and are identified by holding a group of assertions, created by an assertion
 *              creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *        decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
open class IterableContainsInAnyOrderEntriesAssertionCreator<E : Any, T : Iterable<E?>>(
    private val searchBehaviour: IterableContainsInAnyOrderSearchBehaviour,
    checkers: List<IterableContains.Checker>
) : ContainsAssertionCreator<T, (AssertionPlant<E>.() -> Unit)?, IterableContains.Checker>(checkers),
    IterableContains.Creator<T, (AssertionPlant<E>.() -> Unit)?> {

    override fun createAssertionGroupForSearchCriteriaAssertions(assertions: List<Assertion>): AssertionGroup {
        val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
        return AssertionGroup.Builder.list.create(description, RawString.EMPTY, assertions)
    }

    override fun searchAndCreateAssertion(plant: AssertionPlant<T>, searchCriterion: (AssertionPlant<E>.() -> Unit)?, featureFactory: (Int, Translatable) -> AssertionGroup): AssertionGroup {
        val itr = plant.subject.iterator()
        val (explanatoryAssertions, count) = createExplanatoryAssertionsAndMatchingCount(itr, searchCriterion)
        val featureAssertion = featureFactory(count, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES)
        return AssertionGroup.Builder.list.create(AN_ENTRY_WHICH, RawString.EMPTY, listOf(
            AssertionGroup.Builder.explanatory.withDefault.create(explanatoryAssertions),
            featureAssertion
        ))
    }

    private fun createExplanatoryAssertionsAndMatchingCount(itr: Iterator<E?>, assertionCreator: (AssertionPlant<E>.() -> Unit)?): Pair<List<Assertion>, Int> {
        return if (itr.hasNext()) {
            val first = itr.next()
            val group = collectIterableAssertionsForExplanationWithFirst(assertionCreator, first)
            val sequence = sequenceOf(first) + itr.asSequence()
            val count = sequence.count { allCreatedAssertionsHold(it, assertionCreator) }
            group to count
        } else {
            val group = collectIterableAssertionsForExplanation(assertionCreator, null)
            group to 0
        }
    }
}
