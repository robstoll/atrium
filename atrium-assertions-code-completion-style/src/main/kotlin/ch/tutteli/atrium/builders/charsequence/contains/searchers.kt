package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsIndexSearcher
import ch.tutteli.atrium.creating.IAssertionPlant

fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T>.value(expected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIndexSearcher(), expected, arrayOf())

fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T>.values(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIndexSearcher(), expected, otherExpected)

private fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T>.addAssertion(
    searcher: CharSequenceContainsIndexSearcher<T>,
    expected: Any, otherExpected: Array<out Any>): IAssertionPlant<T> {

    val assertionGroup = CharSequenceContainsAssertionCreator(containsBuilder.decorator, searcher, checkers)
        .create(containsBuilder.plant, expected, *otherExpected)
    return containsBuilder.plant.addAssertion(assertionGroup)
}
