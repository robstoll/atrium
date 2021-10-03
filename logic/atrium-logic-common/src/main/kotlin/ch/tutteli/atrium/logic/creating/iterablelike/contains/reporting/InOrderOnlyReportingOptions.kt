package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting

import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

interface InOrderOnlyReportingOptions {

    /**
     * Always shows only failing expectations, same as [InOrderOnlyReportingOptions.showOnlyFailingIfMoreElementsThan]`(0)`
     */
    fun showOnlyFailing() = showOnlyFailingIfMoreElementsThan(0)

    /**
     * Always shows a summary where both failing and successful expectations are shown, same as
     * [InOrderOnlyReportingOptions.showOnlyFailingIfMoreElementsThan]`(Int.MAX_VALUE)`.
     */
    fun showAlwaysSummary() = showOnlyFailingIfMoreElementsThan(Int.MAX_VALUE)

    /**
     * Show only failing expectations, i.e. elements which do not match, instead of a summary which
     * lists also successful expectations/elements.
     *
     * Default shows up to 10 elements in a summary ans only failing afterwards,
     * i.e. default is [showOnlyFailingIfMoreElementsThan]`(10)`
     */
    fun showOnlyFailingIfMoreElementsThan(number: Int)

    /**
     * Indicates until how many elements the summary view shall be used. If there are more elements in the
     * [IterableLike], then only failing expectations shall be shown.
     */
    val numberOfElementsInSummary: Int
}


