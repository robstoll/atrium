package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.reporting.errorDueToBug
import ch.tutteli.atrium.reporting.failWithBugErrorIf
import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.theming.text.StyledString

// TODO 1.3.0 KDOC
data class TextPreRenderControlObject(
    val prefix: Icon,
    val indentLevel: Int,
    private val controller: TextPreRenderController,
    val reportableFilter: ReportableFilter,
    val explainsProof: Boolean
) : TextPreRenderController by controller {
    fun includeInReporting(reportable: Reportable) = reportableFilter.includeInReporting(reportable)
}

// TODO 1.3.0 KDOC
fun TextPreRenderControlObject.transformAndGetSingleColumnOfSingleNode(
    reportable: Reportable
): StyledString {
    val node = transformAndGetSingleNode(reportable)
    failWithBugErrorIf(node.columns.size != 1) {
        "transformChild for InlineElement $reportable returned ${node.columns.size} columns, expected 1"
    }
    return node.columns.first()
}

// TODO 1.3.0 KDOC
fun TextPreRenderControlObject.transformAndGetSingleNode(
    reportable: Reportable
): OutputNode {
    val nodes = transformChild(reportable, this)
    val node = ch.tutteli.kbox.takeIf(nodes.size == 1) {
        nodes[0]
    } ?: errorDueToBug(
        "transformChild for InlineElement $reportable returned ${nodes.size} nodes, expected 1"
    )

    return node
}
