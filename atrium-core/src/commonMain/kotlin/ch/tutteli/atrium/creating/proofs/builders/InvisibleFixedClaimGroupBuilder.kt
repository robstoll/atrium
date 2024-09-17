package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.InvisibleFixedClaimGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder

class InvisibleFixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val holds: Boolean
) : BaseSubGroupBuilder<SubjectT, InvisibleFixedClaimGroup, InvisibleFixedClaimGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.invisibleFixedClaimGroup(children, holds) }
)
