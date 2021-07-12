package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.impl

import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions

internal class InOrderOnlyReportingOptionsImpl : InOrderOnlyReportingOptions {
    private var _numberOfElementsInSummary = 10
    override val numberOfElementsInSummary: Int get() = _numberOfElementsInSummary

    override fun showOnlyFailingIfMoreElementsThan(number: Int) {
        // could check for negative numbers but it does not really matter that much, it still means always
        _numberOfElementsInSummary = number
    }
}
