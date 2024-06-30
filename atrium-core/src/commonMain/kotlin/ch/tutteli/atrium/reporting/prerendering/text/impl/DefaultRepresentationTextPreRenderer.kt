package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Representation
import ch.tutteli.atrium.reporting.reportables.TextElement
import ch.tutteli.atrium.reporting.text.TextObjFormatter
import ch.tutteli.atrium.reporting.text.impl.DefaultTextObjFormatter
import ch.tutteli.atrium.reporting.theming.text.noStyle

internal class DefaultRepresentationTextPreRenderer(
    private val textObjFormatter: TextObjFormatter
) : TypedTextPreRenderer<Representation>(Representation::class) {
    override fun transformIt(reportable: Representation, controlObject: TextPreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                columns = listOf(textObjFormatter.format(reportable.representation)),
                emptyList(),
                definesOwnLevel = true
            )
        )
}
