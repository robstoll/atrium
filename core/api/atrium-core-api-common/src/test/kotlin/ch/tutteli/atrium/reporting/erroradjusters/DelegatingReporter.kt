package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter

//TODO 0.16.0, remove again, only temporary
internal class DelegatingReporter(
    reporter: Reporter,
    override val atriumErrorAdjuster: AtriumErrorAdjuster
) : Reporter by reporter

