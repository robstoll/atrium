package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.reporting.reportables.ReportableGroup
import ch.tutteli.atrium.reporting.reportables.ReportableWithDesignation

/**
 * The base interface for [Proof] groups, providing a default implementation for [Proof.holds]
 * which returns `true` if all its [proofs] hold.
 */
interface ProofGroup : Proof, ReportableGroup {

    /**
     * The [Proof]s of this group, which are defined for the subject.
     *
     * Per default all instances in [children] which are [Proof]s.
     */
    val proofs: List<Proof> get() = children.filterIsInstance<Proof>()

    /**
     * Holds if all its [proofs] hold.
     *
     * @return `true` if all [proofs] hold; `false` otherwise.
     */
    override fun holds(): Boolean  = proofs.all(Proof::holds)
}

interface RootProofGroup: ProofGroup, ReportableWithDesignation
interface FeatureProofGroup: ProofGroup, ReportableWithDesignation
interface InvisibleProofGroup: ProofGroup
