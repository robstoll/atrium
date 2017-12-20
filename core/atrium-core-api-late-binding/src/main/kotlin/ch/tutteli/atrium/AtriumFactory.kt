package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*

@Suppress("UNUSED_PARAMETER")
/**
 * A dummy implementation of [IAtriumFactory] which should be replaced by an actual implementation.
 *
 * It provides factory methods which all throw an [UnsupportedOperationException] to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [MethodCallFormatter]
 * - [Translator]
 * - [TranslationSupplier]
 * - [LocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [Reporter]
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

    override fun newThrowingAssertionChecker(reporter: Reporter): IAssertionChecker
        = throwUnsupportedOperationException()

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = throwUnsupportedOperationException()

    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: IBaseAssertionPlant<T, *>): IAssertionChecker
        = throwUnsupportedOperationException()

    override fun newMethodCallFormatter(): MethodCallFormatter
        = throwUnsupportedOperationException()

    override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator
        = throwUnsupportedOperationException()

    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = throwUnsupportedOperationException()

    override fun newLocaleOrderDecider(): LocaleOrderDecider
        = throwUnsupportedOperationException()

    override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = throwUnsupportedOperationException()

    override fun newAssertionFormatterController(): AssertionFormatterController
        = throwUnsupportedOperationException()

    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = throwUnsupportedOperationException()

    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun registerSameLineTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): Unit = throwUnsupportedOperationException()

    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = throwUnsupportedOperationException()

    private fun throwUnsupportedOperationException(): Nothing
        = throw UnsupportedOperationException(ERROR_MSG)
}
