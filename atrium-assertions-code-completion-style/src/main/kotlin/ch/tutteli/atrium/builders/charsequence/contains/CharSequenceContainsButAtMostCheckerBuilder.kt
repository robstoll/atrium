package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAtMostChecker
import ch.tutteli.atrium.containsNot

open class CharSequenceContainsButAtMostCheckerBuilder<T : CharSequence>(
    val times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilder<T>,
    containsBuilder: CharSequenceContainsBuilder<T>
) : CharSequenceContainsCheckerBuilder<T>(containsBuilder) {

    init {
        val nameAtLeastFun = containsBuilder::atLeast.name
        val nameButAtMostFun = atLeastBuilder::butAtMost.name
        val nameExactlyFun = containsBuilder::exactly.name
        if (atLeastBuilder.times == times) throw IllegalArgumentException(
            "use $nameExactlyFun($times) instead of $nameAtLeastFun($times).$nameButAtMostFun($times)")

        if (atLeastBuilder.times > times) throw IllegalArgumentException(
            "specifying $nameButAtMostFun($times) does not make sense if $nameAtLeastFun(${atLeastBuilder.times}) was used before")

    }

    override val checkers: List<CharSequenceContainsAssertionCreator.IChecker<T>> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        CharSequenceContainsAtMostChecker(times, containsBuilder.plant::containsNot.name, containsBuilder::atMost.name))
}
