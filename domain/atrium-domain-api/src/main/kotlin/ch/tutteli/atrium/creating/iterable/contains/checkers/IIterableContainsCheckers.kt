package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Defines the minimum set of [IterableContains.Checker]s an implementation of the domain of Atrium
 * has to provide.
 */
interface IIterableContainsCheckers {
    fun newContainsAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): IterableContains.Checker

    fun newContainsAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): IterableContains.Checker

    fun newContainsExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): IterableContains.Checker

    fun newContainsNotChecker(): IterableContains.Checker
}
