package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object OnlyFailureReporterSpec : ch.tutteli.atrium.spec.reporting.OnlyFailureReporterSpec(
    AssertionVerbFactory, ::OnlyFailureReporter)
