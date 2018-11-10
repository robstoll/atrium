package ch.tutteli.atrium.robstoll.core

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.PropertiesPerEntityAndLocaleTranslationSupplier
import ch.tutteli.atrium.reporting.translating.TranslationSupplier

class CoreFactoryImpl : CoreFactoryCommonImpl(), CoreFactory {

    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier =
        PropertiesPerEntityAndLocaleTranslationSupplier()
}
