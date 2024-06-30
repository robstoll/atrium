//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl.IgnoringCaseIndexSearcher
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl.IgnoringCaseRegexSearcher
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl.IndexSearcher
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl.RegexSearcher
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

class DefaultCharSequenceContainsAssertions : CharSequenceContainsAssertions {
    override fun <T : CharSequence> values(
        checkerStepLogic: CheckerStepLogic<T, NoOpSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): AssertionGroup =
        checkOnlyAllowedTypeNotEmptyStringAndCreateAssertionGroup(checkerStepLogic, IndexSearcher(), expected)

    override fun <T : CharSequence> valuesIgnoringCase(
        checkerStepLogic: CheckerStepLogic<T, IgnoringCaseSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): AssertionGroup =
        checkOnlyAllowedTypeNotEmptyStringAndCreateAssertionGroup(checkerStepLogic, IgnoringCaseIndexSearcher(), expected)

    private fun <T : CharSequence, S : SearchBehaviour> checkOnlyAllowedTypeNotEmptyStringAndCreateAssertionGroup(
        checkerStepLogic: CheckerStepLogic<T, S>,
        searcher: Searcher<S, CharSequenceOrNumberOrChar>,
        expected: List<CharSequenceOrNumberOrChar>
    ): AssertionGroup {
        require(expected.isNotEmpty()) {
            "You have to specify at least one search criterion for a CharSequence contains assertion"
        }
        expected.forEach {
            require(it is CharSequence || it is Number || it is Char) {
                "Only values of type CharSequence, Number and Char are allowed\nGiven: $it\n" +
                    "We provide an API with CharSequenceOrNumberOrChar (typealias for Any) for convenience (so that you can mix String and Int for instance).\n" +
                    "Use toString() if you really want to search for its toString()-representation."
            }
            require(it != "") {
                "Searching for the empty string does not make sense. You probably forgot to specify the search criterion."
            }
            require(it.toString() != "") {
                "Searching for an empty CharSequence does not make sense. You probably forgot to specify the search criterion."
            }
        }
        return createAssertionGroup(checkerStepLogic, searcher, expected, DescriptionCharSequenceExpectation.VALUE)
    }

    override fun <T : CharSequence> regex(
        checkerStepLogic: CheckerStepLogic<T, NoOpSearchBehaviour>,
        expected: List<Regex>
    ): AssertionGroup = createAssertionGroup(
        checkerStepLogic,
        RegexSearcher(),
        expected,
        DescriptionCharSequenceExpectation.STRING_MATCHING_REGEX
    )

    override fun <T : CharSequence> regexIgnoringCase(
        checkerStepLogic: CheckerStepLogic<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ): AssertionGroup = createAssertionGroup(
        checkerStepLogic,
        IgnoringCaseRegexSearcher(),
        expected,
        DescriptionCharSequenceExpectation.STRING_MATCHING_REGEX
    )

    //TODO 1.3.0 replace with InlineElement and remove suppression
    @Suppress("DEPRECATION")
    private fun <T : CharSequence, SC : CharSequenceOrNumberOrChar, S : SearchBehaviour> createAssertionGroup(
        checkerStepLogic: CheckerStepLogic<T, S>,
        searcher: Searcher<S, SC>,
        expected: List<SC>,
        groupDescription: ch.tutteli.atrium.reporting.translating.Translatable
    ): AssertionGroup {
        val creator = CharSequenceContainsAssertionCreator<T, SC, S>(
            checkerStepLogic.entryPointStepLogic.searchBehaviour,
            searcher,
            checkerStepLogic.checkers,
            groupDescription
        )
        return creator.createAssertionGroup(checkerStepLogic.entryPointStepLogic.container, expected)
    }
}
