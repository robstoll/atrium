package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtMostChecker
import ch.tutteli.atrium.containsNot

open class CharSequenceContainsButAtMostCheckerBuilder<T : CharSequence, D: IDecorator>(
    val times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilder<T, D>,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsCheckerBuilder<T, D>(containsBuilder) {

    init {
        val nameAtLeastFun = containsBuilder::atLeast.name
        val nameButAtMostFun = atLeastBuilder::butAtMost.name
        val nameExactlyFun = containsBuilder::exactly.name
        if (atLeastBuilder.times == times) throw IllegalArgumentException(
            "use $nameExactlyFun($times) instead of $nameAtLeastFun($times).$nameButAtMostFun($times)")

        if (atLeastBuilder.times > times) throw IllegalArgumentException(
            "specifying $nameButAtMostFun($times) does not make sense if $nameAtLeastFun(${atLeastBuilder.times}) was used before")

    }

    override val checkers: List<IChecker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        CharSequenceContainsAtMostChecker(times, containsBuilder.plant::containsNot.name, containsBuilder::atMost.name))
}
