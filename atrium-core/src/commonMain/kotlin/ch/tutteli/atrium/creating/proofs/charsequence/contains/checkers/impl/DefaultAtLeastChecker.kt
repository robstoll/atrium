package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.basic.contains.checkers.impl.ToContainChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.AT_LEAST

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param nameNotToContainFun The function which should be used instead of `atLeastCall` when [times] is zero.
 * @param atLeastCall The function which was used and should not be used if [times] is zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class DefaultAtLeastChecker(
    times: Int,
    override val nameNotToContainFun: String,
    override val atLeastCall: (Int) -> String
) : AtLeastChecker, ToContainChecker(times, nameNotToContainFun, atLeastCall) {

    override fun createProof(foundNumberOfTimes: Int): Proof =
        Proof.simple(AT_LEAST, Text(times.toString())) { foundNumberOfTimes >= times }
}
