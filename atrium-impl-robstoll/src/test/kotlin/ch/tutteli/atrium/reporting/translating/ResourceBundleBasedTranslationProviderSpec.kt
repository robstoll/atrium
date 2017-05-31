package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.reporting.ReporterBuilder
import java.util.*

object ResourceBundleBasedTranslationProviderSpec : PropertiesBasedTranslationProviderSpec(
    ReporterBuilder
        .withTranslator(ResourceBundleBasedTranslator.create(Locale("de", "CH", "Sensler")))
        .withDetailedObjectFormatter()
        .withSameLineAssertionMessageFormatter()
        .buildOnlyFailureReporting()
)
