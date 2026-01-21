package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.ProofExplanation

internal class DefaultProofExplanationTextPreRenderer :
    TypedTextPreRenderer<ProofExplanation>(ProofExplanation::class) {

    override fun transformIt(
        reportable: ProofExplanation,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> {
        val newControlObject = controlObject.copy(
            prefix = Icon.PROOF_EXPLANATION_BULLET_POINT,
            explainsProof = true
        )
        return OutputNode.singleWithoutColumnsNotOwnLevel(children = reportable.children.flatMap { child ->
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        })
    }
}
