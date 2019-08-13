package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

//TODO #116 migrate spek1 to spek2 - move to common module
object TranslationSupplierBasedTranslatorSpec : Spek({
    include(AtriumsTranslationSupplierBasedTranslatorSpec)
    include(AtriumsTranslatorErrorCaseSpec)
}) {
    object AtriumsTranslationSupplierBasedTranslatorSpec :
        ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierBasedTranslatorSpec(
            AssertionVerbFactory, ::TranslationSupplierBasedTranslator, "[Atrium's TranslatorSpec] "
        )

    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorErrorCaseSpec(
        AssertionVerbFactory,
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
