package ch.tutteli.atrium.reporting.reportables.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.*

internal class DefaultReportableGroup(
    override val description: Reportable,
    override val representation: Any,
    override val children: List<Reportable>
) : ReportableGroup, ReportableWithDesignation

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
    val description: Reportable,
    override val children: List<Reportable>
) : ReportableGroup

internal class DefaultInlineGroup(
    val inlineElements: List<InlineElement>
) : InlineElement
