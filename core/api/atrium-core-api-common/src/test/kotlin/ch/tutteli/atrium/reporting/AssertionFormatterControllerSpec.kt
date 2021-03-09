package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.impl.DefaultTextAssertionFormatterController

object AssertionFormatterControllerSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterControllerSpec(
    ::DefaultTextAssertionFormatterController
)
