package ch.tutteli.atrium.reporting.filters.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.reportables.Reportable

// TODO 1.3.0 KDOC
internal class FailingProofsAndOthers : ReportableFilter {

    override fun includeInReporting(reportable: Reportable): Boolean =
        @Suppress("DEPRECATION")
        when (reportable) {
            //TODO remove with 2.0.0 at the latest and remove @Suppress above
            is ch.tutteli.atrium.assertions.AssertionGroup -> {
                reportable.holds().not() || reportable.type is  ch.tutteli.atrium.assertions.DoNotFilterAssertionGroupType
            }

            is Proof -> reportable.holds().not()
            else -> true
        }
}
