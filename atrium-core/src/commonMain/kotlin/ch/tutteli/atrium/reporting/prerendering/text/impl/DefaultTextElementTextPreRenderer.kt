package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.TextElement
import ch.tutteli.atrium.reporting.theming.text.noStyle

internal class DefaultTextElementTextPreRenderer : TypedTextPreRenderer<TextElement>(TextElement::class) {
    override fun transformIt(reportable: TextElement, controlObject: TextPreRenderControlObject): List<OutputNode> =
        listOf(
            OutputNode(
                columns = listOf(reportable.string.noStyle(noLineBreak = false)),
                emptyList(),
                definesOwnLevel = true
            )
        )
}
