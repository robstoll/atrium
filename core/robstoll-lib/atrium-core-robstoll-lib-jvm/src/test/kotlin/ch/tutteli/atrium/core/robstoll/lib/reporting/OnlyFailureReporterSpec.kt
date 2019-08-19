package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory

object OnlyFailureReporterSpec : ch.tutteli.atrium.specs.reporting.OnlyFailureReporterSpec(
    AssertionVerbFactory, ::OnlyFailureReporter
)
