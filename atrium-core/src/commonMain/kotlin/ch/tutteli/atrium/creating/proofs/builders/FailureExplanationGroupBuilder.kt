package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.FailureExplanationGroup
import ch.tutteli.atrium.reporting.reportables.InlineElement

/** @since 1.3.0 */
class FailureExplanationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseSubGroupBuilder<SubjectT, FailureExplanationGroup, FailureExplanationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Diagnostic.failureExplanationGroup(description, children) }
)
