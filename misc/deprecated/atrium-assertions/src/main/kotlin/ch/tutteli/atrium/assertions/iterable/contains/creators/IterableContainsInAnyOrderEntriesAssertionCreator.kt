@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.creators.ContainsAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ENTRY_WHICH

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by holding a group of assertions, created by an assertion creator lambda.
 *
 * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject] for which the `contains` assertion is be build.
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
@Deprecated("Please open an issue if you used this class; will be removed with 1.0.0")
open class IterableContainsInAnyOrderEntriesAssertionCreator<E : Any, T : Iterable<E?>>(
    private val searchBehaviour: IterableContainsInAnyOrderSearchBehaviour,
    checkers: List<ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.Checker>
) : ContainsAssertionCreator<T, (AssertionPlant<E>.() -> Unit)?, ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.Checker>(checkers),
    IterableContains.Creator<T, (AssertionPlant<E>.() -> Unit)?> {

    final override fun createAssertionGroupForSearchCriteriaAssertions(assertions: List<Assertion>): AssertionGroup {
        val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
        return AssertImpl.builder.list
            .withDescriptionAndEmptyRepresentation(description)
            .withAssertions(assertions)
            .build()
    }

    final override fun searchAndCreateAssertion(subjectProvider: SubjectProvider<T>, searchCriterion: (AssertionPlant<E>.() -> Unit)?, featureFactory: (Int, Translatable) -> AssertionGroup): AssertionGroup {
        val (explanatoryAssertions, count) = createExplanatoryAssertionsAndMatchingCount(subjectProvider.subject.iterator(), searchCriterion)
        val explanatoryGroup = AssertImpl.builder.explanatoryGroup
            .withDefaultType
            .withAssertions(explanatoryAssertions)
            .build()
        val featureAssertion = featureFactory(count, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES)
        return AssertImpl.builder.list
            .withDescriptionAndEmptyRepresentation(AN_ENTRY_WHICH)
            .withAssertions(explanatoryGroup, featureAssertion)
            .build()

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
