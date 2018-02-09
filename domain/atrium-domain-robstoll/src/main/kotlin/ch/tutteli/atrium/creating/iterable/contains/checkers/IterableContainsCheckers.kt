package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Robstoll's implementation of [IIterableContainsCheckers].
 */
object IterableContainsCheckers : IIterableContainsCheckers {
    override fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): IterableContains.Checker
        = IterableContainsAtLeastChecker(times, nameContainsNotFun, atLeastCall)

    override fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): IterableContains.Checker
        = IterableContainsAtMostChecker(times, nameContainsNotFun, atMostCall)

    override fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): IterableContains.Checker
        = IterableContainsExactlyChecker(times, nameContainsNotFun, exactlyCall)

    override fun newNotChecker(): IterableContains.Checker
        = IterableContainsNotChecker()
}
