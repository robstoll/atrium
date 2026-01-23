package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.UsageHintGroup

internal class DefaultUsageHintGroupTextPreRenderer :
    DontShowIfExplainingTextPreRenderer<UsageHintGroup>(UsageHintGroup::class) {

    override fun transformIfNotExplaining(
        reportable: UsageHintGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> {
        val newControlObject = controlObject.copy(prefix = Icon.BULB, indentLevel = controlObject.indentLevel + 1)

        return OutputNode.singleWithoutColumnsNotOwnLevel(children = reportable.children.flatMap { child ->
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        })
    }
}
