package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.reporting.Reporter

// TODO 1.3.0 KDOC
interface TextReporter : Reporter {
    @Deprecated(
        "use createReport, will be removed with 2.0.0 at the latest",
        ReplaceWith("sb.append(this.createReport(assertion))"),
        level = DeprecationLevel.ERROR
    )
    override fun format(@Suppress("DEPRECATION") assertion: ch.tutteli.atrium.assertions.Assertion, sb: StringBuilder) =
        throw UnsupportedOperationException("no longer supported")
}
