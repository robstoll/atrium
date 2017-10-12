package ch.tutteli.atrium.assertions.iterable.contains

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES
import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

class IterableContainsAssertionCreator<E, T : Iterable<E>, D : IDecorator>(
    private val decorator: D,
    private val searcher: ISearcher<E, T, D>,
    private val checkers: List<IChecker>
) {

    fun create(plant: IAssertionPlant<T>, vararg expected: E): IAssertionGroup {
        val assertions = mutableListOf<IAssertion>()
        expected.forEach {
            assertions.add(create(plant, it))
        }
        return InvisibleAssertionGroup(assertions.toList())
    }

    private fun create(plant: IAssertionPlant<T>, expected: E): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val description = decorator.decorateDescription(CONTAINS)
            val count = searcher.search(plant.subject, bothNullOrEqual(expected))
            val assertions = mutableListOf<IAssertion>()
            checkers.forEach {
                assertions.add(it.createAssertion(count))
            }
            val featureAssertion = AssertionGroup(FeatureAssertionGroupType, NUMBER_OF_OCCURRENCES, RawString(count.toString()), assertions.toList())
            AssertionGroup(ListAssertionGroupType, description, expected ?: RawString.NULL, listOf(featureAssertion))
        }
    }

    private fun <E> bothNullOrEqual(expected: E) = { it: E ->
        (it == null && expected == null) || it == expected
    }


    interface IDecorator {
        fun decorateDescription(description: ITranslatable): ITranslatable
    }


    interface ISearcher<E, T : Iterable<E>, D : IDecorator> {
        fun search(searchIn: T, searchFor: (E) -> Boolean): Int
    }


    interface IChecker {
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }
}
