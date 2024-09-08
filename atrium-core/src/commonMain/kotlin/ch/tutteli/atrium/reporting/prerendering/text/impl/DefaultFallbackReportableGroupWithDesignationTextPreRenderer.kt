package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.ProofGroupWithDesignation
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.transformSubProofGroup

internal class DefaultFallbackReportableGroupWithDesignationTextPreRenderer :
    TypedTextPreRenderer<ProofGroupWithDesignation>(ProofGroupWithDesignation::class) {
    override fun transformIt(
        reportable: ProofGroupWithDesignation,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> {
        val newControlObject =
            controlObject.copy(prefix = Icon.LIST_BULLET_POINT, indentLevel = controlObject.indentLevel + 1)
        return controlObject.transformSubProofGroup(reportable, controlObject) { child ->
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
    }
}
