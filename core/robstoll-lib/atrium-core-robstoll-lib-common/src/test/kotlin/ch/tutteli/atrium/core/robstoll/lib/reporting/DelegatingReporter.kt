package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter

internal class DelegatingReporter(
    reporter: Reporter,
    override val atriumErrorAdjuster: AtriumErrorAdjuster
) : Reporter by reporter

