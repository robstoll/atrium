package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.core.robstoll.lib.AssertionVerbFactory

object ReportingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.ReportingAssertionPlantSpec(
    AssertionVerbFactory, ::ReportingAssertionPlantImpl)
