package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.InvisibleFailingProof
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseBuilder
import ch.tutteli.atrium.creating.proofs.builders.impl.DiagnosticBuilder
import ch.tutteli.atrium.creating.proofs.builders.impl.DiagnosticBuilderDelegate
import ch.tutteli.atrium.reporting.reportables.Diagnostic

class InvisibleFailingProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    diagnosticBuilderDelegate: DiagnosticBuilderDelegate<SubjectT>
) : BaseBuilder<SubjectT, InvisibleFailingProof, Diagnostic, InvisibleFailingProofGroupBuilder<SubjectT>>(proofContainer),
    DiagnosticBuilder<SubjectT> by diagnosticBuilderDelegate {
    init {
        diagnosticBuilderDelegate.reportableBuilder = this
    }

    override fun build(children: List<Diagnostic>): InvisibleFailingProof =
        Proof.invisibleFailingProofGroup(children)
}
