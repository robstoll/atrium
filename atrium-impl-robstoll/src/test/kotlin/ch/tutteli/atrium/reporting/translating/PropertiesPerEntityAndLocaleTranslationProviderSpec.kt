package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.reporting.ReporterBuilder
import java.util.*

object PropertiesPerEntityAndLocaleTranslationProviderSpec : PropertiesBasedTranslationProviderSpec(
    ReporterBuilder
        .withTranslations(PropertiesPerEntityAndLocaleTranslationProvider(), Locale("de", "CH", "Sensler"))
        .withDetailedObjectFormatter()
        .withSameLineAssertionMessageFormatter()
        .buildOnlyFailureReporting()
)
