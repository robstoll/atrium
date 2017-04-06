package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.reporting.IAssertionMessageFormatter
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.IReporter

interface IAtriumFactory {
    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlant<T>

    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>

    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>


    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlant<T>

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>


    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    fun <T : Any?> newNullable(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlantNullable<T>

    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>

    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>


    /**
     * Creates an [IObjectFormatter] which represents objects by using their [Object.toString] representation
     * including [Class.name] and their [System.identityHashCode].
     */
    fun newDetailedObjectFormatter(): IObjectFormatter

    /**
     * Creates an [IAssertionMessageFormatter] which puts messages of the form 'a: b' on the same line.
     */
    fun newSameLineAssertionMessageFormatter(objectFormatter: IObjectFormatter): IAssertionMessageFormatter

    /**
     * Creates an [IReporter] which reports only failing assertions
     * and uses the given [assertionMessageFormatter] to format assertions and messages.
     */
    fun newOnlyFailureReporter(assertionMessageFormatter: IAssertionMessageFormatter): IReporter

    /**
     * Creates an [IAssertionChecker] which throws [AssertionError]s in case an assertion fails
     * and uses the given [reporter] for reporting.
     */
    fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker

    /**
     * Creates an [IAssertionChecker] which creates [IFeatureAssertionGroup] instead of checking assertions
     * and delegates this task to the given [subjectPlant] by adding (see [IAssertionPlant.addAssertion]
     * the created [IFeatureAssertionGroup] to it.
     */
    fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
}
