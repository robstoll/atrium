package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
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
 * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject] for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 * @param S The search behaviour which should be applied to the input of the search.
 *
 * @constructor Represents a creator for sophisticated `contains` assertions for [CharSequence].
 * @param searchBehaviour The search behaviour.
 * @param searcher The search method which is used to search for given objects.
 * @param checkers The checkers which create assertions based on the search result.
 */
class CharSequenceContainsAssertionCreator<in T : CharSequence, in SC : Any, S : SearchBehaviour>(
    searchBehaviour: S,
    private val searcher: Searcher<S, SC>,
    checkers: List<Checker>,
    override val groupDescription: Translatable
) : ContainsObjectsAssertionCreator<T, String, SC, S, Checker>(searchBehaviour, checkers), Creator<T, SC> {

    override val descriptionContains: Translatable = DescriptionCharSequenceAssertion.CONTAINS
    override val descriptionNumberOfOccurrences: Translatable = DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES

    override fun getAssertionGroupType(): AssertionGroupType = DefaultListAssertionGroupType

    @Suppress("DEPRECATION" /* switch to Expect and remove this annotation with 1.0.0 */)
    override fun makeSubjectMultipleTimesConsumable(subjectProvider: SubjectProvider<T>): SubjectProvider<String> =
        ExpectImpl.changeSubject(subjectProvider).unreported { it.toString() }

    override fun search(subjectProvider: SubjectProvider<String>, searchCriterion: SC): Int =
        // if the maybeSubject is None it means we are in an explanation like context in which it does not matter if it is found or not.
        subjectProvider.maybeSubject.fold({ -1 }) { searcher.search(it, searchCriterion) }

    override fun decorateAssertion(
        subjectProvider: SubjectProvider<String>,
        featureAssertion: Assertion
    ): List<Assertion> = listOf(featureAssertion)
}
