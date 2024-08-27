package ch.tutteli.atrium.reporting.reportables.impl

import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.*

internal class DefaultReportableGroup(
    override val description: Reportable,
    override val representation: Any,
    override val children: List<Reportable>
) : ReportableGroupWithDesignation

internal class DefaultProofExplanation(
    proof: Proof
) : ProofExplanation {
    override val children: List<Reportable> = when (proof) {
        is InvisibleProofGroup -> proof.children
        else -> listOf(proof)
    }
}

internal class DefaultUsageHintGroup(
    override val children: List<Reportable>
) : UsageHintGroup

internal class DefaultDebugGroup(
    override val description: InlineElement,
    override val children: List<Reportable>
) : DebugGroup

internal class DefaultErrorExplanationGroup(
    override val description: Reportable,
    override val children: List<Reportable>
) : ErrorExplanationGroup

internal class DefaultInformationGroup(
    override val description: Reportable,
    override val children: List<Reportable>
) : InformationGroup


internal class DefaultInlineGroup(
    val inlineElements: List<InlineElement>
) : InlineElement
