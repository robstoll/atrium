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
 * @param E The type of the search criteria.
 * @param D The type of the current [IContains.IDecorator].
 * @param C The type of the checkers in use (typically a sub interface of [IContains.IChecker]).
 *
 * @constructor Represents the base class for [IContains.ICreator]s which use bare objects as search criteria (matching them
 * with `==`).
 * @param decorator The chosen search behaviour.
 * @param checkers The [IContains.IChecker]s which shall be applied to the search result.
 */
abstract class ContainsObjectsAssertionCreator<T : Any, E, D : IContains.IDecorator, C : IContains.IChecker>(
    private val decorator: D,
    checkers: List<C>
) : ContainsAssertionCreator<T, E, C>(checkers) {

    override fun createAssertionGroup(assertions: List<IAssertion>): IAssertionGroup
        = InvisibleAssertionGroup(assertions)

    override final fun searchAndCreateAssertion(plant: IAssertionPlant<T>, expected: E, featureFactory: (Int, ITranslatable) -> IAssertionGroup): IAssertionGroup {
        val count = search(plant, expected)
        val featureAssertion = featureFactory(count, numberOfOccurrences)
        val description = decorator.decorateDescription(descriptionContains)
        return AssertionGroup(ListAssertionGroupType, description, expected ?: RawString.NULL, listOf(featureAssertion))
    }

    /**
     * Provides the translation for `contains`.
     */
    protected abstract val descriptionContains: ITranslatable
    //TODO rename to descriptionNumOfOccurrences
    /**
     * Provides the translation for `number of occurrences`.
     */
    protected abstract val numberOfOccurrences: ITranslatable

    /**
     * Searches for the [expected] object in the given [plant]'s [subject][IAssertionPlant.subject] and returns the
     * number of occurrences.
     *
     * @param plant The plant or rather its [subject][IAssertionPlant.subject] in which the [expected] object shall be
     *        searched for.
     * @param expected The object which is expected to be contained in the [plant]'s [subject][IAssertionPlant.subject].
     *
     * @return The number of occurrences of [expected] in the [plant]'s [subject][IAssertionPlant.subject].
     */
    protected abstract fun search(plant: IAssertionPlant<T>, expected: E): Int
}
