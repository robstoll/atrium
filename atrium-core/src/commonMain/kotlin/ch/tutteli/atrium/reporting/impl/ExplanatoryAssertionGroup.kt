package ch.tutteli.atrium.reporting.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableGroup
import ch.tutteli.atrium.reporting.reportables.ReportableGroupWithDesignation

internal class DefaultReportableGroup(
    override val description: InlineElement,
    override val representation: Any,
    override val children: List<Reportable>
) : ReportableGroupWithDesignation

internal class DefaultProofExplanation(
    proof: Proof
) : ReportableGroup {
    //TODO 1.3.0 unpack invisible groups
    override val children: List<Reportable> = listOf(proof)
}

//TODO 1.3.0 several implementations vs. a group type.
internal class DefaultUsageHintGroup(
    override val children: List<Reportable>
) : ReportableGroup

//TODO 1.3.0 several implementations vs. a group type.
internal class DefaultDebugGroup(
    val description: InlineElement,
    override val children: List<Reportable>
) : ReportableGroup

internal class DefaultInlineGroup(
    val inlineElements: List<InlineElement>
) : InlineElement
