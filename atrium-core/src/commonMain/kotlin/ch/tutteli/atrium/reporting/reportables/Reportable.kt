package ch.tutteli.atrium.reporting.reportables

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.impl.DefaultDebugGroup
import ch.tutteli.atrium.reporting.reportables.impl.DefaultInlineGroup
import ch.tutteli.atrium.reporting.reportables.impl.DefaultProofExplanation
import ch.tutteli.atrium.reporting.reportables.impl.DefaultReportableGroup
import ch.tutteli.atrium.reporting.reportables.impl.DefaultUsageHintGroup

//TODO 1.3.0 check KDOC (including @since) of all types in this file

/**
 * Marker interface for everything which can be reported.
 *
 * @since 1.3.0
 */
interface Reportable {
    /**
     * Extension point for [Reportable] factories.
     *
     * @since 1.3.0
     */
    companion object {
        //TODO 1.3.0 write kdoc for methods

        fun group(description: Reportable, representation: Any?, children: List<Reportable>): ReportableGroup =
            DefaultReportableGroup(description, representation ?: Text.NULL, children)

        fun proofExplanation(proof: Proof): ReportableGroup = DefaultProofExplanation(proof)

        fun usageHintGroup(reportables: List<Reportable>): ReportableGroup = DefaultUsageHintGroup(reportables)

        fun debugGroup(description: Reportable, reportables: List<Reportable>): ReportableGroup =
            DefaultDebugGroup(description, reportables)

        fun inlineGroup(inlineElements: List<InlineElement>): InlineElement = DefaultInlineGroup(inlineElements)
    }
}


//TODO 1.3.0 add KDOC and move to own file
interface ReportableWithDesignation : Reportable {

    /**
     * The description of the [Reportable].
     *
     * @since 1.3.0
     */
    val description: Reportable

    /**
     * A complementing representation to the [description].
     *
     * In the context of a [ReportableGroup] it is typically the subject for which the [ReportableGroup.children]
     * are defined. For instance, if the description is `index 0` then the representation shows what is at index 0.
     *
     * @since 1.3.0
     */
    val representation: Any
}

interface ReportableWithInlineDesignation : ReportableWithDesignation {
    override val description: InlineElement
}
