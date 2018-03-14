package ch.tutteli.atrium.robstoll.creating.charsequence.contains.checkers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.checkers.CheckerFactory
import ch.tutteli.atrium.robstoll.lib.creating.charsequence.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.robstoll.lib.creating.charsequence.contains.checkers.AtMostChecker
import ch.tutteli.atrium.robstoll.lib.creating.charsequence.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.robstoll.lib.creating.charsequence.contains.checkers.NotChecker

/**
 * Robstoll's implementation of [CheckerFactory].
 */
class CheckerFactoryImpl : CheckerFactory {

    override fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): CharSequenceContains.Checker
        = AtLeastChecker(times, nameContainsNotFun, atLeastCall)

    override fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): CharSequenceContains.Checker
        = AtMostChecker(times, nameContainsNotFun, atMostCall)

    override fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): CharSequenceContains.Checker
        = ExactlyChecker(times, nameContainsNotFun, exactlyCall)

    override fun newNotChecker(): CharSequenceContains.Checker
        = NotChecker()
}
