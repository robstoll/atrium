package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.DefaultSummaryAssertionGroupType
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.creators.ContainsAssertionCreator
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.allCreatedAssertionsHold
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createExplanatoryAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createHasElementAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ENTRY_WHICH

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by holding a group of assertions, created by an assertion creator lambda.
 *
 * @param T The type of the subject of the assertion for which the `contains` assertion is be build.
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
class InAnyOrderEntriesAssertionCreator<E : Any, in T : Iterable<E?>>(
    searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableContains.Checker>
) : ContainsAssertionCreator<T, (Expect<E>.() -> Unit)?, IterableContains.Checker>(searchBehaviour, checkers),
    IterableContains.Creator<T, (Expect<E>.() -> Unit)?> {

    override fun searchAndCreateAssertion(
        subjectProvider: SubjectProvider<T>,
        searchCriterion: (Expect<E>.() -> Unit)?,
        featureFactory: (Int, Translatable) -> AssertionGroup
    ): AssertionGroup {
        val iterable = subjectProvider.maybeSubject.getOrElse { emptyList<E?>() }
        val hasElementAssertion = createHasElementAssertion(iterable)
        val (explanatoryGroup, count) =
            createExplanatoryAssertionsAndMatchingCount(iterable.iterator(), searchCriterion)

        val featureAssertion = featureFactory(count, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES)
        val assertions = mutableListOf(explanatoryGroup, featureAssertion)

        val groupType = if (searchBehaviour is NotSearchBehaviour) {
            assertions.add(hasElementAssertion)
            DefaultSummaryAssertionGroupType
        } else {
            DefaultListAssertionGroupType
        }

        return ExpectImpl.builder.customType(groupType)
            .withDescriptionAndEmptyRepresentation(AN_ENTRY_WHICH)
            .withAssertions(assertions)
            .build()
    }

    private fun createExplanatoryAssertionsAndMatchingCount(itr: Iterator<E?>, assertionCreator: (Expect<E>.() -> Unit)?): Pair<AssertionGroup, Int> {
        val (firstNonNullOrNull, sequence) = if (itr.hasNext() && assertionCreator != null) {
            // we search the first non-null element in order that feature assertions which are based on the subject can be showed properly in the explanation
            getFirstNonNullAndSequence(itr, sequenceOf())
        } else {
            null to itr.asSequence()
        }
        val group = createExplanatoryAssertionGroup(assertionCreator) { firstNonNullOrNull?.let { Some(it) } ?: None }
        val count = sequence.count { allCreatedAssertionsHold(it, assertionCreator) }
        return group to count
    }

    private tailrec fun getFirstNonNullAndSequence(itr: Iterator<E?>, sequence: Sequence<E?>): Pair<E?, Sequence<E?>> {
        return if (itr.hasNext()) {
            val first = itr.next()
            if (first != null) {
                first to sequence + sequenceOf(first) + itr.asSequence()
            } else {
                getFirstNonNullAndSequence(itr, sequence + sequenceOf(first))
            }
        } else {
            null to sequence
        }
    }
}
