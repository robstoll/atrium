package ch.tutteli.atrium.core

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.*
import kotlin.reflect.KClass

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
 * - [AssertionChecker]
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
     * Creates a [ReportingAssertionContainer] which checks and reports added [Assertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking,
     * uses [maybeSubject] as [AssertionContainerWithCommonFields.CommonFields.maybeSubject] and also as
     * [AssertionContainerWithCommonFields.CommonFields.representation].
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionContainerWithCommonFields.CommonFields.assertionVerb]).
     * @param maybeSubject Used as [AssertionContainerWithCommonFields.CommonFields.maybeSubject] and
     *   also as [AssertionContainerWithCommonFields.CommonFields.representation].
     * @param reporter The reporter which will be used for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion container.
     */
    fun <T> newReportingAssertionContainer(
        assertionVerb: Translatable,
        maybeSubject: Option<T>,
        reporter: Reporter
    ): ReportingAssertionContainer<T> = newReportingAssertionContainer(
        assertionVerb, maybeSubject, newThrowingAssertionChecker(reporter)
    )

    /**
     * Creates a [ReportingAssertionPlant] which checks and reports added [Assertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking,
     * uses [subjectProvider] as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid undesired side effects
     * (the provider is most likely called more than once).
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param reporter The reporter which will be used for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    @Suppress("DEPRECATION")
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead",
        ReplaceWith(
            "this.newReportingAssertionContainer(assertionVerb, Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */), reporter)",
            "ch.tutteli.atrium.core.Some"
        )
    )
    fun <T : Any> newReportingPlant(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        reporter: Reporter
    ): ReportingAssertionPlant<T> = newReportingPlant(
        assertionVerb, subjectProvider, newThrowingAssertionChecker(reporter)
    )

    /**
     * Creates a [ReportingAssertionContainer] which checks and reports added [Assertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking, uses [maybeSubject] as
     * [AssertionContainerWithCommonFields.CommonFields.maybeSubject] and also as
     * [AssertionContainerWithCommonFields.CommonFields.representation].
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionContainerWithCommonFields.CommonFields.assertionVerb]).
     * @param maybeSubject Used as [AssertionContainerWithCommonFields.CommonFields.maybeSubject] and
     *   also as [AssertionContainerWithCommonFields.CommonFields.representation].
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     *   (see [AssertionContainerWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T> newReportingAssertionContainer(
        assertionVerb: Translatable,
        maybeSubject: Option<T>,
        assertionChecker: AssertionChecker
    ): ReportingAssertionContainer<T> {
        return newReportingAssertionContainer(
            AssertionContainerWithCommonFields.CommonFields(
                assertionVerb,
                maybeSubject,
                LazyRepresentation { maybeSubject.getOrElse { RawString.create(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG) } },
                assertionChecker,
                RawString.NULL
            )
        )
    }

    /**
     * Creates a [ReportingAssertionPlant] which checks and reports added [Assertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking, uses [subjectProvider] as
     * [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    @Suppress("DEPRECATION")
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead",
        ReplaceWith(
            "this.newReportingAssertionContainer(assertionVerb, Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */), assertionChecker)",
            "ch.tutteli.atrium.core.Some"
        )
    )
    fun <T : Any> newReportingPlant(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        assertionChecker: AssertionChecker
    ): ReportingAssertionPlant<T> {
        val evalOnceSubjectProvider = subjectProvider.evalOnce()
        return newReportingPlant(
            AssertionPlantWithCommonFields.CommonFields(
                assertionVerb,
                evalOnceSubjectProvider,
                evalOnceSubjectProvider,
                assertionChecker,
                RawString.NULL
            )
        )
    }

    /**
     * Creates a [ReportingAssertionContainer] which checks and reports added [Assertion]s.
     *
     * It uses the [AssertionContainerWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for
     * assertion checking
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T> newReportingAssertionContainer(
        commonFields: AssertionContainerWithCommonFields.CommonFields<T>
    ): ReportingAssertionContainer<T>

    /**
     * Creates a [ReportingAssertionPlant] which checks and reports added [Assertion]s.
     *
     * It uses the [AssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for
     * assertion checking
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead",
        ReplaceWith("this.newReportingAssertionContainer(commonFields)")
    )
    fun <T : Any> newReportingPlant(
        commonFields: AssertionPlantWithCommonFields.CommonFields<T>
    ): ReportingAssertionPlant<T>


    /**
     * Creates a [ReportingAssertionPlant] which [AssertionPlant.addAssertionsCreatedBy] the
     * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
     * the configured [Reporter]) reported as a whole.
     *
     * It creates a [CoreFactory.newThrowingAssertionChecker] based on the given [reporter] for assertion checking,
     * uses [subjectProvider] as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param reporter The reporter which will be used for a [newThrowingAssertionChecker].
     * @param assertionCreator The
     *
     * @return The newly created [AssertionPlant] which can be used to postulate further assertions.
     *
     * @throws AssertionError The newly created [AssertionPlant] might throw an [AssertionError] in case a
     *   created [Assertion] does not hold.
     */
    @Suppress("DEPRECATION")
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead",
        ReplaceWith(
            "this.newReportingAssertionContainer(assertionVerb, Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */), reporter).addAssertionsCreatedBy(assertionCreator)",
            "ch.tutteli.atrium.core.Some"
        )
    )
    fun <T : Any> newReportingPlantAndAddAssertionsCreatedBy(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        reporter: Reporter,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ) = newReportingPlant(assertionVerb, subjectProvider, reporter)
        .addAssertionsCreatedBy(assertionCreator)

    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the [AssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for
     * assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead",
        ReplaceWith("this.newReportingAssertionContainer(commonFields)")
    )
    fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>

    /**
     * Creates a [CheckingAssertionPlant] which provides a method to check whether
     * [allAssertionsHold][CheckingAssertionPlant.allAssertionsHold].
     *
     * @param subjectProvider The provider which provides the subject for which this plant will
     * create and check [Assertion]s.
     *
     * @return The newly created assertion plant.
     */
    @Deprecated(
        "Switch from Assert to Expect and use newCollectingAssertionContainer instead",
        ReplaceWith(
            "this.newCollectingAssertionContainer(Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */))",
            "ch.tutteli.atrium.core.Some"
        )
    )
    fun <T : Any> newCheckingPlant(subjectProvider: () -> T): CheckingAssertionPlant<T>

    /**
     * Creates a [CollectingAssertionContainer] which is intended to be used as receiver object in lambdas so that it
     * can collect [Assertion]s created inside the lambda.
     *
     * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
     *   [None] in case a previous subject change was not successful.
     *
     * @return The newly created assertion container.
     */
    fun <T> newCollectingAssertionContainer(maybeSubject: Option<T>): CollectingAssertionContainer<T>

    /**
     * Creates a [CollectingAssertionPlant] which is intended to be used as receiver object in lambdas to
     * collect created [Assertion]s inside the lambda.
     *
     * Notice, that the plant might not provide a [CollectingAssertionPlant.subject] in which case it
     * throws a [PlantHasNoSubjectException] if subject is accessed.
     * Use [newCheckingPlant] instead if you want to know whether the assertions hold.
     *
     * @param subjectProvider The function which will either provide the subject for this plant or throw an
     *   [PlantHasNoSubjectException] in case it cannot provide it. A [CollectingAssertionPlant] should evaluate the
     *   [subjectProvider] only once.
     *
     * @return The newly created assertion plant.
     */
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newCollectingAssertionContainer instead",
        ReplaceWith(
            "this.newCollectingAssertionContainer(Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */))",
            "ch.tutteli.atrium.core.Some"
        )
    )
    fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>


    /**
     * Creates a [CollectingAssertionPlantNullable] which is intended to be used as receiver object in lambdas to
     * collect created [Assertion]s inside the lambda.
     *
     * Notice, that the plant might not provide a [CollectingAssertionPlantNullable.subject] in which case it
     * throws a [PlantHasNoSubjectException] if subject is accessed.
     *
     * @param subjectProvider The function which will either provide the subject for this plant or throw an
     *   [PlantHasNoSubjectException] in case it cannot provide it. A [CollectingAssertionPlant] should evaluate the
     *   [subjectProvider] only once.
     *
     * @return The newly created assertion plant.
     */
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newCollectingAssertionContainer instead",
        ReplaceWith(
            "this.newCollectingAssertionContainer(Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */))",
            "ch.tutteli.atrium.core.Some"
        )
    )
    fun <T> newCollectingPlantNullable(subjectProvider: () -> T): CollectingAssertionPlantNullable<T>


    /**
     * Creates an [AssertionChecker] which throws [AtriumError]s in case an assertion fails
     * and uses the given [reporter] for reporting.
     *
     * @param reporter The reporter which is used to report [Assertion]s.
     *
     * @return The newly created assertion checker.
     */
    fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker

    /**
     * Creates an [AssertionChecker] which creates an [AssertionGroup] of [type][AssertionGroup.type]
     * [FeatureAssertionGroupType] instead of checking assertions and delegates this task to the given
     * [originalAssertionHolder] by adding (see [AssertionPlant.addAssertion]) the created assertion group to it.
     *
     * @param originalAssertionHolder The assertion plant to which the created [AssertionGroup] of [type][AssertionGroup.type]
     *   [FeatureAssertionGroupType] will be [added][AssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun newFeatureAssertionChecker(originalAssertionHolder: AssertionHolder): AssertionChecker

    /**
     * Creates an [AssertionChecker] which delegates the checking of [Assertion]s to the given
     * [originalAssertionHolder] by adding (see [AssertionHolder.addAssertion]) the assertions to the given
     * [originalAssertionHolder].
     *
     * @param originalAssertionHolder The assertion container to which the [Assertion]s will
     *   be [added][AssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun newDelegatingAssertionChecker(originalAssertionHolder: AssertionHolder): AssertionChecker


    /**
     * Creates an [AssertionChecker] which delegates the checking of [Assertion]s to the given [subjectPlant]
     * by adding (see [AssertionPlant.addAssertion]) the assertions to the given [subjectPlant].
     *
     * @param subjectPlant The assertion plant to which the [Assertion]s will be [added][AssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun <T> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker


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


/**
 * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
 *
 * It creates a [CoreFactory.newThrowingAssertionChecker] based on the given [reporter] for assertion checking,
 * uses [subjectProvider] as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
 * [AssertionPlantWithCommonFields.CommonFields.representationProvider].
 * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
 * (the provider is most likely called more than once).
 *
 * @param assertionVerb The assertion verb which will be used inter alia in reporting
 *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
 * @param subjectProvider Used as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
 *   also as [AssertionPlantWithCommonFields.CommonFields.representationProvider].
 * @param reporter The reporter which will be used for a [CoreFactory.newThrowingAssertionChecker].
 *
 * @return The newly created assertion plant.
 */
