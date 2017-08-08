package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator.IDecorator

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsExactlyChecker
import ch.tutteli.atrium.containsNot

open class CharSequenceContainsExactlyCheckerBuilder<T : CharSequence, D: IDecorator>(
    val times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsCheckerBuilder<T, D>(containsBuilder) {

    override val checkers: List<IChecker> = listOf(CharSequenceContainsExactlyChecker(
        times, containsBuilder.plant::containsNot.name, containsBuilder::exactly.name))
}
