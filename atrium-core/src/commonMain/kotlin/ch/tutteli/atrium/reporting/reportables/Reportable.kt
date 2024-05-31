package ch.tutteli.atrium.reporting.reportables

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.impl.DefaultUsageHintGroup
import ch.tutteli.atrium.reporting.impl.DefaultProofExplanation

/**
 * Marker interface for everything which can be reported.
 */
interface Reportable {
    /**
     * Extension point for [Reportable] factories.
     */
    companion object {
        //TODO 1.3.0 write kdoc for methods

        fun proofExplanation(proof: Proof): ReportableGroup = DefaultProofExplanation(proof)

        fun usageHintGroup(reportables: List<Reportable>): ReportableGroup = DefaultUsageHintGroup(reportables)
    }
}

/**
 * A [Reportable] which does not build itself a block but can be combined with other [InlineElement] seamlessly.
 */
interface InlineElement : Reportable

interface TextElement : InlineElement {
    val string: String
}
