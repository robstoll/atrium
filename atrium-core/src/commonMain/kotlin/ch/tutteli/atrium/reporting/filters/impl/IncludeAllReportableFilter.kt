package ch.tutteli.atrium.reporting.filters.impl

import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.reportables.Reportable

// TODO 1.3.0 KDOC
internal object IncludeAllReportableFilter : ReportableFilter {
    override fun includeInReporting(reportable: Reportable): Boolean = true
}
