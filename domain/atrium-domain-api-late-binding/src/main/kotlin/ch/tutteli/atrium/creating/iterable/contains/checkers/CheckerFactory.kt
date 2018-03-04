package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.throwUnsupportedOperationException

/**
 * A dummy implementation of [ICheckerFactory] which should be replaced by an actual implementation.
 */
object CheckerFactory : ICheckerFactory {
    override fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): IterableContains.Checker = throwUnsupportedOperationException()

    override fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): IterableContains.Checker = throwUnsupportedOperationException()

    override fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): IterableContains.Checker = throwUnsupportedOperationException()

    override fun newNotChecker(): IterableContains.Checker
        = throwUnsupportedOperationException()
}
