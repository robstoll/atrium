package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.reportables.DebugGroup
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultDebugGroupTextPreRenderer(
    iconStyler: TextIconStyler
) : BaseReportableGroupWithDescriptionTextPreRenderer<DebugGroup>(
    DebugGroup::class,
    iconStyler,
    groupIcon = Icon.DEBUG_INFO,
    childIcon = Icon.LIST_BULLET_POINT
)
