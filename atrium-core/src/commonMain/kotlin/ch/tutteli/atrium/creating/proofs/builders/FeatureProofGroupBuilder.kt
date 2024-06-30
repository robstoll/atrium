package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.FeatureProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.Reportable

class FeatureProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: Reportable,
    private val representation: Any?
) : BaseSubGroupBuilder<SubjectT, FeatureProofGroup, FeatureProofGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.featureGroup(description, representation, children) }
)
