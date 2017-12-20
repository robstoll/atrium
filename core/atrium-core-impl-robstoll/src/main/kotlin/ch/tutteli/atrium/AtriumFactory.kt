package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.checking.DelegatingAssertionChecker
import ch.tutteli.atrium.checking.FeatureAssertionChecker
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.*
import java.util.*

/**
 * Robstoll's `abstract factory` of atrium.
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [MethodCallFormatter]
 * - [ITranslator]
 * - [ITranslationSupplier]
 * - [LocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [Reporter]
 */
object AtriumFactory : IAtriumFactory {

    override fun <T : Any> newReportingPlant(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlant<T>
        = ReportingAssertionPlant(commonFields)

    override fun <T : Any?> newReportingPlantNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlantNullable<T>
        = ReportingAssertionPlantNullable(commonFields)

    override fun <T : Any> newCheckingPlant(subject: T): ICheckingAssertionPlant<T>
        = CheckingAssertionPlant(subject)

    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): ICollectingAssertionPlant<T>
        = CollectingAssertionPlant(subjectProvider)

    override fun newThrowingAssertionChecker(reporter: Reporter): IAssertionChecker
        = ThrowingAssertionChecker(reporter)

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: IBaseAssertionPlant<T, *>): IAssertionChecker
        = DelegatingAssertionChecker(subjectPlant)

    override fun newMethodCallFormatter(): MethodCallFormatter
        = TextMethodCallFormatter

    override fun newTranslator(translationSupplier: ITranslationSupplier, localeOrderDecider: ILocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator
        = TranslationSupplierBasedTranslator(translationSupplier, localeOrderDecider, primaryLocale, fallbackLocales)

    override fun newPropertiesBasedTranslationSupplier(): ITranslationSupplier
        = PropertiesPerEntityAndLocaleTranslationSupplier()

    override fun newLocaleOrderDecider(): ILocaleOrderDecider
        = LocaleOrderDecider()

    override fun newDetailedObjectFormatter(translator: ITranslator): ObjectFormatter
        = DetailedObjectFormatter(translator)

    override fun newAssertionFormatterController(): AssertionFormatterController
        = AssertionFormatterControllerImpl()

    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = AssertionFormatterControllerBasedFacade(assertionFormatterController)

    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: ITranslator): AssertionFormatter
        = TextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator), objectFormatter)

    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: ITranslator): AssertionFormatter
        = TextFeatureAssertionGroupFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: ITranslator): AssertionFormatter
        = TextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = TextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)

    override fun registerSameLineTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        objectFormatter: ObjectFormatter,
        translator: ITranslator
    ) {
        val pairFormatter = newTextSameLineAssertionPairFormatter(objectFormatter, translator)
        assertionFormatterFacade.register { TextListAssertionGroupFormatter(bulletPoints, it, pairFormatter) }
        assertionFormatterFacade.register { TextFeatureAssertionGroupFormatter(bulletPoints, it, pairFormatter) }
        assertionFormatterFacade.register(::InvisibleAssertionGroupFormatter)
        assertionFormatterFacade.register { TextExplanatoryAssertionGroupFormatter(bulletPoints, it) }
        assertionFormatterFacade.register { TextIndentAssertionGroupFormatter(bulletPoints, it) }
        assertionFormatterFacade.register { TextSummaryAssertionGroupFormatter(bulletPoints, it, pairFormatter) }
        assertionFormatterFacade.register { TextFallbackAssertionFormatter(bulletPoints, it, pairFormatter, objectFormatter) }
    }

    private fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: ITranslator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = OnlyFailureReporter(assertionFormatterFacade)
}
