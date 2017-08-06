package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator

abstract class CharSequenceContainsCheckerBuilder<T : CharSequence>(val containsBuilder: CharSequenceContainsBuilder<T>) {
    abstract val checkers: List<CharSequenceContainsAssertionCreator.IChecker<T>>
}
