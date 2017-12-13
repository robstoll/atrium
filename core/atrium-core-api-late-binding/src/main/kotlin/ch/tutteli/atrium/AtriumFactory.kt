package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ILocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import java.util.*

@Suppress("UNUSED_PARAMETER")
/**
 * A dummy implementation of [IAtriumFactory] which should be replaced by an actual implementation.
 *
 * It provides factory methods which all throw an [UnsupportedOperationException] to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IMethodCallFormatter]
 * - [ITranslator]
 * - [ITranslationSupplier]
 * - [ILocaleOrderDecider]
 * - [IObjectFormatter]
 * - [IAssertionFormatterFacade]
 * - [IAssertionFormatterController]
 * - [IAssertionFormatter]
 * - [IReporter]
 */
object AtriumFactory : IAtriumFactory {

    private const val ERROR_MSG = "The atrium-core-api-late-binding should only be used as a compileOnly dependency, " +
        "meaning as a substitute for a real implementation"

    override fun <T : Any> newReportingPlant(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlant<T>
        = throwUnsupportedOperationException()

    override fun <T : Any?> newReportingPlantNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlantNullable<T>
        = throwUnsupportedOperationException()

    override fun <T : Any> newCheckingPlant(subject: T): ICheckingAssertionPlant<T>
        = throwUnsupportedOperationException()

    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): ICollectingAssertionPlant<T>
        = throwUnsupportedOperationException()

    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = throwUnsupportedOperationException()

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = throwUnsupportedOperationException()

    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: IBaseAssertionPlant<T, *>): IAssertionChecker
        = throwUnsupportedOperationException()

    override fun newMethodCallFormatter(): IMethodCallFormatter
        = throwUnsupportedOperationException()

    override fun newTranslator(translationSupplier: ITranslationSupplier, localeOrderDecider: ILocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator
        = throwUnsupportedOperationException()

    override fun newPropertiesBasedTranslationSupplier(): ITranslationSupplier
        = throwUnsupportedOperationException()

    override fun newLocaleOrderDecider(): ILocaleOrderDecider
        = throwUnsupportedOperationException()

    override fun newDetailedObjectFormatter(translator: ITranslator): IObjectFormatter
        = throwUnsupportedOperationException()

    override fun newAssertionFormatterController(): IAssertionFormatterController
        = throwUnsupportedOperationException()

    override fun newAssertionFormatterFacade(assertionFormatterController: IAssertionFormatterController): IAssertionFormatterFacade
        = throwUnsupportedOperationException()

    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController): IAssertionFormatter
        = throwUnsupportedOperationException()

    override fun registerSameLineTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
        assertionFormatterFacade: IAssertionFormatterFacade,
        objectFormatter: IObjectFormatter,
        translator: ITranslator
    ): Unit = throwUnsupportedOperationException()

    override fun newOnlyFailureReporter(assertionFormatterFacade: IAssertionFormatterFacade): IReporter
        = throwUnsupportedOperationException()

    private fun throwUnsupportedOperationException(): Nothing
        = throw UnsupportedOperationException(ERROR_MSG)
}
