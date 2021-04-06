package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.impl.OnlyFailureReporter

object OnlyFailureReporterSpec : ch.tutteli.atrium.specs.reporting.OnlyFailureReporterSpec(
    ::OnlyFailureReporter
)
