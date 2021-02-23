package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.impl.TranslationSupplierBasedTranslator
import ch.tutteli.atrium.specs.reporting.translating.RealTranslatorIntSpec
import ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec

@ExperimentalComponentFactoryContainer
object PropertiesPerEntityAndLocaleTranslationSupplierSpec : RealTranslatorIntSpec(
    { optionsChooser, primaryLocale, fallbackLocales ->
        optionsChooser.apply {
            withSingletonComponent(TranslationSupplier::class){
                PropertiesPerEntityAndLocaleTranslationSupplier()
            }
            withSingletonComponent(Translator::class) { c ->
                TranslationSupplierBasedTranslator(c.build(), c.build(), primaryLocale, fallbackLocales)
            }
        }
    },
    true,
    "[Atrium's TranslationIntSpec] "
)
