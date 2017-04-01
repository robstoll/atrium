package ch.tutteli.atrium.verbs

import ch.tutteli.atrium.reporting.ReporterBuilder

object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withDetailedObjectFormatter()
            .withSameLineAssertionMessageFormatter()
            .buildOnlyFailureReporting()
    }
}
