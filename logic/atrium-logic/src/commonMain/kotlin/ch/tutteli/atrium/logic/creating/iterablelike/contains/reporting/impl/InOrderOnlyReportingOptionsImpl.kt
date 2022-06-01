package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.impl

import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions

internal class InOrderOnlyReportingOptionsImpl : BaseOnlyReportingOptionsImpl(), InOrderOnlyReportingOptions{
    //TODO 0.19.0 remove
    @Suppress("OverridingDeprecatedMember")
    override val numberOfElementsInSummary: Int get() = maxNumberOfExpectedElementsForSummary
    @Suppress("OverridingDeprecatedMember")
    override fun showOnlyFailingIfMoreElementsThan(number: Int) = showOnlyFailingIfMoreExpectedElementsThan(number)
}
