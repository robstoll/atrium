package ch.tutteli.atrium.reporting

import  ch.tutteli.atrium.reporting.reportables.Reportable

/**
 * Represents a reporter which reports about [Reportable]s.
 */
//TODO 1.3.0 deprecate
interface Reporter {

    /**
     * Reports about the given [assertion], using the given [sb] where the actual
     * implementation will decide whether the given [assertion] is noteworthy to be reported.
     *
     * @param sb The [StringBuilder] which can be used for reporting.
     * @param assertion The assertion which should be considered for reporting.
     */
    @Deprecated(
        "switch from Assertion to Proof based reporting, will be removed with 2.0.0 at the latest",
        ReplaceWith("sb.append(this.createReport(assertion))")
    )
    @Suppress("DEPRECATION")
    fun format(assertion: ch.tutteli.atrium.assertions.Assertion, sb: StringBuilder)

    /**
     * Creates a report about the given [reportable].
     *
     * @param reportable The [Reportable] for which a report shall be created.
     *
     * @return The report as StringBuilder
     */
    //TODO 2.0.0 remove default implementation, only here to retain source compatibility
    //TODO 1.3.0 require a proof instead?
    fun createReport(reportable: Reportable): StringBuilder =
        throw UnsupportedOperationException("Either you are still using OnlyFailureReporter which is deprecated and cannot be used for Proof-based reporting (switch to TextReporter) or you implemented a custom reporter which is still based on Assertion instead of Proof.")
}
