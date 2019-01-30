package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory

/**
 * Represents the [ReporterFactory] with [ReporterFactory.id] `default`.
 *
 * It is defined as follows (currently):
 * reporterBuilder
 *    .withoutTranslationsUseDefaultLocale()
 *    .withDetailedObjectFormatter()
 *    .withDefaultAssertionFormatterController()
 *    .withDefaultAssertionFormatterFacade()
 *    .withTextSameLineAssertionPairFormatter()
 *    .withTextCapabilities()
 *    .withDefaultAtriumErrorAdjusters()
 *    .withOnlyFailureReporter()
 *    .build()
 *
 */
class DefaultReporterFactory : ReporterFactory {
    override val id = "default"

    override fun create(): Reporter {
        return reporterBuilder
            .withoutTranslationsUseDefaultLocale()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities()
            .withDefaultAtriumErrorAdjusters()
            .withOnlyFailureReporter()
            .build()
    }
}
