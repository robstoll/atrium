package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.impl.createHasElementAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by expected objects (equality comparison).
 *
 * @param T The type of the subject of the assertion for which the `contains` assertion is be build.
 * @param SC The type of the elements of the iterable, used as search criteria.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where expected entries
 *   can appear in any order and are identified by expected objects (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
class InAnyOrderValuesAssertionCreator<SC, T : IterableLike>(
    private val converter: (T) -> Iterable<SC>,
    searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableLikeContains.Checker>
) : ContainsObjectsAssertionCreator<T, List<SC>, SC, InAnyOrderSearchBehaviour, IterableLikeContains.Checker>(
    searchBehaviour,
    checkers
), IterableLikeContains.Creator<T, SC> {

    override val descriptionContains: Translatable = DescriptionIterableAssertion.CONTAINS
    override val descriptionNumberOfOccurrences: Translatable = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES
    override val groupDescription: Translatable = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS

    override fun getAssertionGroupType(): AssertionGroupType {
        return if (searchBehaviour is NotSearchBehaviour) {
            DefaultSummaryAssertionGroupType
        } else {
            DefaultListAssertionGroupType
        }
    }

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<List<SC>> =
        turnSubjectToList(container, converter)

    override fun search(multiConsumableContainer: AssertionContainer<List<SC>>, searchCriterion: SC): Int =
        multiConsumableContainer.maybeSubject.fold({ -1 }) { subject -> subject.filter { it == searchCriterion }.size }

    override fun decorateAssertion(
        container: AssertionContainer<List<SC>>,
        featureAssertion: Assertion
    ): List<Assertion> {
        return if (searchBehaviour is NotSearchBehaviour) {
            listOf(
                featureAssertion,
                createHasElementAssertion(container.maybeSubject.getOrElse { emptyList() }.iterator())
            )
        } else {
            listOf(featureAssertion)
        }
    }
}
