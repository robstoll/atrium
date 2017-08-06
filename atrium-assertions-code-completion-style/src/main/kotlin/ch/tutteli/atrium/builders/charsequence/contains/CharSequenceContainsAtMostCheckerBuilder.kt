package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAtMostChecker
import ch.tutteli.atrium.containsNot

open class CharSequenceContainsAtMostCheckerBuilder<T : CharSequence>(
    val times: Int,
    containsBuilder: CharSequenceContainsBuilder<T>
) : CharSequenceContainsCheckerBuilder<T>(containsBuilder) {

    override val checkers: List<CharSequenceContainsAssertionCreator.IChecker<T>> = listOf(CharSequenceContainsAtMostChecker(
        times, containsBuilder.plant::containsNot.name, containsBuilder::atMost.name))
}
