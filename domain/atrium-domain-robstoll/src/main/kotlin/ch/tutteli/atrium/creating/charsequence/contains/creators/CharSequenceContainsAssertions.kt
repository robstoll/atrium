package ch.tutteli.atrium.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [ICharSequenceContainsAssertions].
 */
object CharSequenceContainsAssertions: ICharSequenceContainsAssertions {

    override fun <T : CharSequence> values(
        checkerBuilder: CharSequenceContains.CheckerBuilder<T, NoOpSearchBehaviour>,
        expected: Any,
        otherExpected: Array<out Any>
    ): AssertionGroup
        = _containsValues(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> valuesIgnoringCase(
        checkerBuilder: CharSequenceContains.CheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        expected: Any,
        otherExpected: Array<out Any>
    ): AssertionGroup
        = _containsValuesIgnoringCase(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> defaultTranslationOf(
        checkerBuilder: CharSequenceContains.CheckerBuilder<T, NoOpSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup
        = _containsDefaultTranslationOf(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerBuilder: CharSequenceContains.CheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup
        = _containsDefaultTranslationOfIgnoringCase(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> regex(
        checkerBuilder: CharSequenceContains.CheckerBuilder<T, NoOpSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup
        = _containsRegex(checkerBuilder, expected, otherExpected)

    override fun <T : CharSequence> regexIgnoringCase(
        checkerBuilder: CharSequenceContains.CheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup
        = _containsRegexIgnoringCase(checkerBuilder, expected, otherExpected)
}
