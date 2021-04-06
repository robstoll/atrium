package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController

object AssertionFormatterControllerSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterControllerSpec(
    ::DefaultAssertionFormatterController
)
