package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.core.coreFactory
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
                coreFactory.newPropertiesBasedTranslationSupplier(),
                coreFactory.newLocaleOrderDecider(),
                primaryLocale,
                fallbackLocales
            )
        },
        "[Atrium's TranslatorErrorCaseSpec] "
    )
}
