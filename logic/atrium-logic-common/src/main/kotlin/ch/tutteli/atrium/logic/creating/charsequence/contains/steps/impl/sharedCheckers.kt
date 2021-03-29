package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.AtMostChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl.DefaultAtLeastChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl.DefaultAtMostChecker

@Suppress( /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2*/ "DEPRECATION")
@UseExperimental(ExperimentalNewExpectTypes::class)
fun <T : CharSequence> atLeastChecker(
    container: AssertionContainer<T>,
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
): AtLeastChecker = container.getImpl(AtLeastChecker::class) {
    DefaultAtLeastChecker(times, nameContainsNotFun, atLeastCall)
}

@Suppress( /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2*/ "DEPRECATION")
@UseExperimental(ExperimentalNewExpectTypes::class)
fun <T : CharSequence> atMostChecker(
    container: AssertionContainer<T>,
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String
): AtMostChecker = container.getImpl(AtMostChecker::class) {
    DefaultAtMostChecker(times, nameContainsNotFun, atMostCall)
}
