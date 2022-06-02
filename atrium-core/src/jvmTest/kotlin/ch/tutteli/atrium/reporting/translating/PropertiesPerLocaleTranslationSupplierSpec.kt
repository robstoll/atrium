package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.reporting.translating.impl.TranslationSupplierBasedTranslator

@ExperimentalComponentFactoryContainer
object PropertiesPerLocaleTranslationSupplierSpec : ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec(
    { optionsChooser, primaryLocale, fallbackLocales ->
        optionsChooser.apply {
            withSingletonComponent(TranslationSupplier::class){
                PropertiesPerLocaleTranslationSupplier()
            }
            withSingletonComponent(Translator::class) { c ->
                TranslationSupplierBasedTranslator(c.build(), c.build(), primaryLocale, fallbackLocales)
            }
        }
    },
    true,
    "[Atrium's TranslationIntSpec] "
)

