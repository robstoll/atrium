package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtMostChecker

abstract class CharSequenceContainsButAtMostCheckerBuilderBase<T : CharSequence, D : IDecorator>(
    val times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilderBase<T, D>,
    containsBuilder: CharSequenceContainsBuilder<T, D>,
    nameContainsNotFun: String,
    nameAtMostFun: String,
    nameAtLeastFun: String,
    nameButAtMostFun: String,
    nameExactlyFun: String
) : CharSequenceContainsCheckerBuilder<T, D>(containsBuilder) {

    init {
        if (atLeastBuilder.times == times) throw IllegalArgumentException(
            "use $nameExactlyFun($times) instead of $nameAtLeastFun($times).$nameButAtMostFun($times)")

        if (atLeastBuilder.times > times) throw IllegalArgumentException(
            "specifying $nameButAtMostFun($times) does not make sense if $nameAtLeastFun(${atLeastBuilder.times}) was used before")

    }

    override val checkers: List<IChecker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        CharSequenceContainsAtMostChecker(times, nameContainsNotFun, nameAtMostFun))
}
