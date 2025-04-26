package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder

/** @since 1.3.0 */
class InvisibleProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseSubGroupBuilder<SubjectT, Proof, InvisibleProofGroupBuilder<SubjectT>>(
    proofContainer,
    Proof.Companion::invisibleGroupOrSingleChildIfProof
)
