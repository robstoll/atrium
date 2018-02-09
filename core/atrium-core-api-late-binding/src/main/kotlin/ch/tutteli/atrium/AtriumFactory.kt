package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*

/**
 * Deprecated, use CoreFactory instead, will be removed with 1.0.0
 */
@Deprecated("Use CoreFactory instead, will be removed with 1.0.0")
object AtriumFactory : IAtriumFactory {

    private const val ERROR_MSG = "The atrium-core-api-late-binding should only be used as a compileOnly dependency, " +
        "meaning as a substitute for a real implementation"

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newReportingPlant(commonFields)"))
    override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newReportingPlantNullable(commonFields)"))
    override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newCheckingPlant(subject)"))
    override fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newCollectingPlant(subjectProvider)"))
    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newThrowingAssertionChecker(reporter)"))
    override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newFeatureAssertionChecker(subjectPlant)"))
    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newDelegatingAssertionChecker(subjectPlant)"))
    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newMethodCallFormatter()"))
    override fun newMethodCallFormatter(): MethodCallFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTranslator(translationSupplier, localeOrderDecider, primaryLocale, *fallbackLocales)"))
    override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newPropertiesBasedTranslationSupplier()"))
    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newLocaleOrderDecider()"))
    override fun newLocaleOrderDecider(): LocaleOrderDecider
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newDetailedObjectFormatter(translator)"))
    override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newAssertionFormatterController()"))
    override fun newAssertionFormatterController(): AssertionFormatterController
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newAssertionFormatterFacade(assertionFormatterController)"))
    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextSameLineAssertionPairFormatter(objectFormatter, translator)"))
    override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator): AssertionPairFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextFeatureAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)"))
    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.registerTextAssertionFormatterCapabilities(bulletPoints, assertionFormatterFacade, textAssertionPairFormatter, objectFormatter, translator)"))
    override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): Unit = throwUnsupportedOperationException()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newOnlyFailureReporter(assertionFormatterFacade)"))
    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = throwUnsupportedOperationException()

    private fun throwUnsupportedOperationException(): Nothing
        = throw UnsupportedOperationException(ERROR_MSG)
}
