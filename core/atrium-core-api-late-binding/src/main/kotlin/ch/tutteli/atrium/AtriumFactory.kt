package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.checking.AssertionChecker
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
 * - [AssertionPlant]
 * - [AssertionPlantNullable]
 * - [CheckingAssertionPlant]
 * - [CollectingAssertionPlant]
 * - [AssertionChecker]
 * - [MethodCallFormatter]
 * - [Translator]
 * - [TranslationSupplier]
 * - [LocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [AssertionPairFormatter]
 * - [Reporter]
 */
object AtriumFactory : IAtriumFactory {

    private const val ERROR_MSG = "The atrium-core-api-late-binding should only be used as a compileOnly dependency, " +
        "meaning as a substitute for a real implementation"

    override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = throwUnsupportedOperationException()

    override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = throwUnsupportedOperationException()

    override fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>
        = throwUnsupportedOperationException()

    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = throwUnsupportedOperationException()

    override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = throwUnsupportedOperationException()

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker
        = throwUnsupportedOperationException()

    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
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

    override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator): AssertionPairFormatter
        = throwUnsupportedOperationException()

    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = throwUnsupportedOperationException()

    override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): Unit = throwUnsupportedOperationException()

    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = throwUnsupportedOperationException()

    private fun throwUnsupportedOperationException(): Nothing
        = throw UnsupportedOperationException(ERROR_MSG)
}
