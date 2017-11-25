package ch.tutteli.atrium.assertions.basic.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents the base class for [IContains.ICreator]s which use bare objects as search criteria (matching them
 * with `==`).
 *
 * It provides a template to fulfill the job of creating the sophisticated `contains` assertion.
 *
 * @param T The type of the [IAssertionPlant.subject].
 * @param S The type of the search criteria.
 * @param D The type of the current [IContains.IDecorator].
 * @param C The type of the checkers in use (typically a sub interface of [IContains.IChecker]).
 *
 * @constructor Represents the base class for [IContains.ICreator]s which use bare objects as search criteria (matching them
 * with `==`).
 * @param decorator The chosen search behaviour.
 * @param checkers The [IContains.IChecker]s which shall be applied to the search result.
 */
abstract class ContainsObjectsAssertionCreator<T : Any, S, D : IContains.IDecorator, C : IContains.IChecker>(
    private val decorator: D,
    checkers: List<C>
) : ContainsAssertionCreator<T, S, C>(checkers) {

    override fun createAssertionGroupForSearchCriteriaAssertions(assertions: List<IAssertion>): IAssertionGroup
        = InvisibleAssertionGroup(assertions)

    override final fun searchAndCreateAssertion(plant: IAssertionPlant<T>, searchCriterion: S, featureFactory: (Int, ITranslatable) -> IAssertionGroup): IAssertionGroup {
        val count = search(plant, searchCriterion)
        val featureAssertion = featureFactory(count, descriptionNumberOfOccurrences)
        val description = decorator.decorateDescription(descriptionContains)
        return AssertionGroup(ListAssertionGroupType, description, searchCriterion ?: RawString.NULL, listOf(featureAssertion))
    }

    /**
     * Provides the translation for `contains`.
     */
    protected abstract val descriptionContains: ITranslatable

    /**
     * Provides the translation for `number of occurrences`.
     */
    protected abstract val descriptionNumberOfOccurrences: ITranslatable

    /**
     * Searches for something matching the given [searchCriterion] in the given [plant]'s
     * [subject][IAssertionPlant.subject] and returns the number of occurrences.
     *
     * @param plant The plant or rather its [subject][IAssertionPlant.subject] in which we shall look for something
     *        matching the given [searchCriterion].
     * @param searchCriterion The search criterion used to determine whether something matches or not.
     *
     * @return The number of times the [searchCriterion] matched in the [plant]'s [subject][IAssertionPlant.subject].
     */
    protected abstract fun search(plant: IAssertionPlant<T>, searchCriterion: S): Int
}
