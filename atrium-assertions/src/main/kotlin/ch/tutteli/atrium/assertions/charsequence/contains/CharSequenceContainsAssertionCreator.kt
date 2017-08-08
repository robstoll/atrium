package ch.tutteli.atrium.assertions.charsequence.contains

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

class CharSequenceContainsAssertionCreator<T : CharSequence, D : CharSequenceContainsAssertionCreator.IDecorator>(
    val decorator: D,
    val searcher: ISearcher<D>,
    val checkers: List<IChecker>
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
        val description = decorator.decorateDescription(CONTAINS)
        val count = searcher.search(plant.subject, expected)
        val assertions = mutableListOf<IAssertion>()
        checkers.forEach {
            assertions.add(it.createAssertion(count))
        }
        val featureAssertion = AssertionGroup(FeatureAssertionGroupType, NUMBER_OF_OCCURRENCES, count, assertions.toList())
        return AssertionGroup(ListAssertionGroupType, description, expected, listOf(featureAssertion))
    }

    /**
     * Represents a decoration behaviour of the input [CharSequence] of the search but leaves it up to the [ISearcher]
     * to implement the behaviour -- yet, it provides a method to decorate the description (an [ITranslatable]) of the
     * resulting [IAssertion] produces by [CharSequenceContainsAssertionCreator].
     */
    interface IDecorator {
        fun decorateDescription(description: ITranslatable): ITranslatable
    }

    /**
     * Represents a searcher which supports a certain decoration behaviour of the input [CharSequence] of the search.
     *
     * @param D The decoration behaviour which should be applied to the input [CharSequence] in which the [ISearcher]
     *          will look for something -- the actual decorator implementation happens in the [ISearcher]; [IDecorator]
     *          only decorates [ITranslatable] for reporting.
     */
    interface ISearcher<D : IDecorator> {
        fun search(searchIn: CharSequence, searchFor: Any): Int
    }

    interface IChecker {
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }
}
