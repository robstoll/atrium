package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory

object ReportingAssertionContainerSpec : ch.tutteli.atrium.specs.creating.ReportingAssertionContainerSpec(
    AssertionVerbFactory, ::ReportingAssertionContainerImpl
)
