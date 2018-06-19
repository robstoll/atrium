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
@Deprecated("Use coreFactory instead, will be removed with 1.0.0", ReplaceWith("coreFactory", "ch.tutteli.atrium.core.coreFactory"))
object AtriumFactory : IAtriumFactory {

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newReportingPlant(commonFields)"))
    override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = ReportingAssertionPlantImpl(commonFields)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newReportingPlantNullable(commonFields)"))
    override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = ReportingAssertionPlantNullableImpl(commonFields)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newCheckingPlant(subject)"))
    override fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>
        = CheckingAssertionPlantImpl { subject }

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newCollectingPlant(subjectProvider)"))
    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = CollectingAssertionPlantImpl(subjectProvider)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newThrowingAssertionChecker(reporter)"))
    override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = ThrowingAssertionChecker(reporter)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newFeatureAssertionChecker(subjectPlant)"))
    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newDelegatingAssertionChecker(subjectPlant)"))
    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = DelegatingAssertionChecker(subjectPlant)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newMethodCallFormatter()"))
    override fun newMethodCallFormatter(): MethodCallFormatter
        = TextMethodCallFormatter

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTranslator(translationSupplier, localeOrderDecider, primaryLocale, *fallbackLocales)"))
    override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator
        = TranslationSupplierBasedTranslator(translationSupplier, localeOrderDecider, primaryLocale, fallbackLocales.toList())

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newPropertiesBasedTranslationSupplier()"))
    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = PropertiesPerEntityAndLocaleTranslationSupplier()

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newLocaleOrderDecider()"))
    override fun newLocaleOrderDecider(): LocaleOrderDecider
        = CoroutineBasedLocaleOrderDecider()

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newDetailedObjectFormatter(translator)"))
    override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = DetailedObjectFormatter(translator)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newAssertionFormatterController()"))
    override fun newAssertionFormatterController(): AssertionFormatterController
        = AssertionFormatterControllerImpl()

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newAssertionFormatterFacade(assertionFormatterController)"))
    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = AssertionFormatterControllerBasedFacade(assertionFormatterController)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextSameLineAssertionPairFormatter(objectFormatter, translator)"))
    override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextFallbackAssertionFormatter(bulletPoints.asSequence().map { it.key.kotlin to it.value }.toMap(), assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFallbackAssertionFormatter(
        toKClassBasedMap(bulletPoints),
        assertionFormatterController,
        newTextSameLineAssertionPairFormatter(objectFormatter, translator),
        objectFormatter
    )

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextFeatureAssertionGroupFormatter(bulletPoints.asSequence().map { it.key.kotlin to it.value }.toMap(), assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFeatureAssertionGroupFormatter(
        toKClassBasedMap(bulletPoints),
        assertionFormatterController,
        newTextSameLineAssertionPairFormatter(objectFormatter, translator)
    )

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextListAssertionGroupFormatter(bulletPoints.asSequence().map { it.key.kotlin to it.value }.toMap(), assertionFormatterController, objectFormatter, translator)"))
    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextListAssertionGroupFormatter(
        toKClassBasedMap(bulletPoints),
        assertionFormatterController,
        newTextSameLineAssertionPairFormatter(objectFormatter, translator)
    )

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints.asSequence().map { it.key.kotlin to it.value }.toMap(), assertionFormatterController)"))
    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = TextExplanatoryAssertionGroupFormatter(toKClassBasedMap(bulletPoints), assertionFormatterController)

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.registerTextAssertionFormatterCapabilities(bulletPoints.asSequence().map { it.key.kotlin to it.value }.toMap(), assertionFormatterFacade, textAssertionPairFormatter, objectFormatter, translator)"))
    override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ) {
        assertionFormatterFacade.register {
            TextListAssertionGroupFormatter(toKClassBasedMap(bulletPoints), it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register {
            TextFeatureAssertionGroupFormatter(toKClassBasedMap(bulletPoints), it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register {
            TextExplanatoryAssertionGroupFormatter(toKClassBasedMap(bulletPoints), it)
        }
        assertionFormatterFacade.register {
            TextIndentAssertionGroupFormatter(toKClassBasedMap(bulletPoints), it)
        }
        assertionFormatterFacade.register {
            TextSummaryAssertionGroupFormatter(toKClassBasedMap(bulletPoints), it, textAssertionPairFormatter)
        }
        assertionFormatterFacade.register {
            TextFallbackAssertionFormatter(toKClassBasedMap(bulletPoints), it, textAssertionPairFormatter, objectFormatter)
        }
    }

    private fun toKClassBasedMap(bulletPoints: Map<Class<out BulletPointIdentifier>, String>) =
        bulletPoints.asSequence().map { it.key.kotlin to it.value }.toMap()

    @Deprecated("Use coreFactory, will be removed with 1.0.0", ReplaceWith("coreFactory.newOnlyFailureReporter(assertionFormatterFacade)"))
    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = OnlyFailureReporter(assertionFormatterFacade)
}
