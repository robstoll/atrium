package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class PropertiesPerLocaleTranslationSupplierSpec : Spek({
    include(AtriumsTranslationSupplierSpec)
    include(AtriumsTranslationIntSpec)
}) {

    object AtriumsTranslationSupplierSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec(
        AssertionVerbFactory, ::PropertiesPerLocaleTranslationSupplier, "[Atrium's TranslationSupplierSpec] ")

    object AtriumsTranslationIntSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec(
        AssertionVerbFactory,
        { primaryLocale, fallbackLocales ->
            ReporterBuilder
                .withTranslationSupplier(PropertiesPerLocaleTranslationSupplier())
                .withDefaultLocaleOrderDecider()
                .withDefaultTranslator(primaryLocale, *fallbackLocales)
                .withDetailedObjectFormatter()
                .withDefaultAssertionFormatterController()
                .withDefaultAssertionFormatterFacade()
                .withSameLineTextAssertionFormatter()
                .buildOnlyFailureReporter()
        },
        true,
        "[Atrium's TranslationIntSpec] "
    )
}
