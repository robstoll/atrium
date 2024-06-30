package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.SimpleProof
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.transformGroup

internal class DefaultSimpleProofTextPreRenderer :
    TypedTextPreRenderer<SimpleProof>(SimpleProof::class) {
    override fun transformIt(reportable: SimpleProof, controlObject: TextPreRenderControlObject): List<OutputNode> {
        // we kind of misuse transformGroup to re-use the logic for TextDesignationPreRenderer
        // but we need to:
        // - set explainsProof = false as we can always show the representation
        // - set definesOwnLevel to false as a SimpleProof does not define an own level
        return listOf(
            controlObject.transformGroup(
                reportable,
                if (controlObject.explainsProof) controlObject.copy(explainsProof = false) else controlObject
            ).single().copy(definesOwnLevel = false)
        )
    }
}
