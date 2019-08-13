package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object OnlyFailureReporterSpec : ch.tutteli.atrium.spec.reporting.OnlyFailureReporterSpec(
    AssertionVerbFactory, ::OnlyFailureReporter
)
