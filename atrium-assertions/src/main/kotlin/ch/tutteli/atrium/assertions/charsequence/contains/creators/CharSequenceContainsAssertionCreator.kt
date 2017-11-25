package ch.tutteli.atrium.assertions.charsequence.contains.creators

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.creators.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.*
import ch.tutteli.atrium.creating.IAssertionPlant

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
    decorator: D,
    private val searcher: ISearcher<D>,
    checkers: List<IChecker>
) : ContainsObjectsAssertionCreator<T, Any, D, IChecker>(decorator, checkers),
    ICharSequenceContains.ICreator<T, Any> {

    override val descriptionContains = DescriptionCharSequenceAssertion.CONTAINS
    override val numberOfOccurrences = DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES

    override fun search(plant: IAssertionPlant<T>, searchCriterion: Any): Int
        = searcher.search(plant.subject, searchCriterion)
}
