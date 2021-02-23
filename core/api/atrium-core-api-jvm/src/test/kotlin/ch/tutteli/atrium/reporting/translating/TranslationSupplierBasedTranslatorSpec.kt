package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.reporting.translating.impl.ResourceBundleInspiredLocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.impl.TranslationSupplierBasedTranslator
import org.spekframework.spek2.Spek

object TranslationSupplierBasedTranslatorSpec : Spek({
    include(AtriumsTranslationSupplierBasedTranslatorSpec)
    include(AtriumsTranslatorErrorCaseSpec)
}) {
    object AtriumsTranslationSupplierBasedTranslatorSpec :
        ch.tutteli.atrium.specs.reporting.translating.TranslationSupplierBasedTranslatorSpec(
            ::TranslationSupplierBasedTranslator, "[Atrium's TranslatorSpec] "
        )

    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.specs.reporting.translating.TranslatorErrorCaseSpec(
        { primaryLocale, fallbackLocales ->
            TranslationSupplierBasedTranslator(
                PropertiesPerEntityAndLocaleTranslationSupplier(),
                ResourceBundleInspiredLocaleOrderDecider,
                primaryLocale,
                fallbackLocales
            )
        },
        "[Atrium's TranslatorErrorCaseSpec] "
    )
}
