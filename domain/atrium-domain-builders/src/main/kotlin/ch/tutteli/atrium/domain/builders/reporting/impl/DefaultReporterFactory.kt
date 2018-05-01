package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory

/**
 * Represents the [ReporterFactory] with [ReporterFactory.id] `default`.
 *
 * It specifies:
 * - withoutTranslations
 * - withDetailedObjectFormatter
 * - withDefaultAssertionFormatterController
 * - withDefaultAssertionFormatterFacade
 * - withTextSameLineAssertionPairFormatter
 * - withDefaultTextCapabilities
 * - buildOnlyFailureReporter
 *
 */
class DefaultReporterFactory : ReporterFactory {
    override val id = "default"

    override fun create(): Reporter {
        return reporterBuilder
            .withoutTranslations()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withDefaultTextCapabilities()
            .buildOnlyFailureReporter()
    }
}
