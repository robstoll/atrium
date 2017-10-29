package ch.tutteli.atrium

import ch.tutteli.atrium.checking.FeatureAssertionChecker
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*
import kotlin.reflect.KClass

/**
 * Robstoll's `abstract factory` of atrium.
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IThrowableFluent]
 * - [IAssertionChecker]
 * - [IMethodCallFormatter]
 * - [ITranslator]
 * - [IObjectFormatter]
 * - [IAssertionFormatterFacade]
 * - [IAssertionFormatterController]
 * - [IAssertionFormatter]
 * - [IReporter]
 * - [IDownCastBuilder]
 */
object AtriumFactory : IAtriumFactory {

    override fun <T : Any> newReportingPlantCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlant<T>
        = AssertionPlantCheckLazily(commonFields)

    override fun <T : Any> newReportingPlantCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlant<T>
        = AssertionPlantCheckImmediately(commonFields)

    override fun <T : Any?> newReportingPlantNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlantNullable<T>
        = AssertionPlantNullable(commonFields)

    override fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, reporter: IReporter): IThrowableFluent
        = newThrowableFluent(assertionVerb, act, newThrowingAssertionChecker(reporter))

    override fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): IThrowableFluent
        = ThrowableFluent.create(assertionVerb, act, assertionChecker)

    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = ThrowingAssertionChecker(reporter)

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = FeatureAssertionChecker(subjectPlant)

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

    override fun newTextFallbackAssertionFormatter(bulletPoint: String, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = TextFallbackAssertionFormatter(bulletPoint, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    override fun newTextFeatureAssertionGroupFormatter(arrow: String, featureBulletPoint: String, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = TextFeatureAssertionGroupFormatter(arrow, featureBulletPoint, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    override fun newTextListAssertionGroupFormatter(listBulletPoint: String, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = TextListAssertionGroupFormatter(listBulletPoint, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    override fun registerSameLineTextAssertionFormatterCapabilities(
        bulletPoint: String,
        arrow: String,
        featureBulletPoint: String,
        listBulletPoint: String,
        indentedListBulletPoint: String,
        explanatoryBulletPoint: String,
        assertionFormatterFacade: IAssertionFormatterFacade,
        objectFormatter: IObjectFormatter, translator: ITranslator
    ): Unit {
        val pairFormatter = newTextSameLineAssertionPairFormatter(objectFormatter, translator)
        assertionFormatterFacade.register(::InvisibleAssertionGroupFormatter)
        assertionFormatterFacade.register { TextListAssertionGroupFormatter(listBulletPoint, it, pairFormatter) }
        assertionFormatterFacade.register { IndentAssertionGroupFormatter(indentedListBulletPoint, it) }
        assertionFormatterFacade.register { TextExplanatoryAssertionGroupFormatter(explanatoryBulletPoint, it, pairFormatter) }
        assertionFormatterFacade.register { TextFeatureAssertionGroupFormatter(arrow, featureBulletPoint, it, pairFormatter) }
        assertionFormatterFacade.register { TextFallbackAssertionFormatter(bulletPoint, it, pairFormatter) }
    }

    private fun newTextSameLineAssertionPairFormatter(objectFormatter: IObjectFormatter, translator: ITranslator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    override fun newOnlyFailureReporter(assertionFormatterFacade: IAssertionFormatterFacade): IReporter
        = OnlyFailureReporter(assertionFormatterFacade)

    override fun <TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, subType: KClass<TSub>, commonFields: CommonFields<T?>): IDownCastBuilder<T, TSub>
        = DownCastBuilder(description, subType, commonFields)
}
