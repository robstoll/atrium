package ch.tutteli.atrium.assertions.charsequence.contains

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents a creator of sophisticated `contains` assertions for [CharSequence].
 *
 * A sophisticated `contains` assertion is build up by a [IDecorator] behaviour, a [ISearcher] and a number of
 * [IChecker]s. The [searcher] searches for specified objects, considering the given [decorator] and then passes on the
 * result to the given [checkers] which in turn create the assertions representing the corresponding check. The so
 * created assertions are then group by an [IAssertionGroup].
 *
 * @param T The type of the [IAssertionPlant.subject] for which the `contains` assertion is be build.
 * @param D The decoration behaviour which should be applied to the input of the search.
 *
 * @constructor Represents a creator for sophisticated `contains` assertions for [CharSequence].
 * @param decorator The decoration behaviour.
 * @param searcher The search method which is used to search for given objects.
 * @param checkers The checkers which create assertions based on the search result.
 */
class CharSequenceContainsAssertionCreator<T : CharSequence, D : IDecorator>(
    private val decorator: D,
    private val searcher: ISearcher<D>,
    private val checkers: List<IChecker>
) {
    /**
     * Searches for the [expected] objects in the [plant]'s [subject][IAssertionPlant.subject] using the [searcher],
     * passes on the result to the [checkers] and creates an [IAssertionGroup] based on the resulting assertions.
     *
     * @param plant Its [subject][IAssertionPlant.subject] is used as input of the search.
     * @param expected The objects which are expected to be contained in the input of the search.
     *
     * @return An [IAssertionGroup] which contains the assertion created by the [checkers].
     */
    fun create(plant: IAssertionPlant<T>, vararg expected: Any): IAssertionGroup {
        val assertions = mutableListOf<IAssertion>()
        expected.forEach {
            assertions.add(create(plant, it))
        }
        return InvisibleAssertionGroup(assertions.toList())
    }

    private fun create(plant: IAssertionPlant<T>, expected: Any): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val description = decorator.decorateDescription(CONTAINS)
            val count = searcher.search(plant.subject, expected)
            val assertions = mutableListOf<IAssertion>()
            checkers.forEach {
                assertions.add(it.createAssertion(count))
            }
            val featureAssertion = AssertionGroup(FeatureAssertionGroupType, NUMBER_OF_OCCURRENCES, RawString(count.toString()), assertions.toList())
            AssertionGroup(ListAssertionGroupType, description, expected, listOf(featureAssertion))
        }
    }

    /**
     * Represents a decoration behaviour of the input [CharSequence] of the search but leaves it up to the [ISearcher]
     * to implement the behaviour -- yet, it provides a method to decorate the description (an [ITranslatable]) of the
     * resulting [IAssertion] produced by [CharSequenceContainsAssertionCreator].
     */
    interface IDecorator {
        /**
         * Decorates the given [description] and returns the result.
         *
         * @return The decorated [description].
         */
        fun decorateDescription(description: ITranslatable): ITranslatable
    }

    /**
     * Represents a searcher which supports the decoration behaviour [D] for a given input [CharSequence] of the search.
     *
     * @param D The decoration behaviour which should be applied to the input [CharSequence] in which the [ISearcher]
     *          will look for something -- the actual decorator implementation happens in the [ISearcher]; [IDecorator]
     *          only decorates [ITranslatable] for reporting.
     */
    interface ISearcher<D : IDecorator> {
        /**
         * Searches in the given [searchIn] for the given [searchFor], using its [toString][Any.toString]
         * implementation, and returns the number of occurrences.
         *
         * Whether searches are disjoint or non-disjoint is up to the implementation.
         *
         * @param searchIn The input [CharSequence] in which this [ISearcher] shall search
         * @param searchFor The object which shall be found
         *
         * @return The number of occurrences of [searchFor] in [searchIn].
         */
        fun search(searchIn: CharSequence, searchFor: Any): Int
    }

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input string.
     *
     * It provides the method [createAssertion] which creates a corresponding Assertion which represents this check.
     */
    interface IChecker {
        /**
         * Creates an [IAssertion] representing the check based on the given [foundNumberOfTimes] which is the result
         * of the search (usually produced by an [ISearcher]).
         *
         * @return The newly created [IAssertion].
         */
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }
}
