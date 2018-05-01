package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object ReportingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.ReportingAssertionPlantSpec(
    AssertionVerbFactory, ::ReportingAssertionPlantImpl)
