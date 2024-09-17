package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.InformationGroup
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultInformationGroupTextPreRenderer(
    iconStyler: TextIconStyler
) : BaseReportableGroupWithDescriptionTextPreRenderer<InformationGroup>(
    InformationGroup::class,
    iconStyler,
    groupIcon = Icon.INFORMATION_SOURCE,
    childIcon = Icon.LIST_BULLET_POINT
)
