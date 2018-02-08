package ch.tutteli.atrium.creating.charsequence.contains.checkers

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

/**
 * Defines the minimum set of [CharSequenceContains.Checker]s an implementation of the domain of Atrium
 * has to provide.
 */
interface ICharSequenceContainsCheckers {
    fun newContainsAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): CharSequenceContains.Checker

    fun newContainsAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): CharSequenceContains.Checker

    fun newContainsExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): CharSequenceContains.Checker

    fun newContainsNotChecker(): CharSequenceContains.Checker
}
