package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.transformAndGetSingleColumnOfSingleNode
import ch.tutteli.atrium.reporting.reportables.Column

internal class DefaultColumnTextPreRenderer : TypedTextPreRenderer<Column>(Column::class) {
    override fun transformIt(reportable: Column, controlObject: TextPreRenderControlObject): List<OutputNode> {
        val styledString = controlObject.transformAndGetSingleColumnOfSingleNode(reportable.inlineElement)

        return listOf(
            OutputNode(
                columns = listOf(styledString.withHorizontalAlignment(reportable.alignment)),
                emptyList(),
                definesOwnLevel = true
            )
        )
    }
}
