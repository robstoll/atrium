package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAtLeastChecker
import ch.tutteli.atrium.containsNot

open class CharSequenceContainsAtLeastCheckerBuilder<T : CharSequence>(
    times: Int,
    containsBuilder: CharSequenceContainsBuilder<T>
) : CharSequenceContainsCheckerBuilder<T>(containsBuilder) {

    override val checkers: List<CharSequenceContainsAssertionCreator.IChecker<T>> = listOf(CharSequenceContainsAtLeastChecker(
        times, containsBuilder.plant::containsNot.name, containsBuilder::atLeast.name))
}
