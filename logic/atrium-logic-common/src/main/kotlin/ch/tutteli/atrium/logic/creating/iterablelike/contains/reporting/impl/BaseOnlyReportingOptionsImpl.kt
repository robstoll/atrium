package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.impl

import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.OnlyReportingOptions

internal abstract class BaseOnlyReportingOptionsImpl : OnlyReportingOptions {
    private var _maxNumberOfExpectedElementsInSummary = 10
    override val maxNumberOfExpectedElementsForSummary: Int get() = _maxNumberOfExpectedElementsInSummary

    override fun showOnlyFailingIfMoreExpectedElementsThan(number: Int) {
        // could check for negative numbers, but it does not really matter that much, it still means always
        _maxNumberOfExpectedElementsInSummary = number
    }
}
