package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*

/**
 * Deprecated, use [coreFactory] instead (uses [ServiceLoader]), will be removed with 1.0.0
 */
@Deprecated("use coreFactory instead, will be removed with 1.0.0", ReplaceWith("coreFactory", "ch.tutteli.atrium.core.coreFactory"))
object AtriumFactory : IAtriumFactory {

    private const val ERROR_MSG = "The atrium-core-api-late-binding should only be used as a compileOnly dependency, " +
        "meaning as a substitute for a real implementation"

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newReportingPlant(commonFields)"))
    override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newReportingPlantNullable(commonFields)"))
    override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newCheckingPlant(subject)"))
    override fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newCollectingPlant(subjectProvider)"))
    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newThrowingAssertionChecker(reporter)"))
    override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newFeatureAssertionChecker(subjectPlant)"))
    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newDelegatingAssertionChecker(subjectPlant)"))
    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newMethodCallFormatter()"))
    override fun newMethodCallFormatter(): MethodCallFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTranslator(translationSupplier, localeOrderDecider, primaryLocale, *fallbackLocales)"))
    override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newPropertiesBasedTranslationSupplier()"))
    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newLocaleOrderDecider()"))
    override fun newLocaleOrderDecider(): LocaleOrderDecider
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newDetailedObjectFormatter(translator)"))
    override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newAssertionFormatterController()"))
    override fun newAssertionFormatterController(): AssertionFormatterController
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newAssertionFormatterFacade(assertionFormatterController)"))
    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextSameLineAssertionPairFormatter(objectFormatter, translator)"))
    override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator): AssertionPairFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextFeatureAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)"))
    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.registerTextAssertionFormatterCapabilities(bulletPoints, assertionFormatterFacade, textAssertionPairFormatter, objectFormatter, translator)"))
    override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): Unit = throwUnsupportedOperationException()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newOnlyFailureReporter(assertionFormatterFacade)"))
    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = throwUnsupportedOperationException()

    private fun throwUnsupportedOperationException(): Nothing
        = throw UnsupportedOperationException(ERROR_MSG)
}
