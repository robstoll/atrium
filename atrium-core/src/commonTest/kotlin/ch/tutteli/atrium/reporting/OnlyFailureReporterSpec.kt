//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.impl.OnlyFailureReporter

object OnlyFailureReporterSpec : ch.tutteli.atrium.specs.reporting.OnlyFailureReporterSpec(
    ::OnlyFailureReporter
)
