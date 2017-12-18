package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class PropertiesPerEntityAndLocaleTranslationSupplierSpec : Spek({
    include(AtriumsTranslationSupplierSpec)
    include(AtriumsTranslationIntSpec)
}) {

    object AtriumsTranslationSupplierSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec(
        AssertionVerbFactory, ::PropertiesPerEntityAndLocaleTranslationSupplier, "[Atrium's TranslationSupplierSpec] ")

    object AtriumsTranslationIntSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec(
        AssertionVerbFactory,
        { primaryLocale, fallbackLocales ->
            ReporterBuilder
                .withTranslationSupplier(PropertiesPerEntityAndLocaleTranslationSupplier())
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
