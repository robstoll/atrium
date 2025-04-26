package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.RepresentationOnlyProof
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.text.TextObjFormatter

internal class DefaultRepresentationOnlyProofTextPreRenderer(
    private val textObjFormatter: TextObjFormatter
) : TypedTextPreRenderer<RepresentationOnlyProof>(RepresentationOnlyProof::class) {
    override fun transformIt(reportable: RepresentationOnlyProof, controlObject: TextPreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                columns = listOf(textObjFormatter.format(reportable.representation)),
                emptyList(),
                definesOwnLevel = true
            )
        )
}
