package ch.tutteli.atrium.assertions.charsequence

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

class CharSequenceContainsAssertionCreator<T : CharSequence>(
    val decorator: IDecorator<T>,
    val searcher: ISearcher<T>,
    val checkers: List<IChecker<T>>
) {
    fun create(plant: IAssertionPlant<T>, vararg expected: Any): IAssertionGroup {
        val assertions = mutableListOf<IAssertion>()
        expected.forEach {
            //TODO lazy assertions
            assertions.add(create(plant, it))
        }
        return InvisibleAssertionGroup(assertions)
    }

    private fun create(plant: IAssertionPlant<T>, expected: Any): IAssertionGroup {
        val (description, decoratedSearchIn) = decorator.decorate(plant.subject)
        val count = searcher.search(decoratedSearchIn, expected)
        val assertions = mutableListOf<IAssertion>()
        checkers.forEach {
            assertions.add(it.createAssertion(count))
        }
        val featureAssertion = AssertionGroup(FeatureAssertionGroupType, DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES, count, assertions.toList())
        return AssertionGroup(ListAssertionGroupType, description, expected, listOf(featureAssertion))
    }

    interface IDecorator<C : CharSequence> {
        fun decorate(searchIn: CharSequence): Pair<ITranslatable, CharSequence>
    }

    class NothingDecorator<C : CharSequence> : IDecorator<C> {
        override fun decorate(searchIn: CharSequence) = DescriptionCharSequenceAssertion.CONTAINS to searchIn
    }

    interface ISearcher<C : CharSequence> {
        fun search(searchIn: CharSequence, searchFor: Any): Int
    }

    interface IChecker<C : CharSequence> {
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }
}
