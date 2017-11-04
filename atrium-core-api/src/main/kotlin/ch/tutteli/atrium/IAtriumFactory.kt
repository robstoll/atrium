@file:JvmName("IAtriumFactoryExtensions")

package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.ICollectingAssertionPlant.PlantHasNoSubjectException
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import java.util.*
import kotlin.reflect.KClass

/**
 * The minimum contract of the 'abstract factory' of atrium.
 *
 * It is extended with the following extension functions defined in the atrium-api (in the same file as this interface):
 * - [ch.tutteli.atrium.newReportingPlantCheckLazilyAtTheEnd]
 * - [ch.tutteli.atrium.newDownCastBuilder]
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IMethodCallFormatter]
 * - [ITranslator]
 * - [IObjectFormatter]
 * - [IAssertionFormatterFacade]
 * - [IAssertionFormatterController]
 * - [IAssertionFormatter]
 * - [IReporter]
 * - [IDownCastBuilder]
 * - [IThrowableFluent]
 */
interface IAtriumFactory {
    /**
     * Creates an [IReportingAssertionPlant] which does not check and report the created or
     * added [IAssertion]s until one calls [IReportingAssertionPlant.checkAssertions].
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlantCheckLazily(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IReportingAssertionPlant<T>
        = newReportingPlantCheckLazily(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IReportingAssertionPlant] which does not check and report the created or
     * added [IAssertion]s until one calls [IReportingAssertionPlant.checkAssertions].
     *
     * It uses the given [assertionChecker] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker The checker which will be used to check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlantCheckLazily(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IReportingAssertionPlant<T>
        = newReportingPlantCheckLazily(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IReportingAssertionPlant] which does not check and report the created or
     * added [IAssertion]s until one calls [IReportingAssertionPlant.checkAssertions].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlantCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlant<T>

    /**
     * Creates an [IReportingAssertionPlant] which immediately checks and reports added [IAssertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlantCheckImmediately(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IReportingAssertionPlant<T>
        = newReportingPlantCheckImmediately(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IReportingAssertionPlant] which immediately checks and reports added [IAssertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker The checker which will be used to check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlantCheckImmediately(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IReportingAssertionPlant<T>
        = newReportingPlantCheckImmediately(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IReportingAssertionPlant] which immediately checks and reports added [IAssertion]s.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlantCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlant<T>


    /**
     * Creates an [IReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IReportingAssertionPlantNullable<T>
        = newReportingPlantNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the given [assertionChecker] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker The checker which will be used to check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IReportingAssertionPlantNullable<T>
        = newReportingPlantNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlantNullable<T>

    /**
     * Creates an [ICheckingAssertionPlant] which provides a method to check whether
     * [allAssertionsHold][ICheckingAssertionPlant.allAssertionsHold].
     *
     * @param subject The subject for which this plant will create [IAssertion]s.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCheckingPlant(subject: T): ICheckingAssertionPlant<T>

    /**
     * Creates an [ICollectingAssertionPlant] which is intended to be used as receiver object in lambdas to collect
     * created [IAssertion]s inside the lambda.
     *
     * Notice, that this [IAssertionPlant] might not even provide a [IAssertionPlant.subject] in which case it
     * throws an [PlantHasNoSubjectException] if [subject][IAssertionPlant.subject] is accessed.
     * Use [newCheckingPlant] instead if you want to know whether the assertions hold.
     *
     * @param subjectProvider The function which will either provide the subject for this plant or throw an
     * [PlantHasNoSubjectException] in case it cannot be provided.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCollectingPlant(subjectProvider: () -> T): ICollectingAssertionPlant<T>

    /**
     * Creates an [IThrowableFluent] based on the given [assertionVerb] and the [act] function.
     *
     * It uses the given [reporter] for reporting.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param act The function which is expected to throw a [Throwable] which in turn will be used as subject
     *        for postulated [IAssertion]s (see [ThrowableFluent] and
     *        [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter used to create a [newThrowingAssertionChecker] and used for failure reporting.
     *
     * @return The newly created [IThrowableFluent].
     */
    fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, reporter: IReporter): IThrowableFluent

    /**
     * Creates an [IThrowableFluent] based on the given [assertionVerb] and the [act] function.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param act The function which is expected to throw a [Throwable] which in turn will be used as subject
     *        for postulated [IAssertion]s (see [ThrowableFluent] and
     *        [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker Used to report failures (see [IAssertionChecker.fail]
     *        and [IAssertionPlantWithCommonFields.CommonFields.assertionChecker])).
     *
     * @return The newly created [IThrowableFluent].
     */
    fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): IThrowableFluent

    /**
     * Creates an [IAssertionChecker] which throws [AssertionError]s in case an assertion fails
     * and uses the given [reporter] for reporting.
     *
     * @param reporter The reporter which is used to report [IAssertion]s.
     *
     * @return The newly created assertion checker.
     */
    fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker

    /**
     * Creates an [IAssertionChecker] which creates an [IAssertionGroup] of [type][IAssertionGroup.type]
     * [IFeatureAssertionGroupType] instead of checking assertions and delegates this task to the given
     * [subjectPlant] by adding (see [IAssertionPlant.addAssertion]) the created assertion group to it.
     *
     * @param subjectPlant The assertion plant to which the created [IAssertionGroup] of [type][IAssertionGroup.type]
     *        [IFeatureAssertionGroupType] will be [added][IAssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker

    /**
     * Creates an [IMethodCallFormatter] which represents arguments of a method call by using their [Object.toString]
     * representation with the exception of:
     * - [CharSequence], is wrapped in quotes (`"`) and line breaks (CR or/and LF) are escaped so that the
     *   whole representation remains on one line.
     * - [Char] is wrapped in apostrophes (`'`)
     *
     * @return The newly created method call formatter.
     */
    fun newMethodCallFormatter(): IMethodCallFormatter


    /**
     * Creates an [ITranslator] which translates [ITranslatable]s to [primaryLocale] and falls back
     * to [fallbackLocales] (in the given order) in case no translation exists for [primaryLocale].
     *
     * In case neither a translation exists for any [fallbackLocales] then it uses
     * [ITranslatable]'s [getDefault][ITranslatable.getDefault].
     * It uses the given [translationSupplier] to retrieve all available translations.
     *
     * @param translationSupplier Provides the translations for
     * @param primaryLocale The [Locale] to which the translator translates per default.
     * @param fallbackLocales Used in case a translation for a given [ITranslatable] is not defined for
     *                        [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used
     *                        in the given order.
     *
     * @return The newly created translator.
     */
    fun newTranslator(translationSupplier: ITranslationSupplier, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator

    /**
     * Creates an [IObjectFormatter] which represents objects by using their [Object.toString] representation
     * including [Class.name] and their [System.identityHashCode].
     *
     * @return The newly created object formatter.
     */
    fun newDetailedObjectFormatter(translator: ITranslator): IObjectFormatter

    /**
     * Creates an [IAssertionFormatterController] which all be used per default for [newAssertionFormatterFacade].
     *
     * @return The newly created assertion formatter controller.
     */
    fun newAssertionFormatterController(): IAssertionFormatterController

    /**
     * Creates an [IAssertionFormatterFacade] which shall be used per default for [newOnlyFailureReporter].
     *
     * @param assertionFormatterController The [IAssertionFormatterController] which shall be used for formatting.
     *
     * @return The newly created assertion formatter facade.
     */
    fun newAssertionFormatterFacade(assertionFormatterController: IAssertionFormatterController): IAssertionFormatterFacade

    /**
     * Creates an [IAssertionFormatter] which is intended for text output (e.g. for the console) and serves as
     * fallback if no other formatter is able to format a given [IAssertion].
     *
     * Typically this includes the formatting of the [IAssertionGroup] with a [RootAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [RootAssertionGroupType] as prefix for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [ITranslatable] such as [IBasicAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter

    /**
     * Creates an [IAssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [IAssertionGroup]s of type [IFeatureAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [PrefixFeatureAssertionGroupHeader] as prefix of the group header and [IFeatureAssertionGroupType] as prefix
     * for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [ITranslatable] such as [IBasicAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter

    /**
     * Creates an [IAssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [IAssertionGroup]s of type [IListAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [IListAssertionGroupType] as prefix for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [ITranslatable] such as [IBasicAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter

    /**
     * Creates an [IAssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [IAssertionGroup]s of type [IExplanatoryAssertionGroupType] by creating an
     * [AssertionFormatterMethodObject] which indicates that formatting its [IAssertionGroup.assertions] happens within
     * an explanatory assertion group.
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [IExplanatoryAssertionGroupType] as prefix for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     *
     * @return The newly created assertion formatter.
     */
    fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController): IAssertionFormatter

    /**
     * Registers all available [IAssertionFormatter]s -- which put assertion pairs on the same line and report in
     * text format (e.g. for the console) -- to the given [assertionFormatterFacade].
     *
     * Should at least support [RootAssertionGroupType], [IFeatureAssertionGroupType], [IListAssertionGroupType],
     * [ISummaryAssertionGroupType] and [IExplanatoryAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting to prefix each [IAssertion] in
     * [IAssertionGroup.assertions].
     * @param assertionFormatterFacade The [IAssertionFormatterFacade] to which all [IAssertionFormatter]s with
     *        same line capabilities and text reporting should be registered.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [ITranslatable] such as [IBasicAssertion.description].
     */
    fun registerSameLineTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
        assertionFormatterFacade: IAssertionFormatterFacade,
        objectFormatter: IObjectFormatter,
        translator: ITranslator)

    /**
     * Creates an [IReporter] which reports only failing assertions
     * and uses the given [assertionFormatterFacade] to format assertions and messages.
     *
     * @param assertionFormatterFacade The formatter which is used to format [IAssertion]s.
     *
     * @return The newly created reporter.
     */
    fun newOnlyFailureReporter(assertionFormatterFacade: IAssertionFormatterFacade): IReporter

    /**n
     * Use the extension function [ch.tutteli.atrium.newDownCastBuilder] with reified type parameter whenever possible.
     *
     * Prepares a down cast; use [IDownCastBuilder.cast] to perform the down cast.
     *
     * Call [IDownCastBuilder.withLazyAssertions]/[IDownCastBuilder.withNullRepresentation] to specialise the down-cast.
     *
     * @param description The description of the down-cast.
     * @param commonFields The commonFields which will be used to create a [IDownCastBuilder].
     *
     * @return The newly created [IDownCastBuilder].
     *
     * @see IDownCastBuilder
     */
    fun <TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, subType: KClass<TSub>, commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>): IDownCastBuilder<T, TSub>
}

