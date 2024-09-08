package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.DebugGroup
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultDebugGroupTextPreRenderer(
    private val iconStyler: TextIconStyler
) : TypedTextPreRenderer<DebugGroup>(DebugGroup::class) {
    override fun transformIt(
        reportable: DebugGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = controlObject.transformGroup(
        reportable,
        controlObject,
        prefixDescriptionColumns = listOf(iconStyler.style(Icon.DEBUG_INFO)),
    ) { child ->
        val newControlObject =
            determineChildControlObject(controlObject, child, Icon.LIST_BULLET_POINT, additionalIndent = 1)
        controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
    }.let {
        listOf(it.first().copy(usesOwnPrefix = true)) + it.drop(1)
    }
}
