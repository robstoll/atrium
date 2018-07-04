package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.creators.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion

/**
 * Represents a creator of sophisticated `contains` assertions for [CharSequence].
 *
 * A sophisticated `contains` assertion is build up by a desired [SearchBehaviour], a [Searcher] and a number of
 * [Checker]s. The [searcher] searches for specified objects -- considering the given [searchBehaviour] -- and
 * then passes on the result to the given [checkers] which in turn create the assertions representing the
 * corresponding check. Those created assertions are then grouped into an [AssertionGroup] which represents the
 * sophisticated assertion as a whole.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 * @param S The search behaviour which should be applied to the input of the search.
 *
 * @constructor Represents a creator for sophisticated `contains` assertions for [CharSequence].
 * @param searchBehaviour The search behaviour.
 * @param searcher The search method which is used to search for given objects.
 * @param checkers The checkers which create assertions based on the search result.
 */
class CharSequenceContainsAssertionCreator<in T : CharSequence, in SC: Any, S : SearchBehaviour>(
    searchBehaviour: S,
    private val searcher: Searcher<S>,
    checkers: List<Checker>,
    override val groupDescription: Translatable
) : ContainsObjectsAssertionCreator<T, SC, S, Checker>(searchBehaviour, checkers),
    CharSequenceContains.Creator<T, SC> {

    override val descriptionContains = DescriptionCharSequenceAssertion.CONTAINS
    override val descriptionNumberOfOccurrences = DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES

    override fun getAssertionGroupType() = DefaultListAssertionGroupType

    override fun search(plant: AssertionPlant<T>, searchCriterion: SC): Int
        = searcher.search(plant.subject, searchCriterion)

    override fun decorateAssertion(plant: AssertionPlant<T>, featureAssertion: Assertion) = listOf(featureAssertion)
}
