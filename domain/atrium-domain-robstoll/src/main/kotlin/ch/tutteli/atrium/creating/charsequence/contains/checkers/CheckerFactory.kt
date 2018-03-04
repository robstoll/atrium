package ch.tutteli.atrium.creating.charsequence.contains.checkers

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Robstoll's implementation of [ICheckerFactory].
 */
object CheckerFactory : ICheckerFactory {

    override fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): CharSequenceContains.Checker
        = CharSequenceContainsAtLeastChecker(times, nameContainsNotFun, atLeastCall)

    override fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): CharSequenceContains.Checker
        = CharSequenceContainsAtMostChecker(times, nameContainsNotFun, atMostCall)

    override fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): CharSequenceContains.Checker
        = CharSequenceContainsExactlyChecker(times, nameContainsNotFun, exactlyCall)

    override fun newNotChecker(): CharSequenceContains.Checker
        = CharSequenceContainsNotChecker()
}
