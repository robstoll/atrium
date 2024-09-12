package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.determineChildControlObject
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultInvisibleProofGroupTextPreRenderer :
    TypedTextPreRenderer<InvisibleProofGroup>(InvisibleProofGroup::class) {
    override fun transformIt(
        reportable: InvisibleProofGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> =
        OutputNode.singleWithoutColumnsNotOwnLevel(
            children = reportable.children.flatMap { child ->
                val newControlObject = determineChildControlObject(controlObject, child, controlObject.prefix)
                controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
            }
        )
}

