package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.migration.toAtriumLocale
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

object TranslationSupplierBasedTranslatorSpec : Spek({
    include(AtriumsTranslationSupplierBasedTranslatorSpec)
    include(AtriumsTranslatorErrorCaseSpec)
}) {
    object AtriumsTranslationSupplierBasedTranslatorSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierBasedTranslatorSpec(
            AssertionVerbFactory,
            { translationSupplier: TranslationSupplier,
              localeOrderDecider: LocaleOrderDecider,
              primaryLocale: java.util.Locale,
              fallbackLocales: List<java.util.Locale> ->
                TranslationSupplierBasedTranslator(
                    translationSupplier,
                    localeOrderDecider,
                    primaryLocale.toAtriumLocale(),
                    fallbackLocales.map { it.toAtriumLocale() }
                )
            }, "[Atrium's TranslatorSpec] "
        )

    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorErrorCaseSpec(
        AssertionVerbFactory,
        { primaryLocale, fallbackLocales ->
            TranslationSupplierBasedTranslator(
                coreFactory.newPropertiesBasedTranslationSupplier(),
                coreFactory.newLocaleOrderDecider(),
                primaryLocale.toAtriumLocale(),
                fallbackLocales.map { it.toAtriumLocale() }
            )
        },
        "[Atrium's TranslatorErrorCaseSpec] "
    )
}
