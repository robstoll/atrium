package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.reportables.DebugGroup
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultDebugGroupTextPreRenderer(
    iconStyler: TextIconStyler
) : DontShowIfExplainingTextPreRenderer<DebugGroup>(DebugGroup::class) {

    private val delegate = object : BaseReportableGroupWithDescriptionTextPreRenderer<DebugGroup>(
        DebugGroup::class,
        iconStyler,
        groupIcon = Icon.DEBUG_INFO,
        childIcon = Icon.LIST_BULLET_POINT
    ) {}

    override fun transformIfNotExplaining(
        reportable: DebugGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = delegate.transform(reportable, controlObject)
}
