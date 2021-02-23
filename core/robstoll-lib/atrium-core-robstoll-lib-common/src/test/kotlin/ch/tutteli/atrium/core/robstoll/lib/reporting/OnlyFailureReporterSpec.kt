//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.reporting

object OnlyFailureReporterSpec : ch.tutteli.atrium.specs.reporting.OnlyFailureReporterSpec(
    ::OnlyFailureReporter
)
