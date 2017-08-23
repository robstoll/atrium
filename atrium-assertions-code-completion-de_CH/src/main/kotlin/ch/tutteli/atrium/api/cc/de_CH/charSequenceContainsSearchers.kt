package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseRegexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsRegexSearcher
import ch.tutteli.atrium.creating.IAssertionPlant

fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.wert(expected: Any): IAssertionPlant<T>
    = werte(expected)

fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.werte(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIndexSearcher(), expected, otherExpected)

@JvmName("valueIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.wert(expected: Any): IAssertionPlant<T>
    = werte(expected)

@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.werte(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIgnoringCaseIndexSearcher(), expected, otherExpected)

fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.regex(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsRegexSearcher(), expected, otherExpected)

@JvmName("regexIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.regex(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIgnoringCaseRegexSearcher(), expected, otherExpected)
