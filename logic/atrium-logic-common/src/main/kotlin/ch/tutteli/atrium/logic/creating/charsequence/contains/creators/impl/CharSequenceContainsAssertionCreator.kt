package ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.logic.toAssertionContainer
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
 * @param T The type of the subject for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 * @param S The search behaviour which should be applied to the input of the search.
 *
 * @constructor Represents a creator for sophisticated `contains` assertions for [CharSequence].
 * @param searchBehaviour The search behaviour.
 * @param searcher The search method which is used to search for given objects.
 * @param checkers The checkers which create assertions based on the search result.
 */
class CharSequenceContainsAssertionCreator<T : CharSequence, in SC : Any, S : SearchBehaviour>(
    searchBehaviour: S,
    private val searcher: Searcher<S, SC>,
    checkers: List<Checker>,
    override val groupDescription: Translatable
) : ContainsObjectsAssertionCreator<T, String, SC, S, Checker>(searchBehaviour, checkers), Creator<T, SC> {

    override val descriptionContains: Translatable = DescriptionCharSequenceAssertion.CONTAINS
    override val descriptionNumberOfOccurrences: Translatable = DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES
    override val descriptionNotFound: Translatable = DescriptionCharSequenceAssertion.NOT_FOUND
    override val descriptionNumberOfElementsFound: Translatable = DescriptionCharSequenceAssertion.NUMBER_OF_MATCHES_FOUND

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<String> =
        container.changeSubject.unreported { it.toString() }.toAssertionContainer()

    override fun search(multiConsumableContainer: AssertionContainer<String>, searchCriterion: SC): Int =
        // if the maybeSubject is None it means we are in an explanation like context in which
        // it should not matter what this number is. Moreover, we check in the atMostChecker that times is >= 0
        multiConsumableContainer.maybeSubject.fold({ -1 }) { searcher.search(it, searchCriterion) }
}
