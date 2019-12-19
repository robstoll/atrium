@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.core.robstoll

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.PropertiesPerEntityAndLocaleTranslationSupplier
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.TranslationSupplier

class CoreFactoryImpl : CoreFactoryCommonImpl(), CoreFactory {
    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter =
        newOnlyFailureReporter(assertionFormatterFacade, newNoOpAtriumErrorAdjuster())

    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier =
        PropertiesPerEntityAndLocaleTranslationSupplier()
}
