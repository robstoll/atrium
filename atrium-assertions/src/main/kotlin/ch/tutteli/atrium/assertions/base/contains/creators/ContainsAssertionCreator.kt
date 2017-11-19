package ch.tutteli.atrium.assertions.base.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.base.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

abstract class ContainsAssertionCreator<T : Any, E, C : IContains.IChecker>(
    private val checkers: List<C>
) : IContains.ICreator<T, E> {

    override final fun createAssertionGroup(plant: IAssertionPlant<T>, expected: E, otherExpected: Array<out E>): IAssertionGroup {
        val assertions = listOf(expected, *otherExpected).map { createForExpected(plant, it) }
        return createAssertionGroup(assertions)
    }

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
     * [checkers] which then can be used to create a resulting [IAssertionGroup] representing the assertion as a whole.
     *
     * @param plant The plant for which the assertion is created
     * @param expected The search input.
     * @param featureFactory The feature factory which should be called.
     */
    protected abstract fun searchAndCreateAssertion(
        plant: IAssertionPlant<T>,
        expected: E,
        featureFactory: (count: Int, numberOfOccurrences: ITranslatable) -> IAssertionGroup
    ): IAssertionGroup
}
