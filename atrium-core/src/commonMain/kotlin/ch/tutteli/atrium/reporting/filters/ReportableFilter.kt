package ch.tutteli.atrium.reporting.filters

import ch.tutteli.atrium.reporting.filters.impl.FailingProofsAndOthers
import ch.tutteli.atrium.reporting.filters.impl.IncludeAllReportableFilter
import ch.tutteli.atrium.reporting.reportables.Reportable

// TODO 1.3.0 KDOC
interface ReportableFilter {
    fun includeInReporting(reportable: Reportable): Boolean

    // TODO 1.3.0 KDOC
    companion object {
        fun includeAll(): ReportableFilter = IncludeAllReportableFilter
        fun failingProofsAndNonProof(): ReportableFilter = FailingProofsAndOthers()
    }
}
