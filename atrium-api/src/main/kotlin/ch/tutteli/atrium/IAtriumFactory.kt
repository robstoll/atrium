@file:JvmName("IAtriumFactoryExtensions")
package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import java.lang.Throwable
import java.util.*
import kotlin.reflect.KClass


/**
 * The minimum contract of the `abstract factory` of atrium.
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IReporter]
 * - [IAssertionFormatter]
 * - [IObjectFormatter]
 * - [ITranslator]
 * - [IThrowableFluent]
 * - [IDownCastBuilder]
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
     * Creates an [IAssertionFormatter] which puts messages of the form 'a: b' on the same line.
     *
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion] and [Message].
     *
     * @return The newly created assertion formatter.
     */
    fun newSameLineAssertionFormatter(objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter

    /**
     * Creates an [IReporter] which reports only failing assertions
     * and uses the given [assertionFormatter] to format assertions and messages.
     *
     * @param assertionFormatter The formatter which is used to format [IAssertion]s.
     *
     * @return The newly created reporter.
     */
    fun newOnlyFailureReporter(assertionFormatter: IAssertionFormatter): IReporter

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
     * Creates an [IAssertionChecker] which creates [IFeatureAssertionGroup] instead of checking assertions
     * and delegates this task to the given [subjectPlant] by adding (see [IAssertionPlant.addAssertion]
     * the created [IFeatureAssertionGroup] to it.
     *
     * @param subjectPlant The assertion plant to which the created [IFeatureAssertionGroup]
     *        will be [added][IAssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker

    /**
     * Use the extension function with reified type parameter whenever possible.
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
