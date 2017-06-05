package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.spec.reporting.translating.TranslationProviderSpec
import java.util.*

object PropertiesPerEntityAndLocaleTranslationProviderSpec : TranslationProviderSpec(
    AssertionVerbFactory,
    ReporterBuilder
        .withTranslations(PropertiesPerEntityAndLocaleTranslationProvider(), Locale("de", "CH"), Locale("fr"))
        .withDetailedObjectFormatter()
        .withSameLineAssertionMessageFormatter()
        .buildOnlyFailureReporting()
)
