package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.robstoll.lib.checking.DelegatingAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.checking.FeatureAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.core.robstoll.lib.creating.CheckingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.CollectingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.ReportingAssertionPlantImpl
import ch.tutteli.atrium.core.robstoll.lib.creating.ReportingAssertionPlantNullableImpl
import ch.tutteli.atrium.core.robstoll.lib.reporting.*
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.CoroutineBasedLocaleOrderDecider
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.PropertiesPerEntityAndLocaleTranslationSupplier
import ch.tutteli.atrium.core.robstoll.lib.reporting.translating.TranslationSupplierBasedTranslator
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

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newReportingPlant(commonFields)"))
    override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = ReportingAssertionPlantImpl(commonFields)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newReportingPlantNullable(commonFields)"))
    override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = ReportingAssertionPlantNullableImpl(commonFields)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newCheckingPlant(subject)"))
    override fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>
        = CheckingAssertionPlantImpl(subject)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newCollectingPlant(subjectProvider)"))
    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = CollectingAssertionPlantImpl(subjectProvider)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newThrowingAssertionChecker(reporter)"))
    override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = ThrowingAssertionChecker(reporter)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newFeatureAssertionChecker(subjectPlant)"))
    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newDelegatingAssertionChecker(subjectPlant)"))
    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = DelegatingAssertionChecker(subjectPlant)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newMethodCallFormatter()"))
    override fun newMethodCallFormatter(): MethodCallFormatter
        = TextMethodCallFormatter

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTranslator(translationSupplier, localeOrderDecider, primaryLocale, *fallbackLocales)"))
    override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator
        = TranslationSupplierBasedTranslator(translationSupplier, localeOrderDecider, primaryLocale, fallbackLocales)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newPropertiesBasedTranslationSupplier()"))
    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = PropertiesPerEntityAndLocaleTranslationSupplier()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newLocaleOrderDecider()"))
    override fun newLocaleOrderDecider(): LocaleOrderDecider
        = CoroutineBasedLocaleOrderDecider()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newDetailedObjectFormatter(translator)"))
    override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = DetailedObjectFormatter(translator)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newAssertionFormatterController()"))
    override fun newAssertionFormatterController(): AssertionFormatterController
        = AssertionFormatterControllerImpl()

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newAssertionFormatterFacade(assertionFormatterController)"))
    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = AssertionFormatterControllerBasedFacade(assertionFormatterController)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextSameLineAssertionPairFormatter(objectFormatter, translator)"))
    override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFallbackAssertionFormatter(
        bulletPoints,
        assertionFormatterController,
        newTextSameLineAssertionPairFormatter(objectFormatter, translator),
        objectFormatter
    )

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextFeatureAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFeatureAssertionGroupFormatter(
        bulletPoints,
        assertionFormatterController,
        newTextSameLineAssertionPairFormatter(objectFormatter, translator)
    )

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextListAssertionGroupFormatter(
        bulletPoints,
        assertionFormatterController,
        newTextSameLineAssertionPairFormatter(objectFormatter, translator)
    )

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)"))
    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = TextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.registerTextAssertionFormatterCapabilities(bulletPoints, assertionFormatterFacade, textAssertionPairFormatter, objectFormatter, translator)"))
    override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
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
        assertionFormatterFacade.register(::InvisibleAssertionGroupFormatter)

        assertionFormatterFacade.register {
            TextExplanatoryAssertionGroupFormatter(bulletPoints, it)
        }
        assertionFormatterFacade.register {
            TextIndentAssertionGroupFormatter(bulletPoints, it)
        }
        assertionFormatterFacade.register {
            TextSummaryAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register {
            TextFallbackAssertionFormatter(bulletPoints, it, textAssertionPairFormatter, objectFormatter)
        }
    }

    @Deprecated("use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newOnlyFailureReporter(assertionFormatterFacade)"))
    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = OnlyFailureReporter(assertionFormatterFacade)
}
