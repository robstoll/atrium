package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsExactlyChecker
import ch.tutteli.atrium.containsNot

open class CharSequenceContainsExactlyCheckerBuilder<T : CharSequence>(
    val times: Int,
    containsBuilder: CharSequenceContainsBuilder<T>
) : CharSequenceContainsCheckerBuilder<T>(containsBuilder) {

    override val checkers: List<CharSequenceContainsAssertionCreator.IChecker<T>> = listOf(CharSequenceContainsExactlyChecker(
        times, containsBuilder.plant::containsNot.name, containsBuilder::exactly.name))
}
