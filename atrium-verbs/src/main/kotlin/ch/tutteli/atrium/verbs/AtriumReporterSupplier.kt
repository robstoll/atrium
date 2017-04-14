package ch.tutteli.atrium.verbs

import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.verbs.assert.assert
import ch.tutteli.atrium.verbs.assertthat.assertThat
import ch.tutteli.atrium.verbs.expect.expect

/**
 * Supplies the [IReporter] for the assertion verbs [assert], [assertThat] and [expect].
 */
object AtriumReporterSupplier {
    /**
     * The [IReporter] for the assertion verbs [assert], [assertThat] and [expect].
     */
    val REPORTER by lazy {
        ReporterBuilder
            .withDetailedObjectFormatter()
            .withSameLineAssertionMessageFormatter()
            .buildOnlyFailureReporting()
    }
}
