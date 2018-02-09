package ch.tutteli.atrium.assertions.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseRegexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsRegexSearcher
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("use CharSequenceContainsAssertions.values, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsAssertions.values(checker, expected, *otherExpected)", "ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions"))
fun <T : CharSequence> _containsValues(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checker, CharSequenceContainsIndexSearcher(), expected, otherExpected)

@Deprecated("use CharSequenceContainsAssertions.valuesIgnoringCase, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsAssertions.valuesIgnoringCase(checker, expected, *otherExpected)", "ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions"))
fun <T : CharSequence> _containsValuesIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checker, CharSequenceContainsIgnoringCaseIndexSearcher(), expected, otherExpected)

private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> checkOnlyAllowedTypeAndCreateAssertionGroup(
    checker: CharSequenceContainsCheckerBuilder<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup {
    listOf(expected, *otherExpected).forEach {
        require(it is CharSequence || it is Number || it is Char) {
            "Only CharSequence, Number and Char are allowed, `$it` given.\nWe provide an API with Any for convenience (so that you can mix String and Int for instance)."
        }
    }
    return createAssertionGroup(checker, searcher, expected, otherExpected)
}

@Deprecated("use CharSequenceContainsAssertions.defaultTranslationOf, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsAssertions.defaultTranslationOf(checker, expected, *otherExpected)", "ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions"))
fun <T : CharSequence> _containsDefaultTranslationOf(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
    expected: Translatable,
    otherExpected: Array<out Translatable>
): AssertionGroup
    = _containsValues(checker, expected.getDefault(), mapDefaultTranslations(otherExpected))

@Deprecated("use CharSequenceContainsAssertions.defaultTranslationOfIgnoringCase, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsAssertions.defaultTranslationOfIgnoringCase(checker, expected, *otherExpected)", "ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions"))
fun <T : CharSequence> _containsDefaultTranslationOfIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
    expected: Translatable,
    otherExpected: Array<out Translatable>
): AssertionGroup
    = _containsValuesIgnoringCase(checker, expected.getDefault(), mapDefaultTranslations(otherExpected))

private fun mapDefaultTranslations(otherExpected: Array<out Translatable>) =
    otherExpected.map { it.getDefault() }.toTypedArray()

@Deprecated("use CharSequenceContainsAssertions.regex, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsAssertions.regex(checker, expected, *otherExpected)", "ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions"))
fun <T : CharSequence> _containsRegex(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): AssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsRegexSearcher(), expected, otherExpected)

@Deprecated("use CharSequenceContainsAssertions.regexIgnoringCase, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsAssertions.regexIgnoringCase(checker, expected, *otherExpected)", "ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions"))
fun <T : CharSequence> _containsRegexIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): AssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsIgnoringCaseRegexSearcher(), expected, otherExpected)

private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> createAssertionGroup(
    checker: CharSequenceContainsCheckerBuilder<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup {
    return CharSequenceContainsAssertionCreator<T, S>(checker.containsBuilder.searchBehaviour, searcher, checker.checkers)
        .createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}
