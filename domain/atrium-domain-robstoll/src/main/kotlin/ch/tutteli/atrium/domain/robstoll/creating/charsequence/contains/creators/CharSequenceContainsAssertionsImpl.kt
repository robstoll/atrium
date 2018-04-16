package ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators.*
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [CharSequenceContainsAssertions].
 */
class CharSequenceContainsAssertionsImpl: CharSequenceContainsAssertions {

    override fun <T : CharSequence> values(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Any>
    ): AssertionGroup
        = _containsValues(checkerOption, expected)

    override fun <T : CharSequence> valuesIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: Any,
        otherExpected: Array<out Any>
    ): AssertionGroup
        = _containsValuesIgnoringCase(checkerOption, expected, otherExpected)

    override fun <T : CharSequence> defaultTranslationOf(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup
        = _containsDefaultTranslationOf(checkerOption, expected, otherExpected)

    override fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup
        = _containsDefaultTranslationOfIgnoringCase(checkerOption, expected, otherExpected)

    override fun <T : CharSequence> regex(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup
        = _containsRegex(checkerOption, expected, otherExpected)

    override fun <T : CharSequence> regexIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup
        = _containsRegexIgnoringCase(checkerOption, expected, otherExpected)
}
