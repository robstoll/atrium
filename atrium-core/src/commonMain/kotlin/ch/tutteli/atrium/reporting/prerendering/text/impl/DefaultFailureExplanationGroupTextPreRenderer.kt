package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.reportables.FailureExplanationGroup
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultFailureExplanationGroupTextPreRenderer(
    iconStyler: TextIconStyler
) : BaseReportableGroupWithDescriptionTextPreRenderer<FailureExplanationGroup>(
    FailureExplanationGroup::class,
    iconStyler,
    groupIcon = Icon.BANGBANG,
    childIcon = Icon.LIST_BULLET_POINT
)
