package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Robstoll's implementation of [IIterableContainsCheckers].
 */
object IterableContainsCheckers : IIterableContainsCheckers {
    override fun newContainsAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): IterableContains.Checker
        = IterableContainsAtLeastChecker(times, nameContainsNotFun, atLeastCall)

    override fun newContainsAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): IterableContains.Checker
        = IterableContainsAtMostChecker(times, nameContainsNotFun, atMostCall)

    override fun newContainsExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): IterableContains.Checker
        = IterableContainsExactlyChecker(times, nameContainsNotFun, exactlyCall)

    override fun newContainsNotChecker(): IterableContains.Checker
        = IterableContainsNotChecker()
}
