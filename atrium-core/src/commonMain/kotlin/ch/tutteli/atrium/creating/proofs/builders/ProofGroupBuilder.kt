package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.Reportable

class ProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: Reportable,
    private val representation: Any?
) : BaseSubGroupBuilder<SubjectT, ProofGroup, ProofGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.group(description, representation, children) }
)
