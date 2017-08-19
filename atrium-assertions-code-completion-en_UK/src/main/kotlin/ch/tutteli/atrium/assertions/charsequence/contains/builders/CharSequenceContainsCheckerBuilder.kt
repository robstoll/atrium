package ch.tutteli.atrium.assertions.charsequence.contains.builders


import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator

abstract class CharSequenceContainsCheckerBuilder<T : CharSequence, D: IDecorator>(
    val containsBuilder: CharSequenceContainsBuilder<T, D>
) {
    abstract val checkers: List<IChecker>
}
