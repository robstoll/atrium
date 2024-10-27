//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController

object AssertionFormatterControllerSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterControllerSpec(
    ::DefaultAssertionFormatterController
)
