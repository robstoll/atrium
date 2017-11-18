package ch.tutteli.atrium.assertions.base.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.base.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

abstract class ContainsObjectsAssertionCreator<T : Any, E, D : IContains.IDecorator, C : IContains.IChecker>(
    private val decorator: D,
    private val checkers: List<C>
) : IContains.ICreator<T, E> {

    override final fun createAssertionGroup(plant: IAssertionPlant<T>, expected: E, otherExpected: Array<out E>): IAssertionGroup {
        val assertions = listOf(expected, *otherExpected).map { create(plant, it) }
        return InvisibleAssertionGroup(assertions)
    }

    private fun create(plant: IAssertionPlant<T>, expected: E): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            val count = search(plant, expected)
            val assertions = checkers.map { it.createAssertion(count) }
            val featureAssertion = AssertionGroup(FeatureAssertionGroupType, numberOfOccurrences, RawString(count.toString()), assertions)
            AssertionGroup(ListAssertionGroupType, description, expected ?: RawString.NULL, listOf(featureAssertion))
        }
    }

    protected abstract val numberOfOccurrences: ITranslatable
    protected abstract fun search(plant: IAssertionPlant<T>, expected: E): Int
}
