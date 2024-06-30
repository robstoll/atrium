package ch.tutteli.atrium.reporting.reportables

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.impl.DefaultProofGroup
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.impl.*
import ch.tutteli.atrium.reporting.impl.DefaultDebugGroup
import ch.tutteli.atrium.reporting.impl.DefaultInlineGroup
import ch.tutteli.atrium.reporting.impl.DefaultProofExplanation
import ch.tutteli.atrium.reporting.impl.DefaultReportableGroup
import ch.tutteli.atrium.reporting.impl.DefaultUsageHintGroup

/**
 * Marker interface for everything which can be reported.
 */
interface Reportable {
    /**
     * Extension point for [Reportable] factories.
     */
    companion object {
        //TODO 1.3.0 write kdoc for methods

        fun group(description: InlineElement, representation: Any?, children: List<Reportable>): ReportableGroup =
            DefaultReportableGroup(description, representation ?: Text.NULL, children)

        fun proofExplanation(proof: Proof): ReportableGroup = DefaultProofExplanation(proof)

        fun usageHintGroup(reportables: List<Reportable>): ReportableGroup = DefaultUsageHintGroup(reportables)

        fun debugGroup(description: InlineElement, reportables: List<Reportable>): ReportableGroup =
            DefaultDebugGroup(description, reportables)

        fun inlineGroup(inlineElements: List<InlineElement>): InlineElement = DefaultInlineGroup(inlineElements)
    }
}

