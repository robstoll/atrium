package ch.tutteli.atrium.reporting.reportables.impl

import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.reportables.*

internal abstract class BaseDiagnosticGroup(final override val children: List<Reportable>) : DiagnosticGroup {
    init {
        requireOneChild()
    }
}

internal class DefaultDiagnosticGroup(
    override val description: Diagnostic,
    override val representation: Any,
    children: List<Reportable>
) : BaseDiagnosticGroup(children), ReportableGroupWithDesignation

internal class DefaultProofExplanation(
    proof: Proof
) : ProofExplanation {
    override val children: List<Reportable> = when (proof) {
        is InvisibleProofGroup -> proof.children
        else -> listOf(proof)
    }
}

internal class DefaultUsageHintGroup(
    children: List<Reportable>
) : BaseDiagnosticGroup(children), UsageHintGroup

internal class DefaultDebugGroup(
    override val description: InlineElement,
    children: List<Reportable>
) : BaseDiagnosticGroup(children), DebugGroup

internal class DefaultFailureExplanationGroup(
    override val description: Diagnostic,
    children: List<Reportable>
) : BaseDiagnosticGroup(children), FailureExplanationGroup

internal class DefaultInformationGroup(
    override val description: Diagnostic,
    children: List<Reportable>
) : BaseDiagnosticGroup(children), InformationGroup

internal class DefaultInlineGroup(
    override val inlineElements: List<InlineElement>
) : InlineGroup {
    init {
        require(inlineElements.isNotEmpty()) {
            "an inline group requires at least one inline element"
        }
    }
}

internal class DefaultRow(
    override val icon: Icon?,
    override val includingBorder: Boolean,
    override val columns: List<Column>
) : Row {
    init {
        require(columns.isNotEmpty()) {
            "a row requires at least one column"
        }
    }
}

internal class DefaultColumn(
    override val inlineElement: InlineElement,
    override val alignment: HorizontalAlignment
) : Column
