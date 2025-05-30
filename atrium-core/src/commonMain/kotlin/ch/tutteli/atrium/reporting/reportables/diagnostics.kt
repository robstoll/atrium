package ch.tutteli.atrium.reporting.reportables

import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.impl.*

//TODO 1.3.0 check KDOC (including @since) of all types in this file


interface Diagnostic : Reportable {
    /**
     * Extension point for [Diagnostic] factories.
     *
     * @since 1.3.0
     */
    companion object {
        //TODO 1.3.0 write kdoc for methods

        fun group(description: InlineElement, representation: Any?, children: List<Reportable>): DiagnosticGroup =
            DefaultDiagnosticGroup(description, representation ?: Text.NULL, children)

        fun proofExplanation(proof: Proof): ProofExplanation {
            val proofToExplain = if (proof is InvisibleProofGroup && proof.children.size == 1) {
                // a single child in an InvisibleProofGroup has to be a Proof
                proof.children.first() as Proof
            } else proof
            return DefaultProofExplanation(proofToExplain)
        }

        fun failureExplanationGroup(
            description: InlineElement,
            reportables: List<Reportable>
        ): FailureExplanationGroup = DefaultFailureExplanationGroup(description, reportables)

        //TODO 1.3.0 note sure we need this
        fun informationGroup(description: InlineElement, reportables: List<Reportable>): InformationGroup =
            DefaultInformationGroup(description, reportables)

        fun usageHintGroup(reportables: List<Reportable>): UsageHintGroup = DefaultUsageHintGroup(reportables)

        fun debugGroup(description: InlineElement, reportables: List<Reportable>): DebugGroup =
            DefaultDebugGroup(description, reportables)

        //TODO 1.3.0 remove?
        fun inlineGroupOrSingleElement(inlineElements: List<InlineElement>): InlineElement =
            ch.tutteli.kbox.takeIf(inlineElements.size == 1) {
                inlineElements[0]
            } ?: inlineGroup(inlineElements)

        fun inlineGroup(inlineElements: List<InlineElement>): InlineGroup = DefaultInlineGroup(inlineElements)

        fun row(icon: Icon?, includingBorder: Boolean, columns: List<Column>): Row =
            DefaultRow(icon, includingBorder, columns)

        fun column(inlineElement: InlineElement, alignment: HorizontalAlignment): Column =
            DefaultColumn(inlineElement, alignment)

        fun representation(representation: Any?): Representation = DefaultRepresentation(representation)
        fun invisibleGroup(children: List<Diagnostic>): Diagnostic = DefaultInvisibleDiagnosticGroup(children)
    }
}

interface DiagnosticGroup : Diagnostic, ReportableGroup
interface InvisibleLikeDiagnosticGroup: DiagnosticGroup
interface InvisibleDiagnosticGroup: InvisibleLikeDiagnosticGroup


interface DiagnosticGroupWithDesignation : DiagnosticGroup, ReportableGroupWithDesignation

interface DiagnosticGroupWithDescription : DiagnosticGroup {
    val description: Diagnostic
}

interface DebugGroup : DiagnosticGroupWithDescription
interface FailureExplanationGroup : DiagnosticGroupWithDescription
interface InformationGroup : DiagnosticGroupWithDescription

//TODO 1.3.0 check KDOC (including @since) of all types in this file
interface ProofExplanation : DiagnosticGroup

interface UsageHintGroup : DiagnosticGroup

interface InlineGroup : InlineElement {
    val inlineElements: List<InlineElement>
}

interface Row : Diagnostic {
    val icon: Icon?
    val includingBorder: Boolean
    val columns: List<Column>
}

interface Column : Diagnostic {
    val inlineElement: InlineElement
    val alignment: HorizontalAlignment
}
