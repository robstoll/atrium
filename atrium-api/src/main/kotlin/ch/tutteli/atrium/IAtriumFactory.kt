@file:JvmName("IAtriumFactoryExtensions")

package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroupType
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
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
 * - [ch.tutteli.atrium.newCheckLazilyAtTheEnd]
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
     * Creates an [IAssertionPlant] which does not check the created or
     * added [IAssertion]s until one calls [IAssertionPlant.checkAssertions].
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
    fun <T : Any> newCheckLazily(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IAssertionPlant<T>
        = newCheckLazily(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlant] which does not check the created or
     * added [IAssertion]s until one calls [IAssertionPlant.checkAssertions].
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
    fun <T : Any> newCheckLazily(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckLazily(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlant] which does not check the created or
     * added [IAssertion]s until one calls [IAssertionPlant.checkAssertions].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
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
    fun <T : Any> newCheckImmediately(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IAssertionPlant<T>
        = newCheckImmediately(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
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
    fun <T : Any> newCheckImmediately(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckImmediately(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>


    /**
     * Creates an [IAssertionPlantNullable].
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
    fun <T : Any?> newNullable(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IAssertionPlantNullable<T>
        = newNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlantNullable].
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
    fun <T : Any?> newNullable(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>
        = newNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>

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
     * Creates an [IAssertionFormatter] which is intended for text output (e.g. for the console) and
     * puts assertion pairs on the same line.
     *
     * For instance, it formats the pair `a to b` as follows: `"a: b"`
     *
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [ITranslatable] such as [IBasicAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextSameLineAssertionFormatter(assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter

    /**
     * Registers all available [IAssertionFormatter]s -- which put assertion pairs on the same line and report in
     * text format (e.g. for the console) -- to the given [assertionFormatterFacade].
     *
     * @param assertionFormatterFacade The [IAssertionFormatterFacade] to which all [IAssertionFormatter]s with
     *        same line capabilities and text reporting should be registered.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [ITranslatable] such as [IBasicAssertion.description].
     */
    fun registerSameLineTextAssertionFormatterCapabilities(assertionFormatterFacade: IAssertionFormatterFacade, objectFormatter: IObjectFormatter, translator: ITranslator): Unit

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
 * (see [IAtriumFactory.newCheckLazily]).
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
inline fun <T : Any> IAtriumFactory.newCheckLazilyAtTheEnd(assertionVerb: ITranslatable, subject: T, reporter: IReporter, createAssertions: IAssertionPlant<T>.() -> Unit)
    = newCheckLazily(assertionVerb, subject, reporter)
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
