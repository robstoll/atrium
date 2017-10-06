package ch.tutteli.atrium

import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import java.util.*
import kotlin.reflect.KClass

/**
 * A dummy implementation of [IAtriumFactory] which should be replaced by an actual implementation.
 *
 * It provides factory methods which all throw an [UnsupportedOperationException] to create:
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
@Suppress("UNUSED_PARAMETER")
object AtriumFactory : IAtriumFactory {

    private const val ERROR_MSG = "The atrium-core-api-late-binding should only be used as a compileOnly dependency, " +
        "meaning as a substitute for a real implementation"

    override fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, reporter: IReporter): IThrowableFluent
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): IThrowableFluent
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newMethodCallFormatter(): IMethodCallFormatter
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newTranslator(translationSupplier: ITranslationSupplier, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newDetailedObjectFormatter(translator: ITranslator): IObjectFormatter
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newAssertionFormatterController(): IAssertionFormatterController
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newAssertionFormatterFacade(assertionFormatterController: IAssertionFormatterController): IAssertionFormatterFacade
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newTextFallbackAssertionFormatter(bulletPoint: String, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newTextFeatureAssertionGroupFormatter(arrow: String, featureBulletPoint: String, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newTextListAssertionGroupFormatter(listBulletPoint: String, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun registerSameLineTextAssertionFormatterCapabilities(bulletPoint: String, arrow: String, featureBulletPoint: String, listBulletPoint: String, assertionFormatterFacade: IAssertionFormatterFacade, objectFormatter: IObjectFormatter, translator: ITranslator): Unit
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun newOnlyFailureReporter(assertionFormatterFacade: IAssertionFormatterFacade): IReporter
        = throw UnsupportedOperationException(ERROR_MSG)

    override fun <TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, subType: KClass<TSub>, commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>): IDownCastBuilder<T, TSub>
        = throw UnsupportedOperationException(ERROR_MSG)
}
