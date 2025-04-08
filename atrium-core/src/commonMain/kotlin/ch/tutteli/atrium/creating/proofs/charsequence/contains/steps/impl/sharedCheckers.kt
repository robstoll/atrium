
package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.AtMostChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl.DefaultAtLeastChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl.DefaultAtMostChecker

@OptIn(ExperimentalNewExpectTypes::class)
fun <T : CharSequence> atLeastChecker(
    container: ProofContainer<T>,
    times: Int,
    nameToContainNotFun: String,
    atLeastCall: (Int) -> String
): AtLeastChecker = container.getImpl(AtLeastChecker::class) {
    DefaultAtLeastChecker(times, nameToContainNotFun, atLeastCall)
}

@OptIn(ExperimentalNewExpectTypes::class)
fun <T : CharSequence> atMostChecker(
    container: ProofContainer<T>,
    times: Int,
    nameToContainNotFun: String,
    atMostCall: (Int) -> String
): AtMostChecker = container.getImpl(AtMostChecker::class) {
    DefaultAtMostChecker(times, nameToContainNotFun, atMostCall)
}
