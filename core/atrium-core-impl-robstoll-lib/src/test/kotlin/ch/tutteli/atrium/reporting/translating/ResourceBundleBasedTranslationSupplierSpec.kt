package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import java.util.*

class ResourceBundleBasedTranslationSupplierSpec : Spek({
    include(TranslationSupplierSpec)
    include(TranslationSupplierNorwegianSpec)
}) {
    companion object {
        fun createReporter(primaryLocale: Locale, vararg fallbackLocales: Locale)
            = ReporterBuilder
            .withTranslator(ResourceBundleBasedTranslator.create(primaryLocale, *fallbackLocales))
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withSameLineTextAssertionFormatter()
            .buildOnlyFailureReporter()
    }

    object TranslationSupplierSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec(
        AssertionVerbFactory,
        createReporter(Locale("de", "CH"), Locale("fr")),
        "[Atrium's Translation..Spec] "
    )

    object TranslationSupplierNorwegianSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierNorwegianSpec(
        AssertionVerbFactory,
        createReporter(Locale("nn", "ZZ", "WHATEVER"), Locale("de", "CH")),
        createReporter(Locale("no", "NO", "NY"), Locale("de", "CH")),
        "[Atrium's Translation..NorwegianSpec] "
    )
}
