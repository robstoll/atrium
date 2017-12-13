package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.AtriumFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

object TranslatorSpec : Spek({
    include(AtriumsTranslatorSpec)
    include(AtriumsTranslatorErrorCaseSpec)
}) {
    object AtriumsTranslatorSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorSpec(
        AssertionVerbFactory, ::Translator, "[Atrium's TranslatorSpec] ")

    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorErrorCaseSpec(
        AssertionVerbFactory,
        { p, f -> Translator(AtriumFactory.newPropertiesBasedTranslationSupplier(), AtriumFactory.newLocaleOrderDecider(), p, f) },
        "[Atrium's TranslatorErrorCaseSpec] ")
}
