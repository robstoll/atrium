package ch.tutteli.atrium.reporting.reportables

import ch.tutteli.atrium.reporting.HorizontalAlignment

//TODO 1.3.0 check KDOC (including @since) of all types in this file

/**
 * The base interface for [Reportable] groups.
 */
interface ReportableGroup : Reportable {
    /**
     * The reportable elements of this group, which are defined for the subject.
     */
    //TODO 1.3.0 consider to use a NoneEmptyList instead, in the end there won't be any group without a child
    // if no child is defined then at list a child proof will be there explaining that the user forgot to define
    // children
    val children: List<Reportable>
}

interface ReportableGroupWithDescription : ReportableGroup {
    val description: Reportable
}

interface DebugGroup : ReportableGroupWithDescription
interface FailureExplanationGroup : ReportableGroupWithDescription
interface InformationGroup : ReportableGroupWithDescription

//TODO 1.3.0 check KDOC (including @since) of all types in this file
interface ProofExplanation : ReportableGroup

interface UsageHintGroup : ReportableGroup

interface InlineGroup : InlineElement {
    val inlineElements: List<InlineElement>
}

interface Row : Reportable {
    val icon: Icon?
    val columns: List<Column>
}

interface Column : Reportable {
    val inlineElement: InlineElement
    val alignment: HorizontalAlignment
}
