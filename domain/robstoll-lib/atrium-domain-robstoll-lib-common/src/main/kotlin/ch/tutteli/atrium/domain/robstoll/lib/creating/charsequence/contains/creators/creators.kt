package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IgnoringCaseIndexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IgnoringCaseRegexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IndexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.RegexSearcher
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.STRING_MATCHING_REGEX
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.VALUE

fun <T : CharSequence> _containsValues(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: List<Any>
): AssertionGroup = checkOnlyAllowedTypeNotEmptyStringAndCreateAssertionGroup(
    checkerOption,
    IndexSearcher(),
    expected.map { it.toString() }
)

fun <T : CharSequence> _containsValuesIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: List<Any>
): AssertionGroup = checkOnlyAllowedTypeNotEmptyStringAndCreateAssertionGroup(
    checkerOption,
    IgnoringCaseIndexSearcher(),
    expected.map { it.toString() }
)

private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> checkOnlyAllowedTypeNotEmptyStringAndCreateAssertionGroup(
    checkerOption: CharSequenceContains.CheckerOption<T, S>,
    searcher: CharSequenceContains.Searcher<S, String>,
    expected: List<Any>
): AssertionGroup {
    require(expected.isNotEmpty()) {
        "You have to specify at least one search criterion for a CharSequence contains assertion"
    }
    expected.forEach {
        require(it is CharSequence || it is Number || it is Char) {
            "Only values of type CharSequence, Number and Char are allowed\nGiven: $it\n" +
                "We provide an API with Any for convenience (so that you can mix String and Int for instance).\n" +
                "Use toString() if you really want to search for its toString()-representation."
        }
        require(it != "") {
            "Searching for the empty string does not make sense. You probably forgot to specify the search criterion."
        }
        require(it.toString() != "") {
            "Searching for an empty CharSequence does not make sense. You probably forgot to specify the search criterion."
        }
    }
    return createAssertionGroup(checkerOption, searcher, expected.map { it.toString() }, VALUE)
}

fun <T : CharSequence> _containsDefaultTranslationOf(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: List<Translatable>
): AssertionGroup = _containsValues(checkerOption, expected.map { it.getDefault() })

fun <T : CharSequence> _containsDefaultTranslationOfIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: List<Translatable>
): AssertionGroup = _containsValuesIgnoringCase(checkerOption, expected.map { it.getDefault() })

fun <T : CharSequence> _containsRegex(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: List<Regex>
): AssertionGroup = createAssertionGroup(checkerOption, RegexSearcher(), expected, STRING_MATCHING_REGEX)

fun <T : CharSequence> _containsRegexIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: List<String>
): AssertionGroup = createAssertionGroup(
    checkerOption,
    IgnoringCaseRegexSearcher(),
    expected.map { it.toRegex() },
    STRING_MATCHING_REGEX
)

private fun <T : CharSequence, SC : Any, S : CharSequenceContains.SearchBehaviour> createAssertionGroup(
    checkerOption: CharSequenceContains.CheckerOption<T, S>,
    searcher: CharSequenceContains.Searcher<S, SC>,
    expected: List<SC>,
    groupDescription: Translatable
): AssertionGroup {
    val creator = CharSequenceContainsAssertionCreator<T, SC, S>(
        checkerOption.containsBuilder.searchBehaviour,
        searcher,
        checkerOption.checkers,
        groupDescription
    )
    return creator.createAssertionGroup(checkerOption.containsBuilder.subjectProvider, expected)
}
