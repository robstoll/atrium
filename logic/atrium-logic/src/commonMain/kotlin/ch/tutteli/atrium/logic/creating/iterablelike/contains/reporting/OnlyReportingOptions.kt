package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting

/**
 * Base interface for OnlyReportingOptions
 * @since 0.18.0
 */
interface OnlyReportingOptions {

    /**
     * Always shows only failing expectations, same as [OnlyReportingOptions.showOnlyFailingIfMoreExpectedElementsThan]`(0)`
     *
     * @since 0.17.0
     */
    fun showOnlyFailing() = showOnlyFailingIfMoreExpectedElementsThan(0)

    /**
     * Always shows a summary where both failing and successful expectations are shown, same as
     * [OnlyReportingOptions.showOnlyFailingIfMoreExpectedElementsThan]`(Int.MAX_VALUE)`.
     *
     * @since 0.17.0
     */
    fun showAlwaysSummary() = showOnlyFailingIfMoreExpectedElementsThan(Int.MAX_VALUE)

    /**
     * Show only failing expectations, i.e. elements which do not match, instead of a summary (which
     * lists also successful expectations/elements) if there are more than [number] expected elements.
     *
     * Default shows up to 10 elements in a summary and only failing afterwards,
     * i.e. default is [showOnlyFailingIfMoreExpectedElementsThan]`(10)`
     *
     * @since 0.17.0
     */
    fun showOnlyFailingIfMoreExpectedElementsThan(number: Int)

    /**
     * Indicates until how many expected elements the summary view shall be used. If there are more expected elements,
     * then only failing expectations shall be shown.
     *
     * @since 0.18.0
     */
    val maxNumberOfExpectedElementsForSummary: Int
}


