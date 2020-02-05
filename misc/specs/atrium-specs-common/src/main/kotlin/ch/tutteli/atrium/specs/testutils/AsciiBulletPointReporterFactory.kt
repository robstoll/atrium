package ch.tutteli.atrium.specs.testutils

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory

class AsciiBulletPointReporterFactory : ReporterFactory {
    override val id: String = "ascii"

    override fun create(): Reporter {
        return ReporterBuilder.create()
            .withoutTranslationsUseDefaultLocale()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities(
                RootAssertionGroupType::class to "* ",
                ListAssertionGroupType::class to "- ",
                FeatureAssertionGroupType::class to "=> ",
                @Suppress("DEPRECATION" /* TODO remove together with entry with 1.0.0 */)
                IndentAssertionGroupType::class to "| ",
                PrefixFeatureAssertionGroupHeader::class to ">> ",
                PrefixSuccessfulSummaryAssertion::class to "(/) ",
                PrefixFailingSummaryAssertion::class to "(x) ",
                WarningAssertionGroupType::class to "(!) "
            )
            .withNoOpAtriumErrorAdjuster()
            .withOnlyFailureReporter()
            .build()
    }
}

abstract class WithAsciiReporter {
    init {
        ReporterFactory.specifyFactory("ascii")
    }
}

