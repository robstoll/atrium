package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder
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
            reporterBuilder
                .withTranslationSupplier(PropertiesPerEntityAndLocaleTranslationSupplier())
                .withDefaultLocaleOrderDecider()
                .withDefaultTranslator(primaryLocale, *fallbackLocales)
                .withDetailedObjectFormatter()
                .withDefaultAssertionFormatterController()
                .withDefaultAssertionFormatterFacade()
                .withTextSameLineAssertionPairFormatter()
                .withDefaultTextCapabilities()
                .buildOnlyFailureReporter()
        },
        true,
        "[Atrium's TranslationIntSpec] "
    )
}
