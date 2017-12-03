package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object AssertionFormatterControllerSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterControllerSpec(
    AssertionVerbFactory, ::AssertionFormatterController)
