package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.NotChecker
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof.TO_EQUAL

/**
 * Represents a check that an expected search criterion is not contained in the search input.
 */
class DefaultNotChecker : NotChecker {

    override fun createProof(foundNumberOfTimes: Int): Proof =
        Proof.simple(TO_EQUAL, 0) { foundNumberOfTimes == 0 }
}
