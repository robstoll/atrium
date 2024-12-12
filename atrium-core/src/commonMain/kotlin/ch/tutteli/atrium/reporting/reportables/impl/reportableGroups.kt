package ch.tutteli.atrium.reporting.reportables.impl

import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.*
import ch.tutteli.atrium.reporting.HorizontalAlignment

internal abstract class BaseReportableGroup(final override val children: List<Reportable>) : ReportableGroup {
    init {
        require(children.isNotEmpty()) {
            "a group requires at least one child"
        }
    }
}

internal class DefaultReportableGroup(
    override val description: Reportable,
    override val representation: Any,
    children: List<Reportable>
) : BaseReportableGroup(children), ReportableGroupWithDesignation

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
) : BaseReportableGroup(children), UsageHintGroup

internal class DefaultDebugGroup(
    override val description: InlineElement,
    children: List<Reportable>
) : BaseReportableGroup(children), DebugGroup

internal class DefaultFailureExplanationGroup(
    override val description: Reportable,
    children: List<Reportable>
) : BaseReportableGroup(children), FailureExplanationGroup

internal class DefaultInformationGroup(
    override val description: Reportable,
    children: List<Reportable>
) : BaseReportableGroup(children), InformationGroup

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
