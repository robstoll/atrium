package ch.tutteli.atrium.logic.creating.basic.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.Contains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.impl.createAssertionGroupFromListOfAssertions
import ch.tutteli.atrium.logic.impl.createExplanatoryGroupForMismatches
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the base class for [Contains.Creator]s which use bare objects as search criteria (matching them
 * with `==`).
 *
 * It provides a template to fulfill the job of creating the sophisticated `contains` [Assertion].
 *
 * @param T The type of the subject of this expectation.
 * @param TT The type of the subject of this expectation after making it multiple times consumable.
 * @param SC The type of the search criteria.
 * @param S The type of the current [Contains.SearchBehaviour].
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 *
 * @property searchBehaviour The chosen search behaviour.
 *
 * @constructor Represents the base class for [Contains.Creator]s which use bare objects as search criteria (matching them
 *   with `==`).
 * @param searchBehaviour The chosen search behaviour.
 * @param checkers The [Contains.Checker]s which shall be applied to the search result.
 */
abstract class ContainsObjectsAssertionCreator<T : Any, TT : Any, in SC, S : Contains.SearchBehaviour, C : Contains.Checker>(
    searchBehaviour: S,
    checkers: List<C>
) : ContainsAssertionCreator<T, TT, SC, C>(searchBehaviour, checkers) {

    override fun searchAndCreateAssertion(
        multiConsumableContainer: AssertionContainer<TT>,
        searchCriterion: SC,
        featureFactory: (Int, Translatable) -> AssertionGroup
    ): AssertionGroup {
        val assertions = mutableListOf<Assertion>()
        if (searchBehaviour is NotSearchBehaviour) {
            val mismatches = mismatchesForNotSearchBehaviour(multiConsumableContainer, searchCriterion)
            if (mismatches.isNotEmpty()) assertions.add(createExplanatoryGroupForMismatches(mismatches))
        } else {
            val count = search(multiConsumableContainer, searchCriterion)
            val featureAssertion = featureFactory(count, descriptionNumberOfOccurrences)
            assertions.add(featureAssertion)
        }

        return createAssertionGroupFromListOfAssertions(groupDescription, searchCriterion, assertions)
    }

    /**
     * Provides the translation for `number of occurrences`.
     */
    protected abstract val descriptionNumberOfOccurrences: Translatable

    /**
     * Provides the translation for [AssertionGroup.description]
     */
    protected abstract val groupDescription: Translatable


    /**
     * Searches for something matching the given [searchCriterion] in the subject of the given
     * [multiConsumableContainer] and returns the number of occurrences.
     *
     * @param multiConsumableContainer The provider of the subject of this expectation in which we shall look for something
     *   matching the given [searchCriterion].
     * @param searchCriterion The search criterion used to determine whether something matches or not.
     *
     * @return The number of times the [searchCriterion] matched in the subject of this expectation.
     */
    protected abstract fun search(multiConsumableContainer: AssertionContainer<TT>, searchCriterion: SC): Int

    /**
     * Finds the mismatched indices and values when the [searchBehaviour] is `NotSearchBehaviour` in the subject of the
     * given [multiConsumableContainer] and creates a list of assertions about the mismatched indexed values
     *
     * @param multiConsumableContainer The provider of the subject of this expectation in which we shall look for something
     *   not matching the given [searchCriterion].
     * @param searchCriterion The search criterion used to determine whether something matches or not.
     *
     * @return A list of [Assertion]s that describe the indexed values that did not match the [searchCriterion]
     */
    protected open fun mismatchesForNotSearchBehaviour(
        multiConsumableContainer: AssertionContainer<TT>,
        searchCriterion: SC
    ): List<Assertion> = emptyList()
}
