package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.InvisibleLikeProofGroup
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.reportables.Reportable

internal class DefaultInvisibleLikeProofGroupTextPreRenderer :
    TypedTextPreRenderer<InvisibleLikeProofGroup>(InvisibleLikeProofGroup::class) {

    override fun transformIt(
        reportable: InvisibleLikeProofGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = OutputNode.singleWithoutColumnsNotOwnLevel(
        children = reportable.children.flatMap { child ->
            val newControlObject = determineChildControlObject(controlObject, child, controlObject.prefix)
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
    )

}

