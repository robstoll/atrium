package ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.checkers

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.checkers.CheckerFactory
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.checkers.AtMostChecker
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.checkers.NotChecker

/**
 * Robstoll's implementation of [CheckerFactory].
 */
class CheckerFactoryImpl : CheckerFactory {

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
