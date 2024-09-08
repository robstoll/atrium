package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.UsageHintGroup
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultUsageHintGroupTextPreRenderer(
    private val iconStyler: TextIconStyler
) : TypedTextPreRenderer<UsageHintGroup>(UsageHintGroup::class) {

    override fun transformIt(
        reportable: UsageHintGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = reportable.children.flatMap { child ->
        controlObject.transformChild(child, controlObject).map {
            it.copy(
                // we use an empty string as column as we want to indent by one
                columns = listOf(StyledString.EMPTY_STRING, iconStyler.style(Icon.BULB)) + it.columns,
                indentLevel = it.indentLevel + 1,
                usesOwnPrefix = true
            )
        }
    }
}
