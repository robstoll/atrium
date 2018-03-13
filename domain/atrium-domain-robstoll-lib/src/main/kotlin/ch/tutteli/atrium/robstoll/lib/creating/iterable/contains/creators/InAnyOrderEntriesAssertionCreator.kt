package ch.tutteli.atrium.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.robstoll.lib.creating.basic.contains.creators.ContainsAssertionCreator
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ENTRY_WHICH

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by holding a group of assertions, created by an assertion creator lambda.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where expected entries
 *   can appear in any order and are identified by holding a group of assertions, created by an assertion
 *   creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
class InAnyOrderEntriesAssertionCreator<out E : Any, in T : Iterable<E?>>(
    private val searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableContains.Checker>
) : ContainsAssertionCreator<T, (AssertionPlant<E>.() -> Unit)?, IterableContains.Checker>(checkers),
    IterableContains.Creator<T, (AssertionPlant<E>.() -> Unit)?> {

    override fun createAssertionGroupForSearchCriteriaAssertions(assertions: List<Assertion>): AssertionGroup {
        val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
        return AssertionBuilder.list.create(description, RawString.EMPTY, assertions)
    }

    override fun searchAndCreateAssertion(plant: AssertionPlant<T>, searchCriterion: (AssertionPlant<E>.() -> Unit)?, featureFactory: (Int, Translatable) -> AssertionGroup): AssertionGroup {
        val (explanatoryAssertions, count) = createExplanatoryAssertionsAndMatchingCount(plant.subject.iterator(), searchCriterion)
        val featureAssertion = featureFactory(count, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES)
        return AssertionBuilder.list.create(AN_ENTRY_WHICH, RawString.EMPTY, listOf(
            AssertionBuilder.explanatoryGroup.withDefault.create(explanatoryAssertions),
            featureAssertion
        ))
    }

    private fun createExplanatoryAssertionsAndMatchingCount(itr: Iterator<E?>, assertionCreator: (AssertionPlant<E>.() -> Unit)?): Pair<List<Assertion>, Int> {
        return if (itr.hasNext()) {
            val (firstNonNullOrNull, sequence) = getFirstNonNullAndSequence(itr, sequenceOf())
            val group = collectIterableAssertionsForExplanationWithFirst(assertionCreator, firstNonNullOrNull)
            val count = sequence.count { allCreatedAssertionsHold(it, assertionCreator) }
            group to count
        } else {
            val group = collectIterableAssertionsForExplanation(assertionCreator, null)
            group to 0
        }
    }

    private tailrec fun getFirstNonNullAndSequence(itr: Iterator<E?>, sequence: Sequence<E?>): Pair<E?, Sequence<E?>> {
        return if (itr.hasNext()) {
            val first = itr.next()
            if (first != null) {
                first to sequenceOf(first) + itr.asSequence()
            } else {
                getFirstNonNullAndSequence(itr, sequence + sequenceOf(first))
            }
        } else {
            null to sequence
        }
    }
}
