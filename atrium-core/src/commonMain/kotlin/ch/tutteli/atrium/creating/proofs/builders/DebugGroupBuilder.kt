package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.DebugGroup
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement

/** @since 1.3.0 */
class DebugGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseSubGroupBuilder<SubjectT, DebugGroup, DebugGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Diagnostic.debugGroup(description, children) }
)
