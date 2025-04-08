package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.basic.contains.checkers.impl.ToContainChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.EXACTLY

/**
 * Represents a check that an expected search criterion is contained exactly [times] in the search input.
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param nameNotToContainFun The function which should be used instead of `exactlyCall` when [times] is zero.
 * @param exactlyCall The function call which was used and should not be used if [times] is zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class DefaultExactlyChecker(
    times: Int,
    override val nameNotToContainFun: String,
    override val exactlyCall: (Int) -> String
) : ExactlyChecker, ToContainChecker(times, nameNotToContainFun, exactlyCall) {

    override fun createProof(foundNumberOfTimes: Int): Proof =
        Proof.simple(EXACTLY, Text(times.toString())) { foundNumberOfTimes == times }
}
