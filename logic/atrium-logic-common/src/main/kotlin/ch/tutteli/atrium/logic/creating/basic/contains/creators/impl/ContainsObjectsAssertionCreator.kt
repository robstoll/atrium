package ch.tutteli.atrium.logic.creating.basic.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.Contains
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
        val count = search(multiConsumableContainer, searchCriterion)
        val featureAssertion = featureFactory(count, descriptionNumberOfOccurrences)

        return assertionBuilder.list
            .withDescriptionAndRepresentation(groupDescription, searchCriterion)
            .withAssertion(featureAssertion)
            .build()
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
}
