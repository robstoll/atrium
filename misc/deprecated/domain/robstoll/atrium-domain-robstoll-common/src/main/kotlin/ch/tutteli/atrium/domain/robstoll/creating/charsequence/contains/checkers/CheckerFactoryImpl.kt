//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.checkers

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.checkers.CheckerFactory
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.checkers.AtMostChecker
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.checkers.NotChecker

@Deprecated("use extension functions of atrium-logic; will be removed with 1.0.0")
class CheckerFactoryImpl : CheckerFactory {

    override fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): CharSequenceContains.Checker = AtLeastChecker(times, nameContainsNotFun, atLeastCall)

    override fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): CharSequenceContains.Checker = AtMostChecker(times, nameContainsNotFun, atMostCall)

    override fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): CharSequenceContains.Checker = ExactlyChecker(times, nameContainsNotFun, exactlyCall)

    override fun newNotChecker(): CharSequenceContains.Checker = NotChecker()
}
