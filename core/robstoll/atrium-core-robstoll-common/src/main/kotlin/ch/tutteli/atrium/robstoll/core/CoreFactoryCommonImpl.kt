package ch.tutteli.atrium.robstoll.core

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.CoreFactoryCommon
import ch.tutteli.atrium.core.robstoll.lib.checking.DelegatingAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.checking.FeatureAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.creating.CheckingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.CollectingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.CollectingAssertionPlantNullableImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.ReportingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.ReportingAssertionPlantNullableImpl
import ch.tutteli.atrium.core.robstoll.lib.reporting.*
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.CoroutineBasedLocaleOrderDecider
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.TranslationSupplierBasedTranslator
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

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
 * - [CoroutineBasedLocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [AssertionPairFormatter]
 * - [Reporter]
 * - [AtriumErrorAdjuster]
 */
abstract class CoreFactoryCommonImpl : CoreFactoryCommon {

    final override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = ReportingAssertionPlantImpl(commonFields)

    final override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = ReportingAssertionPlantNullableImpl(commonFields)

    final override fun <T : Any> newCheckingPlant(subjectProvider: () -> T): CheckingAssertionPlant<T>
        = CheckingAssertionPlantImpl(subjectProvider)

    final override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = CollectingAssertionPlantImpl(subjectProvider)

    final override fun <T> newCollectingPlantNullable(subjectProvider: () -> T): CollectingAssertionPlantNullable<T>
        = CollectingAssertionPlantNullableImpl(subjectProvider)

    final override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = ThrowingAssertionChecker(reporter)

    final override fun <T> newFeatureAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    final override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = DelegatingAssertionChecker(subjectPlant)

    final override fun newMethodCallFormatter(): MethodCallFormatter
        = TextMethodCallFormatter

    final override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, fallbackLocales: List<Locale>): Translator
        = TranslationSupplierBasedTranslator(translationSupplier, localeOrderDecider, primaryLocale, fallbackLocales)

    final override fun newLocaleOrderDecider(): LocaleOrderDecider
        = CoroutineBasedLocaleOrderDecider()

    final override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = DetailedObjectFormatter(translator)

    final override fun newAssertionFormatterController(): AssertionFormatterController
        = AssertionFormatterControllerImpl()

    final override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = AssertionFormatterControllerBasedFacade(assertionFormatterController)

    final override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    final override fun newTextFallbackAssertionFormatter(bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFallbackAssertionFormatter(
            bulletPoints,
            assertionFormatterController,
            newTextSameLineAssertionPairFormatter(objectFormatter, translator),
            objectFormatter
        )

    final override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFeatureAssertionGroupFormatter(
            bulletPoints,
            assertionFormatterController,
            newTextSameLineAssertionPairFormatter(objectFormatter, translator)
        )

    final override fun newTextListAssertionGroupFormatter(bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextListAssertionGroupFormatter(
            bulletPoints,
            assertionFormatterController,
            newTextSameLineAssertionPairFormatter(objectFormatter, translator)
        )

    final override fun newTextSummaryAssertionGroupFormatter(bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextSummaryAssertionGroupFormatter(
            bulletPoints,
            assertionFormatterController,
            newTextSameLineAssertionPairFormatter(objectFormatter, translator)
        )

    final override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = TextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)

    final override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
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
        assertionFormatterFacade.register {
            TextExplanatoryAssertionGroupFormatter(bulletPoints, it)
        }
        assertionFormatterFacade.register {
            @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
            TextIndentAssertionGroupFormatter(bulletPoints, it)
        }
        assertionFormatterFacade.register {
            TextSummaryAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register {
            TextFallbackAssertionFormatter(bulletPoints, it, textAssertionPairFormatter, objectFormatter)
        }
    }

    final override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade, atriumErrorAdjuster: AtriumErrorAdjuster): Reporter
        = OnlyFailureReporter(assertionFormatterFacade, atriumErrorAdjuster)

    final override fun newNoOpAtriumErrorAdjuster(): AtriumErrorAdjuster
        = NoOpAtriumErrorAdjuster

    final override fun newRemoveRunnerAtriumErrorAdjuster(): AtriumErrorAdjuster
        = RemoveRunnerAtriumErrorAdjuster()

    final override fun newRemoveAtriumFromAtriumErrorAdjuster(): AtriumErrorAdjuster
        = RemoveAtriumFromAtriumErrorAdjuster()

    final override fun newMultiAtriumErrorAdjuster(
        firstAdjuster: AtriumErrorAdjuster,
        secondAdjuster: AtriumErrorAdjuster,
        otherAdjusters: List<AtriumErrorAdjuster>
    ): AtriumErrorAdjuster = MultiAtriumErrorAdjusterImpl(firstAdjuster, secondAdjuster, otherAdjusters)
}
