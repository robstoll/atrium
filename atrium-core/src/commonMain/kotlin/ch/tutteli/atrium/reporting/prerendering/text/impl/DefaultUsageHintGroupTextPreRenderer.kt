package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.UsageHintGroup

internal class DefaultUsageHintGroupTextPreRenderer : TypedTextPreRenderer<UsageHintGroup>(UsageHintGroup::class) {

    override fun transformIt(
        reportable: UsageHintGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> {
        if (controlObject.explainsProof) return emptyList()
        val newControlObject = controlObject.copy(prefix = Icon.BULB, indentLevel = controlObject.indentLevel + 1)
        return OutputNode.singleWithoutColumnsNotOwnLevel(children = reportable.children.flatMap { child ->
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        })
    }
}
