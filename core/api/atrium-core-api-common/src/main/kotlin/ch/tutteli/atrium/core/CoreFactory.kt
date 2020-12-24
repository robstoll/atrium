package ch.tutteli.atrium.core

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.*
import kotlin.reflect.KClass

@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@Experimental
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class ExperimentalNewExpectTypes

/**
 * The access point to an implementation of [CoreFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val coreFactory by lazy { loadSingleService(CoreFactory::class) }

/**
 * The platform specific minimum contract of the 'abstract factory' of atrium-core.
 *
 * It inherits methods from [CoreFactoryCommon] where the `actual` or in other words platform specific interfaces
 * might add additional methods.
 *
 * Notice, the platform specific types have to define the default methods for `newReportingPlantNullable`
 * (otherwise we are not binary backward compatible) -> will be moved to CoreFactoryCommon with 1.0.0
 */
expect interface CoreFactory : CoreFactoryCommon

/**
 * The minimum contract of the 'abstract factory' of atrium-core for any platform.
 *
 * It provides factory methods to create:
 * - [ReportingAssertionContainer]
 * - [CollectingAssertionContainer]
 * - [ch.tutteli.atrium.checking.AssertionChecker]
 * - [MethodCallFormatter]
 * - [Translator]
 * - [LocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [AssertionPairFormatter]
 * - [Reporter]
 * - [AtriumErrorAdjuster]
 */
interface CoreFactoryCommon {

    /**
     * Creates a [MethodCallFormatter] which represents arguments of a method call by using their [Any.toString]
     * representation with the exception of:
     * - [CharSequence], is wrapped in quotes (`"`) and line breaks (CR or/and LF) are escaped so that the
     *   whole representation remains on one line.
     * - [Char] is wrapped in apostrophes (`'`)
     *
     * @return The newly created method call formatter.
     */
    fun newMethodCallFormatter(): MethodCallFormatter


    /**
     * Creates a [Translator] which translates [Translatable]s to [primaryLocale] and falls back
     * to [fallbackLocales] (in the given order) in case no translation exists for [primaryLocale].
     *
     * It uses the given [translationSupplier] to retrieve all available translations.
     * In case no translation exists for a given property (neither for the [primaryLocale] nor for
     * any [fallbackLocales]) then it uses [Translatable]'s [getDefault][Translatable.getDefault].
     *
     * Please refer to the documentation of [Translator] to see to which extend a translator has to be compatible
     * with Java's `ResourceBundle`.
     *
     * @param translationSupplier Provides the translations for a desired [Locale].
     * @param localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
     *   given [Translatable].
     * @param primaryLocale The [Locale] to which the translator translates per default.
     * @param fallbackLocales Used in case a translation for a given [Translatable] is not defined for [primaryLocale]
     *   or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
     *
     * @return The newly created translator.
     *
     * @throws IllegalArgumentException in case [primaryLocale] or [fallbackLocales] have as language `no` or if they
     *   have: as language `zh`, country is not set and script is either `Hant` or `Hans`.
     */
    fun newTranslator(
        translationSupplier: TranslationSupplier,
        localeOrderDecider: LocaleOrderDecider,
        primaryLocale: Locale,
        fallbackLocales: List<Locale>
    ): Translator

    /**
     * Creates a [LocaleOrderDecider] which decides in which order [Locale]s are processed to find a translation for a
     * given [Translatable].
     *
     * Please refer to the documentation of [LocaleOrderDecider] to see to which extend a translator has to be
     * compatible with Java's `ResourceBundle`.
     *
     * @return The newly created [Locale] order decider.
     */
    fun newLocaleOrderDecider(): LocaleOrderDecider


    /**
     * Creates an [ObjectFormatter] which represents objects by using their [Any.toString] representation
     * including [KClass.qualifiedName] (and possibly further platform specific declarations).
     *
     * @return The newly created object formatter.
     */
    fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter

    /**
     * Creates an [AssertionFormatterController] which all be used per default for [newAssertionFormatterFacade].
     *
     * @return The newly created assertion formatter controller.
     */
    fun newAssertionFormatterController(): AssertionFormatterController

    /**
     * Creates an [AssertionFormatterFacade] which shall be used per default for [newOnlyFailureReporter].
     *
     * @param assertionFormatterController The [AssertionFormatterController] which shall be used for formatting.
     *
     * @return The newly created assertion formatter facade.
     */
    fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade

