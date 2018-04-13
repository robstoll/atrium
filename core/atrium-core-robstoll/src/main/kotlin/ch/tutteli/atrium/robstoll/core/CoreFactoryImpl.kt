package ch.tutteli.atrium.robstoll.core

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.robstoll.lib.checking.DelegatingAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.checking.FeatureAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.creating.CheckingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.CollectingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.ReportingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.ReportingAssertionPlantNullableImpl
import ch.tutteli.atrium.core.robstoll.lib.reporting.*
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.CoroutineBasedLocaleOrderDecider
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.PropertiesPerEntityAndLocaleTranslationSupplier
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.TranslationSupplierBasedTranslator
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*

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
class CoreFactoryImpl : CoreFactory {

    override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = ReportingAssertionPlantImpl(commonFields)

    override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = ReportingAssertionPlantNullableImpl(commonFields)

    override fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>
        = CheckingAssertionPlantImpl(subject)

    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = CollectingAssertionPlantImpl(subjectProvider)

    override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = ThrowingAssertionChecker(reporter)

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = DelegatingAssertionChecker(subjectPlant)

    override fun newMethodCallFormatter(): MethodCallFormatter
        = TextMethodCallFormatter

    override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator
        = TranslationSupplierBasedTranslator(translationSupplier, localeOrderDecider, primaryLocale, fallbackLocales)

    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = PropertiesPerEntityAndLocaleTranslationSupplier()

    override fun newLocaleOrderDecider(): LocaleOrderDecider
        = CoroutineBasedLocaleOrderDecider()

    override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = DetailedObjectFormatter(translator)

    override fun newAssertionFormatterController(): AssertionFormatterController
        = AssertionFormatterControllerImpl()

    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = AssertionFormatterControllerBasedFacade(assertionFormatterController)

    override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFallbackAssertionFormatter(
            bulletPoints,
            assertionFormatterController,
            newTextSameLineAssertionPairFormatter(objectFormatter, translator),
            objectFormatter
        )

    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFeatureAssertionGroupFormatter(
            bulletPoints,
            assertionFormatterController,
            newTextSameLineAssertionPairFormatter(objectFormatter, translator)
        )

    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextListAssertionGroupFormatter(
            bulletPoints,
            assertionFormatterController,
            newTextSameLineAssertionPairFormatter(objectFormatter, translator)
        )

    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = TextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)

    override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ) {
        assertionFormatterFacade.register {
            TextListAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register {
            TextFeatureAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register(::InvisibleAssertionGroupFormatter)
        assertionFormatterFacade.register {
            TextExplanatoryAssertionGroupFormatter(bulletPoints, it)
        }
        assertionFormatterFacade.register {
            TextIndentAssertionGroupFormatter(bulletPoints, it)
        }
        assertionFormatterFacade.register {
            TextSummaryAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register {
            TextFallbackAssertionFormatter(bulletPoints, it, textAssertionPairFormatter, objectFormatter)
        }
    }

    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = OnlyFailureReporter(assertionFormatterFacade)
}
