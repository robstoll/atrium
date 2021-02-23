//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
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
@Deprecated("Will be removed with 0.17.0 without replacement")
class DefaultReporterFactory : ReporterFactory {
    override val id = "default"

    override fun create(): Reporter =
        ReporterBuilder.create()
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