    /**
     * Creates an [AssertionPairFormatter] which is intended for text output (e.g. for the console) and puts assertion
     * pairs on the same line.
     *
     * As an example `assert(10).toBe(9)` results in the following error:
     * ```
     * assert: 10        (java.lang.Integer <934275857>)
     * ◆ to be: 9        (java.lang.Integer <1364913072>)
     * ```
     *
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as
     *   [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextSameLineAssertionPairFormatter(
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): AssertionPairFormatter

    /**
     * Creates an [AssertionPairFormatter] which is intended for text output (e.g. for the console) and puts assertion
     * pairs on separate lines
     *
     * As an example `assert(10).toBe(9)` results in the following error:
     * ```
     * assert:
     *   10        (java.lang.Integer <934275857>)
     * ◆ to be:
     *   9        (java.lang.Integer <1364913072>)
     * ```
     *
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as
     *   [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextNextLineAssertionPairFormatter(
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): AssertionPairFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and serves as
     * fallback if no other formatter is able to format a given [Assertion].
     *
     * Typically this includes the formatting of the [AssertionGroup] with a [RootAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [RootAssertionGroupType] as prefix for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as
     *   [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFallbackAssertionFormatter(
        bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
        assertionFormatterController: AssertionFormatterController,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [AssertionGroup]s of type [FeatureAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [PrefixFeatureAssertionGroupHeader] as prefix of the group header and [FeatureAssertionGroupType] as prefix
     * for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as
     *   [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFeatureAssertionGroupFormatter(
        bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
        assertionFormatterController: AssertionFormatterController,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [AssertionGroup]s of type [ListAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [ListAssertionGroupType] as prefix for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as
     *   [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextListAssertionGroupFormatter(
        bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
        assertionFormatterController: AssertionFormatterController,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [AssertionGroup]s of type [SummaryAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [SummaryAssertionGroupType], [PrefixFailingSummaryAssertion] and [PrefixSuccessfulSummaryAssertion]
     *   as prefix for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as
     *   [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextSummaryAssertionGroupFormatter(
        bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
        assertionFormatterController: AssertionFormatterController,
        objectFormatter: ObjectFormatter,
        translator: Translator
    ): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [AssertionGroup]s of type [ExplanatoryAssertionGroupType] by creating an
     * [AssertionFormatterParameterObject] which indicates that formatting its [AssertionGroup.assertions] happens
     * within an explanatory assertion group.
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [ExplanatoryAssertionGroupType] as prefix for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     *
     * @return The newly created assertion formatter.
     */
    fun newTextExplanatoryAssertionGroupFormatter(
        bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
        assertionFormatterController: AssertionFormatterController
    ): AssertionFormatter

    /**
     * Registers all available [AssertionFormatter]s  -- which are intended for text format (e.g. for the console)
     * -- to the given [assertionFormatterFacade] using the given [textAssertionPairFormatter].
     *
     * Should at least support [RootAssertionGroupType], [FeatureAssertionGroupType], [ListAssertionGroupType],
     * [SummaryAssertionGroupType] and [ExplanatoryAssertionGroupType] (see [AssertionBuilder]).
     *
     * @param bulletPoints The bullet points used in reporting to prefix each [Assertion] in
     *   [AssertionGroup.assertions].
     * @param assertionFormatterFacade The [AssertionFormatterFacade] to which all [AssertionFormatter]s with text
     *   reporting capabilities should be registered.
     * @param textAssertionPairFormatter An [AssertionPairFormatter] which is intended for text format.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [DescriptiveAssertion.description].
     */
    fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator
    )

    /**
     * Creates a [Reporter] which reports only failing assertions
     * and uses the given [assertionFormatterFacade] to format assertions and messages.
     *
     * @param assertionFormatterFacade The formatter which is used to format [Assertion]s.
     *
     * @return The newly created reporter.
     */
    fun newOnlyFailureReporter(
        assertionFormatterFacade: AssertionFormatterFacade,
        atriumErrorAdjuster: AtriumErrorAdjuster
    ): Reporter

    /**
     * An [AtriumErrorAdjuster] which does not modify a given [AtriumError].
     *
     * @return The newly created adjuster.
     */
    fun newNoOpAtriumErrorAdjuster(): AtriumErrorAdjuster

    /**
     * An [AtriumErrorAdjuster] which removes stack frames of test runners.
     *
     * @return The newly created adjuster.
     */
    fun newRemoveRunnerAtriumErrorAdjuster(): AtriumErrorAdjuster

    /**
     * An [AtriumErrorAdjuster] which removes stack frames of Atrium.
     *
     * @return The newly created adjuster.
     */
    fun newRemoveAtriumFromAtriumErrorAdjuster(): AtriumErrorAdjuster

    /**
     * An [AtriumErrorAdjuster] which delegates adjustment to the given [firstAdjuster], [secondAdjuster]
     * and optionally [otherAdjusters].
     *
     * @param firstAdjuster The first adjuster to which is delegated.
     * @param secondAdjuster The second adjuster to which is delegated.
     * @param otherAdjusters Optionally further [AtriumErrorAdjuster] which shall be involved in adjusting.
     *
     * @return The newly created adjuster.
     */
    fun newMultiAtriumErrorAdjuster(
        firstAdjuster: AtriumErrorAdjuster,
        secondAdjuster: AtriumErrorAdjuster,
        otherAdjusters: List<AtriumErrorAdjuster>
    ): AtriumErrorAdjuster
}
