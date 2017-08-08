package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.*
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.creating.IAssertionPlant

fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.value(expected: Any): IAssertionPlant<T>
    = values(expected)

fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.values(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIndexSearcher(), expected, otherExpected)

@JvmName("valueIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.value(expected: Any): IAssertionPlant<T>
    = values(expected)

@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.values(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIgnoringCaseIndexSearcher(), expected, otherExpected)


private fun <T : CharSequence, D : IDecorator> CharSequenceContainsCheckerBuilder<T, D>.addAssertion(
    searcher: ISearcher<D>,
    expected: Any, otherExpected: Array<out Any>): IAssertionPlant<T> {

    val assertionGroup = CharSequenceContainsAssertionCreator<T, D>(containsBuilder.decorator, searcher, checkers)
        .create(containsBuilder.plant, expected, *otherExpected)
    return containsBuilder.plant.addAssertion(assertionGroup)
}
