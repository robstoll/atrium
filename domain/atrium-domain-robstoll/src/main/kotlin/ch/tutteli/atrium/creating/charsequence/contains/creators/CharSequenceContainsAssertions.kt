package ch.tutteli.atrium.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [ICharSequenceContainsAssertions].
 */
object CharSequenceContainsAssertions: ICharSequenceContainsAssertions {

    override fun <T : CharSequence> containsValues(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
        expected: Any,
        otherExpected: Array<out Any>
    ): AssertionGroup
        = _containsValues(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> containsValuesIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
        expected: Any,
        otherExpected: Array<out Any>
    ): AssertionGroup
        = _containsValuesIgnoringCase(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> containsDefaultTranslationOf(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup
        = _containsDefaultTranslationOf(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> containsDefaultTranslationOfIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup
        = _containsDefaultTranslationOfIgnoringCase(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> containsRegex(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup
        = _containsRegex(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> containsRegexIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup
        = _containsRegexIgnoringCase(checkerBuilder, expected, otherExpected)
}
