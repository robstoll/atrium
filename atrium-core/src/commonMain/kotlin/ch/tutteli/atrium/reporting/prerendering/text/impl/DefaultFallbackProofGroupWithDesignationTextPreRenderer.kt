package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.ProofGroupWithDesignation
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.transformSubProofGroup

internal class DefaultFallbackProofGroupWithDesignationTextPreRenderer :
    TypedTextPreRenderer<ProofGroupWithDesignation>(ProofGroupWithDesignation::class) {
    override fun transformIt(
        reportable: ProofGroupWithDesignation,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = controlObject.transformSubProofGroup(reportable, controlObject, Icon.LIST_BULLET_POINT)
}
