package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.core.robstoll.lib.AssertionVerbFactory

object AssertionFormatterControllerSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterControllerSpec(
    AssertionVerbFactory, ::AssertionFormatterControllerImpl)
