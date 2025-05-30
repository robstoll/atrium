//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.AtMostChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl.DefaultAtLeastChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl.DefaultAtMostChecker
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

@OptIn(ExperimentalNewExpectTypes::class)
fun <T : IterableLike> atLeastChecker(
    container: AssertionContainer<T>,
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
): AtLeastChecker = container.getImpl(AtLeastChecker::class) {
    DefaultAtLeastChecker(times, nameContainsNotFun, atLeastCall)
}

@OptIn(ExperimentalNewExpectTypes::class)
fun <T : IterableLike> atMostChecker(
    container: AssertionContainer<T>,
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String
): AtMostChecker = container.getImpl(AtMostChecker::class) {
    DefaultAtMostChecker(times, nameContainsNotFun, atMostCall)
}
