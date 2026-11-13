package ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.NotChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.toAssertionContainer
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

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
    private val checkers: List<Checker>,
    override val groupDescription: Translatable
) : ContainsObjectsAssertionCreator<T, String, SC, S, Checker>(searchBehaviour, checkers), Creator<T, SC> {

    override val descriptionToContain: Translatable = DescriptionCharSequenceExpectation.TO_CONTAIN

    override val descriptionNumberOfOccurrences: Translatable = DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES
    override val descriptionNotFound: Translatable = DescriptionCharSequenceExpectation.NOT_FOUND
    override val descriptionNumberOfElementsFound: Translatable =
        DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES_FOUND

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<String> =
        container.changeSubject.unreported { it.toString() }.toAssertionContainer()

    override fun search(multiConsumableContainer: AssertionContainer<String>, searchCriterion: SC): Int =
        // if the maybeSubject is None it means we are in an explanation like context in which
        // it should not matter what this number is. Moreover, we check in the atMostChecker that times is >= 0
        multiConsumableContainer.maybeSubject.fold({ -1 }) { searcher.search(it, searchCriterion) }

    override fun searchAndCreateAssertion(
        multiConsumableContainer: AssertionContainer<String>,
        searchCriterion: SC,
        featureFactory: (Int, Translatable) -> AssertionGroup
    ): AssertionGroup {
        val assertionGroup = super.searchAndCreateAssertion(multiConsumableContainer, searchCriterion, featureFactory)
        // a `not to contain` expectation cannot fail because its search criterion did not match -- hence we do not
        // provide a hint in this case. Any other failing expectation only involves different line separators if the
        // expectation would have been met when the search criterion had used \r\n instead of \n.
        // note, we check the checkers as well since a NotSearchBehaviour is no longer observable once
        // `ignoringCase` was used (it is then wrapped in an IgnoringCaseSearchBehaviour)
        if (
            assertionGroup.holds() ||
            searchBehaviour is NotSearchBehaviour ||
            checkers.any { it is NotChecker } ||
            searchCriterion !is CharSequence
        ) {
            return assertionGroup
        }
        val expected = searchCriterion.toString()
        val expectedWithFixedLineSeparators = expected.replaceLfWithCrLf()
        if (expectedWithFixedLineSeparators == expected) return assertionGroup

        @Suppress("UNCHECKED_CAST")
        val assertionGroupWithFixedLineSeparators = super.searchAndCreateAssertion(
            multiConsumableContainer,
            expectedWithFixedLineSeparators as SC,
            featureFactory
        )
        if (!assertionGroupWithFixedLineSeparators.holds()) return assertionGroup

        return assertionBuilder.invisibleGroup
            .withAssertions(
                assertionGroup,
                assertionBuilder.explanatoryGroup
                    .withHintType
                    .withExplanatoryAssertion(
                        DescriptionCharSequenceExpectation.WARNING_DIFFERENT_LINE_SEPARATORS
                    )
                    .build()
            )
            .build()
    }
}

/**
 * Replaces every `\n` which is not preceded by a `\r` with `\r\n`.
 */
internal fun String.replaceLfWithCrLf(): String {
    val sb = StringBuilder(length)
    var index = 0
    while (index < length) {
        val ch = this[index]
        sb.append(if (ch == '\n' && (index == 0 || this[index - 1] != '\r')) "\r\n" else ch)
        ++index
    }
    return sb.toString()
}
