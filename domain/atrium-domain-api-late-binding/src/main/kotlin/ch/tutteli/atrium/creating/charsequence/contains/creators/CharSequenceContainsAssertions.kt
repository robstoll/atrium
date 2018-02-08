package ch.tutteli.atrium.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.throwUnsupportedOperationException
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A dummy implementation of [ICharSequenceContainsAssertions] which should be replaced by an actual implementation.
 */
object CharSequenceContainsAssertions : ICharSequenceContainsAssertions {

    override fun <T : CharSequence> containsValues(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
        expected: Any,
        otherExpected: Array<out Any>
    ): AssertionGroup = throwUnsupportedOperationException()

    override fun <T : CharSequence> containsValuesIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
        expected: Any,
        otherExpected: Array<out Any>
    ): AssertionGroup = throwUnsupportedOperationException()

    override fun <T : CharSequence> containsDefaultTranslationOf(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup = throwUnsupportedOperationException()

    override fun <T : CharSequence> containsDefaultTranslationOfIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
        expected: Translatable,
        otherExpected: Array<out Translatable>
    ): AssertionGroup = throwUnsupportedOperationException()

    override fun <T : CharSequence> containsRegex(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup = throwUnsupportedOperationException()

    override fun <T : CharSequence> containsRegexIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
        expected: String,
        otherExpected: Array<out String>
    ): AssertionGroup = throwUnsupportedOperationException()
}
