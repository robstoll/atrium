package ch.tutteli.atrium.assertions.charsequence.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

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
) : ICharSequenceContains.ICreator<T, Any> {
    /**
     * Searches for the [expected] objects in the [plant]'s [subject][IAssertionPlant.subject] using the [searcher],
     * passes on the result to the [checkers] and creates an [IAssertionGroup] based on the resulting assertions.
     *
     * @param plant Its [subject][IAssertionPlant.subject] is used as input of the search.
     * @param expected The objects which are expected to be contained in the input of the search.
     *
     * @return An [IAssertionGroup] which contains the assertion created by the [checkers].
     */
    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: Any, otherExpected: Array<out Any>): IAssertionGroup {
        val assertions = mutableListOf<IAssertion>()
        listOf(expected, *otherExpected).forEach {
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

}
