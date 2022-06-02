package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting

import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Reporting options for `toContain.inOrder.only` expectation functions.
 *
 * @since 0.17.0
 */
interface InOrderOnlyReportingOptions : OnlyReportingOptions{
    /**
     * Show only failing expectations, i.e. elements which do not match, instead of a summary (which
     * lists also successful expectations/elements) if there are more than [number] elements.
     *
     * Default shows up to 10 elements in a summary and only failing afterwards,
     * i.e. default is [showOnlyFailingIfMoreElementsThan]`(10)`
     *
     * @since 0.17.0
     */
    @Deprecated(
        "Use showOnlyFailingIfMoreExpectedElementsThan instead; will be removed with 0.19.0",
        ReplaceWith("this.showOnlyFailingIfMoreExpectedElementsThan(number)")
    )
    fun showOnlyFailingIfMoreElementsThan(number: Int)

    /**
     * Indicates until how many elements the summary view shall be used. If there are more elements in the
     * [IterableLike], then only failing expectations shall be shown.
     *
     * @since 0.17.0
     */
    @Deprecated(
        "Use maxNumberOfExpectedElementsForSummary; will be removed with 0.19.0",
        ReplaceWith("this.maxNumberOfExpectedElementsForSummary")
    )
    val numberOfElementsInSummary: Int
}


