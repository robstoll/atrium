package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.FixedClaimGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.InlineElement

/** @since 1.3.0 */
class FixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?,
    private val holds: Boolean
) : BaseSubGroupBuilder<SubjectT, FixedClaimGroup, FixedClaimGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.fixedClaimGroup(description, representation, children, holds) }
)
