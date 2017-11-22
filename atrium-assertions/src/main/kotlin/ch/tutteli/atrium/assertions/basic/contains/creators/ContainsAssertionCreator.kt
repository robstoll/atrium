package ch.tutteli.atrium.assertions.basic.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents the base class for [IContains.ICreator]s, providing a template to fulfill its job.
 *
 * @param T The type of the [IAssertionPlant.subject].
 * @param E The type of the search criteria.
 * @param C The type of the checkers in use (typically a sub interface of [IContains.IChecker]).
 *
 * @property checkers The [IContains.IChecker]s which shall be applied to the search result.
 *
 * @constructor Represents the base class for [IContains.ICreator]s, providing a template to fulfill its job.
 * @param checkers The [IContains.IChecker]s which shall be applied to the search result.
 */
abstract class ContainsAssertionCreator<T : Any, E, C : IContains.IChecker>(
    private val checkers: List<C>
) : IContains.ICreator<T, E> {

    override final fun createAssertionGroup(plant: IAssertionPlant<T>, expected: E, otherExpected: Array<out E>): IAssertionGroup {
        val assertions = listOf(expected, *otherExpected).map { createForExpected(plant, it) }
        return createAssertionGroup(assertions)
    }

    /**
     * Creates an [IAssertionGroup] representing the sophisticated `contains` assertion as a whole based on the given
     * [assertions].
     *
     * @param assertions The assertions representing search criteria passed to [createAssertionGroup].
     *
     * @return The newly created [IAssertionGroup].
     */
    //TODO rename fun is not really an overload of the above fun
    protected abstract fun createAssertionGroup(assertions: List<IAssertion>): IAssertionGroup

    private fun createForExpected(plant: IAssertionPlant<T>, expected: E): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            searchAndCreateAssertion(plant, expected, this::featureFactory)
        }
    }

    private fun featureFactory(count: Int, numberOfOccurrences: ITranslatable): IAssertionGroup {
        val assertions = checkers.map { it.createAssertion(count) }
        return AssertionGroup(FeatureAssertionGroupType, numberOfOccurrences, RawString(count.toString()), assertions)
    }

    /**
     * Searches for the given [expected] in the given [plant]'s [subject][IAssertionPlant.subject] and should
     * pass on the number of occurrences to the given [featureFactory] which creates feature assertions based on the
     * [checkers], which in turn can be used to create a resulting [IAssertionGroup] representing the assertion
     * for a search criteria as a whole.
     *
     * @param plant The plant for which the assertion is created.
     * @param expected A search criteria.
     * @param featureFactory The feature factory which should be called, passing the number of occurrences including a
     *        translation for `number of occurrences`.
     *
     * @return The newly created [IAssertionGroup].
     */
    protected abstract fun searchAndCreateAssertion(
        plant: IAssertionPlant<T>,
        expected: E,
        featureFactory: (numberOfOccurrences: Int, description: ITranslatable) -> IAssertionGroup
    ): IAssertionGroup
}
