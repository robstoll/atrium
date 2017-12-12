package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import java.util.*

class PropertiesTranslationSupplierNorwegianSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierNorwegianSpec(
    AssertionVerbFactory,
    createReporter(Locale("nn", "ZZ", "WHATEVER"), Locale("de", "CH")),
    createReporter(Locale("no", "NO", "NY"), Locale("de", "CH"))
) {
    companion object {
        fun createReporter(primaryLocale: Locale, vararg fallbackLocales: Locale)
            = ReporterBuilder
            .withDefaultTranslatorAndSupplier(primaryLocale, *fallbackLocales)
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withSameLineTextAssertionFormatter()
            .buildOnlyFailureReporter()
    }
}
