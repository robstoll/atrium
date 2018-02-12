package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.checking.DelegatingAssertionChecker
import ch.tutteli.atrium.checking.FeatureAssertionChecker
import ch.tutteli.atrium.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.*
import java.util.*

@Deprecated("use CoreFactory instead, will be removed with 1.0.0")
object AtriumFactory : IAtriumFactory {

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newReportingPlant(commonFields)"))
    override fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>
        = ReportingAssertionPlantImpl(commonFields)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newReportingPlantNullable(commonFields)"))
    override fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>
        = ReportingAssertionPlantNullableImpl(commonFields)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newCheckingPlant(subject)"))
    override fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>
        = CheckingAssertionPlantImpl(subject)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newCollectingPlant(subjectProvider)"))
    override fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>
        = CollectingAssertionPlantImpl(subjectProvider)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newThrowingAssertionChecker(reporter)"))
    override fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker
        = ThrowingAssertionChecker(reporter)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newFeatureAssertionChecker(subjectPlant)"))
    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newDelegatingAssertionChecker(subjectPlant)"))
    override fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker
        = DelegatingAssertionChecker(subjectPlant)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newMethodCallFormatter()"))
    override fun newMethodCallFormatter(): MethodCallFormatter
        = TextMethodCallFormatter

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTranslator(translationSupplier, localeOrderDecider, primaryLocale, *fallbackLocales)"))
    override fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator
        = TranslationSupplierBasedTranslator(translationSupplier, localeOrderDecider, primaryLocale, fallbackLocales)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newPropertiesBasedTranslationSupplier()"))
    override fun newPropertiesBasedTranslationSupplier(): TranslationSupplier
        = PropertiesPerEntityAndLocaleTranslationSupplier()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newLocaleOrderDecider()"))
    override fun newLocaleOrderDecider(): LocaleOrderDecider
        = CoroutineBasedLocaleOrderDecider()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newDetailedObjectFormatter(translator)"))
    override fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter
        = DetailedObjectFormatter(translator)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newAssertionFormatterController()"))
    override fun newAssertionFormatterController(): AssertionFormatterController
        = AssertionFormatterControllerImpl()

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newAssertionFormatterFacade(assertionFormatterController)"))
    override fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade
        = AssertionFormatterControllerBasedFacade(assertionFormatterController)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextSameLineAssertionPairFormatter(objectFormatter, translator)"))
    override fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator)
        = TextSameLineAssertionPairFormatter(objectFormatter, translator)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator), objectFormatter)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextFeatureAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextFeatureAssertionGroupFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, objectFormatter, translator)"))
    override fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter
        = TextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, newTextSameLineAssertionPairFormatter(objectFormatter, translator))

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)"))
    override fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter
        = TextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.registerTextAssertionFormatterCapabilities(bulletPoints, assertionFormatterFacade, textAssertionPairFormatter, objectFormatter, translator)"))
    override fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ) {
        assertionFormatterFacade.register { TextListAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter) }
        assertionFormatterFacade.register { TextFeatureAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter) }
        assertionFormatterFacade.register(::InvisibleAssertionGroupFormatter)
        assertionFormatterFacade.register { TextExplanatoryAssertionGroupFormatter(bulletPoints, it) }
        assertionFormatterFacade.register { TextIndentAssertionGroupFormatter(bulletPoints, it) }
        assertionFormatterFacade.register { TextSummaryAssertionGroupFormatter(bulletPoints, it, textAssertionPairFormatter) }
        assertionFormatterFacade.register { TextFallbackAssertionFormatter(bulletPoints, it, textAssertionPairFormatter, objectFormatter) }
    }

    @Deprecated("use CoreFactory, will be removed with 1.0.0", ReplaceWith("CoreFactory.newOnlyFailureReporter(assertionFormatterFacade)"))
    override fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
        = OnlyFailureReporter(assertionFormatterFacade)
}
