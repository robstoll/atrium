package ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.ISearcher
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


private fun <T : CharSequence, D : IDecorator> CharSequenceContainsCheckerBuilder<T, D>.addAssertion(
    searcher: ISearcher<D>,
    expected: Any, otherExpected: Array<out Any>): IAssertionPlant<T> {

    val assertionGroup = CharSequenceContainsAssertionCreator<T, D>(containsBuilder.decorator, searcher, checkers)
        .create(containsBuilder.plant, expected, *otherExpected)
    return containsBuilder.plant.addAssertion(assertionGroup)
}
