package ch.tutteli.atrium.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchers.IgnoringCaseIndexSearcher
import ch.tutteli.atrium.creating.charsequence.contains.searchers.IgnoringCaseRegexSearcher
import ch.tutteli.atrium.creating.charsequence.contains.searchers.IndexSearcher
import ch.tutteli.atrium.creating.charsequence.contains.searchers.RegexSearcher
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T : CharSequence> _containsValues(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, NoOpSearchBehaviour>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checkerBuilder, IndexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsValuesIgnoringCase(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, IgnoringCaseSearchBehaviour>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checkerBuilder, IgnoringCaseIndexSearcher(), expected, otherExpected)

private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> checkOnlyAllowedTypeAndCreateAssertionGroup(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup {
    listOf(expected, *otherExpected).forEach {
        require(it is CharSequence || it is Number || it is Char) {
            "Only CharSequence, Number and Char are allowed, `$it` given.\n" +
                "We provide an API with Any for convenience (so that you can mix String and Int for instance).\n" +
                "Use toString() if you really want to search for its toString()-representation."
        }
    }
    return createAssertionGroup(checkerBuilder, searcher, expected, otherExpected)
}

fun <T : CharSequence> _containsDefaultTranslationOf(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, NoOpSearchBehaviour>,
    expected: Translatable,
    otherExpected: Array<out Translatable>
): AssertionGroup
    = _containsValues(checkerBuilder, expected.getDefault(), mapDefaultTranslations(otherExpected))

fun <T : CharSequence> _containsDefaultTranslationOfIgnoringCase(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, IgnoringCaseSearchBehaviour>,
    expected: Translatable,
    otherExpected: Array<out Translatable>
): AssertionGroup
    = _containsValuesIgnoringCase(checkerBuilder, expected.getDefault(), mapDefaultTranslations(otherExpected))

private fun mapDefaultTranslations(otherExpected: Array<out Translatable>) =
    otherExpected.map { it.getDefault() }.toTypedArray()

fun <T : CharSequence> _containsRegex(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, NoOpSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): AssertionGroup
    = createAssertionGroup(checkerBuilder, RegexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsRegexIgnoringCase(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, IgnoringCaseSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): AssertionGroup
    = createAssertionGroup(checkerBuilder, IgnoringCaseRegexSearcher(), expected, otherExpected)

private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> createAssertionGroup(
    checkerBuilder: CharSequenceContains.CheckerBuilder<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: Any,
    otherExpected: Array<out Any>
): AssertionGroup {
    return CharSequenceContainsAssertionCreator<T, S>(checkerBuilder.containsBuilder.searchBehaviour, searcher, checkerBuilder.checkers)
        .createAssertionGroup(checkerBuilder.containsBuilder.plant, expected, otherExpected)
}
