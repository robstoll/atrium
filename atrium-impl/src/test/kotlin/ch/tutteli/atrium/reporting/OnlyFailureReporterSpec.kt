package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

class OnlyFailureReporterSpec : ch.tutteli.atrium.test.reporting.OnlyFailureReporterSpec(
    AssertionVerbFactory, ::OnlyFailureReporter)
