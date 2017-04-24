package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ITranslator

/**
 * The minimum contract of the `abstract factory` of atrium.
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IReporter]
 * - [IAssertionFormatter]
 * - [IObjectFormatter]
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
    fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlant<T>

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
    fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>

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
    fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlant<T>

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
    fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>

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
    fun <T : Any?> newNullable(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlantNullable<T>

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
    fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>

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
     * Creates an [ITranslator].
     *
     * @return The newly created translator.
     */
    fun newTranslator(): ITranslator

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
}
