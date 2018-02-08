package ch.tutteli.atrium.creating.charsequence.contains.checkers

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Robstoll's implementation of [ICharSequenceContainsCheckers].
 */
object CharSequenceContainsCheckers : ICharSequenceContainsCheckers {
    override fun newContainsAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): CharSequenceContains.Checker
        = CharSequenceContainsAtLeastChecker(times, nameContainsNotFun, atLeastCall)

    override fun newContainsAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): CharSequenceContains.Checker
        = CharSequenceContainsAtMostChecker(times, nameContainsNotFun, atMostCall)

    override fun newContainsExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): CharSequenceContains.Checker
        = CharSequenceContainsExactlyChecker(times, nameContainsNotFun, exactlyCall)

    override fun newContainsNotChecker(): CharSequenceContains.Checker
        = CharSequenceContainsNotChecker()
}