@Suppress("DEPRECATION")
@Deprecated(
    "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead",
    ReplaceWith(
        "this.newReportingAssertionContainer(assertionVerb, Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */), reporter)",
        "ch.tutteli.atrium.core.Some"
    )
)
fun <T : Any?> CoreFactoryCommon.newReportingPlantNullable(
    assertionVerb: Translatable,
    subjectProvider: () -> T,
    reporter: Reporter,
    nullRepresentation: Any = RawString.NULL
): ReportingAssertionPlantNullable<T> = newReportingPlantNullable(
    assertionVerb, subjectProvider, newThrowingAssertionChecker(reporter), nullRepresentation
)

/**
 * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
 *
 * It uses the given [assertionChecker] for assertion checking, uses [subjectProvider] as
 * [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
 * [AssertionPlantWithCommonFields.CommonFields.representationProvider].
 * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
 * (the provider is most likely called more than once).
 *
 * @param assertionVerb The assertion verb which will be used inter alia in reporting
 *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
 * @param subjectProvider Used as [AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
 *   also as [AssertionPlantWithCommonFields.CommonFields.representationProvider].
 * @param assertionChecker The checker which will be used to check [Assertion]s.
 *   (see [AssertionPlantWithCommonFields.CommonFields.assertionChecker]).
 *
 * @return The newly created assertion plant.
 */
@Suppress("DEPRECATION")
@Deprecated(
    "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead",
    ReplaceWith(
        "this.newReportingAssertionContainer(assertionVerb, Some(subjectProvider - /* define the subject here instead of subjectProvider - in case you have a transformation from an existing subject, then use maybeSubject.map { } */), assertionChecker)",
        "ch.tutteli.atrium.core.Some"
    )
)
fun <T : Any?> CoreFactoryCommon.newReportingPlantNullable(
    assertionVerb: Translatable,
    subjectProvider: () -> T,
    assertionChecker: AssertionChecker,
    nullRepresentation: Any = RawString.NULL
): ReportingAssertionPlantNullable<T> {
    val evalOnceSubjectProvider = subjectProvider.evalOnce()
    return newReportingPlantNullable(
        AssertionPlantWithCommonFields.CommonFields(
            assertionVerb,
            evalOnceSubjectProvider,
            evalOnceSubjectProvider,
            assertionChecker,
            nullRepresentation
        )
    )
}
