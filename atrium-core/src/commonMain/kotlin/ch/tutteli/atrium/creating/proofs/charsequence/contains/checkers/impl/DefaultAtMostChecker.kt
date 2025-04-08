package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.basic.contains.checkers.impl.ToContainChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.AtMostChecker
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.AT_MOST

/**
 * Represents a check that an expected search criterion is contained at most [times] in the search input.
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param nameNotToContainFun The function which should be used instead of `atMostCall` when [times] is zero.
 * @param atMostCall The function which was used and should not be used if [times] is zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class DefaultAtMostChecker(
    times: Int,
    override val nameNotToContainFun: String,
    override val atMostCall: (Int) -> String
) : AtMostChecker, ToContainChecker(times, nameNotToContainFun, atMostCall) {

    override fun createProof(foundNumberOfTimes: Int): Proof =
        Proof.simple(AT_MOST, Text(times.toString())) { foundNumberOfTimes in 0..times }
}
