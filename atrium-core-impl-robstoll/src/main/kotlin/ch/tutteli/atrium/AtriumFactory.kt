package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.checking.DelegatingAssertionChecker
import ch.tutteli.atrium.checking.FeatureAssertionChecker
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*

/**
 * Robstoll's `abstract factory` of atrium.
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IMethodCallFormatter]
 * - [ITranslator]
 * - [IObjectFormatter]
 * - [IAssertionFormatterFacade]
 * - [IAssertionFormatterController]
 * - [IAssertionFormatter]
 * - [IReporter]
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

    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = ThrowingAssertionChecker(reporter)

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: IBaseAssertionPlant<T, *>): IAssertionChecker
        = DelegatingAssertionChecker(subjectPlant)

    override fun newMethodCallFormatter(): IMethodCallFormatter
        = TextMethodCallFormatter

    override fun newTranslator(translationSupplier: ITranslationSupplier, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator
        = Translator(translationSupplier, primaryLocale, fallbackLocales)

    override fun newDetailedObjectFormatter(translator: ITranslator): IObjectFormatter
        = DetailedObjectFormatter(translator)

    override fun newAssertionFormatterController(): IAssertionFormatterController
        = AssertionFormatterController()

    override fun newAssertionFormatterFacade(assertionFormatterController: IAssertionFormatterController): IAssertionFormatterFacade
        = AssertionFormatterFacade(assertionFormatterController)

    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = TextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator), objectFormatter)

    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = TextFeatureAssertionGroupFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = TextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController): IAssertionFormatter
        = TextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)

    override fun registerSameLineTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
        assertionFormatterFacade: IAssertionFormatterFacade,
        objectFormatter: IObjectFormatter,
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

    private fun newTextSameLineAssertionPairFormatter(objectFormatter: IObjectFormatter, translator: ITranslator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    override fun newOnlyFailureReporter(assertionFormatterFacade: IAssertionFormatterFacade): IReporter
        = OnlyFailureReporter(assertionFormatterFacade)
}
