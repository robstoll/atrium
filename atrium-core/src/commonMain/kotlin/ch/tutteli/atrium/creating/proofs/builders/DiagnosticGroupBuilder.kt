package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseSubGroupBuilder
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.DiagnosticGroup
import ch.tutteli.atrium.reporting.reportables.InlineElement

class DiagnosticGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?
) : BaseSubGroupBuilder<SubjectT, DiagnosticGroup, DiagnosticGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Diagnostic.group(description, representation, children) }
)
