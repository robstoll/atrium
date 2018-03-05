package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Robstoll's implementation of [ICheckerFactory].
 */
object CheckerFactory : ICheckerFactory {

    override fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): IterableContains.Checker
        = AtLeastChecker(times, nameContainsNotFun, atLeastCall)

    override fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): IterableContains.Checker
        = AtMostChecker(times, nameContainsNotFun, atMostCall)

    override fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): IterableContains.Checker
        = ExactlyChecker(times, nameContainsNotFun, exactlyCall)

    override fun newNotChecker(): IterableContains.Checker
        = NotChecker()
}
