package ch.tutteli.atrium.robstoll.core

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.PropertiesPerEntityAndLocaleTranslationSupplier
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.TranslationSupplier

class CoreFactoryImpl : CoreFactoryCommonImpl(), CoreFactory {
    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = newOnlyFailureReporter(assertionFormatterFacade, newNoOpAtriumErrorAdjuster())

    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier =
        PropertiesPerEntityAndLocaleTranslationSupplier()
}
