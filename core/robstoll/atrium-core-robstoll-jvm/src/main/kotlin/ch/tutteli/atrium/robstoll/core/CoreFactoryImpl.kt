package ch.tutteli.atrium.robstoll.core

import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.CoroutineBasedLocaleOrderDecider
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.PropertiesPerEntityAndLocaleTranslationSupplier
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.CheckingAssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Robstoll's `abstract factory` for atrium-core.
 *
 * It provides factory methods to create:
 * - [AssertionPlant]
 * - [AssertionPlantNullable]
 * - [CheckingAssertionPlant]
 * - [CollectingAssertionPlant]
 * - [AssertionChecker]
 * - [MethodCallFormatter]
 * - [Translator]
 * - [TranslationSupplier]
 * - [CoroutineBasedLocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [AssertionPairFormatter]
 * - [Reporter]
 */
class CoreFactoryImpl : CoreFactoryCommonImpl(), CoreFactory {

    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = PropertiesPerEntityAndLocaleTranslationSupplier()
}