/**
 * Use this function to create a custom *assertion verb* which lazy evaluates assertions
 * (see [IAtriumFactory.newReportingPlantCheckLazily]).
 *
 * This function will create an [IAssertionPlant] which does not check the created assertions until one
 * calls [IAssertionPlant.checkAssertions].
 * However, it uses the given [createAssertions] function immediately after creating the [IAssertionPlant]
 * which might add some assertions and it then calls [IAssertionPlant.checkAssertions].
 * In case all assertions added so far hold, then it will not evaluate further added assertions until
 * one calls [IAssertionPlant.checkAssertions] again.
 *
 * It creates a [IAtriumFactory.newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
 *
 * @return The newly created [IAssertionPlant] which can be used to postulate further assertions.
 *
 * @throws AssertionError The newly created [IAssertionPlant] might throw an [AssertionError] in case a
 *         created [IAssertion] does not hold.
 */
inline fun <T : Any> IAtriumFactory.newReportingPlantCheckLazilyAtTheEnd(assertionVerb: ITranslatable, subject: T, reporter: IReporter, createAssertions: IAssertionPlant<T>.() -> Unit)
    = newReportingPlantCheckLazily(assertionVerb, subject, reporter)
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Prepares a down cast; use [IDownCastBuilder.cast] to perform the down cast.
 *
 * Call [IDownCastBuilder.withLazyAssertions]/[IDownCastBuilder.withNullRepresentation] to specialise the down-cast.
 *
 * @param description The description of the down-cast.
 * @param commonFields The commonFields which will be used to create a [IDownCastBuilder].
 *
 * @return The newly created [IDownCastBuilder].
 *
 * @see IDownCastBuilder
 */
inline fun <reified TSub : T, T : Any> IAtriumFactory.newDownCastBuilder(description: ITranslatable, commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>): IDownCastBuilder<T, TSub>
    = newDownCastBuilder(description, TSub::class, commonFields)
