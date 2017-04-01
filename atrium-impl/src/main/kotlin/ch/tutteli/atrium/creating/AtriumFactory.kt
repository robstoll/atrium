package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.reporting.*

/**
 * An abstract factory of atrium which provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IReporter]
 * - [IAssertionMessageFormatter]
 * - [IObjectFormatter]
 * - [ThrowableFluent]
 */
object AtriumFactory {
    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, reporter: IReporter)
        = newCheckLazily(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckLazily(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckLazily(commonFields)


    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlant<T>
        = newCheckImmediately(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckImmediately(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckImmediately(commonFields)


    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    fun <T : Any?> newNullable(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlantNullable<T>
        = newNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>
        = newNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>
        = AssertionPlantNullable(commonFields)


    /**
     * Use this function to create a custom assertion verb function which lazy evaluates the assertions created by [createAssertions].
     *
     * This function will create an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     * However, it uses the given [createAssertions] function immediately after creating the [IAssertionPlant] which might add some assertions
     * and it then calls [IAssertionPlant.checkAssertions].
     * In case all assertions added so far hold, then it will not evaluate further added assertions until one calls [IAssertionPlant.checkAssertions] again.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @return The newly created [IAssertionPlant] which can be used to postulate further assertions.
     * @throws AssertionError The newly created [IAssertionPlant] throws an [AssertionError] in case a created [IAssertion] does not hold.
     */
    inline fun <T : Any> newCheckLazilyAtTheEnd(assertionVerb: String, subject: T, reporter: IReporter, createAssertions: IAssertionPlant<T>.() -> Unit)
        = createAssertionsAndCheckThem(AtriumFactory.newCheckLazily(assertionVerb, subject, reporter), createAssertions)

    /**
     * Uses the given [plant] as receiver of the given [createAssertions] function and then calls [IAssertionPlant.checkAssertions].
     * @return the given [plant]
     */
    inline fun <T : Any> createAssertionsAndCheckThem(plant: IAssertionPlant<T>, createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
        plant.createAssertions()
        plant.checkAssertions()
        return plant
    }

    /**
     * Creates a [ThrowableFluent] base on the given [assertionVerb] and the [act] function.
     *
     * It uses the given [reporter] for reporting.
     */
    fun throwableFluent(assertionVerb: String, act: () -> Unit, reporter: IReporter): ThrowableFluent
        = ThrowableFluent.create(assertionVerb, act, ThrowingAssertionChecker(reporter))

    /**
     * Creates an [IObjectFormatter] which represents objects by using their [Object.toString] representation
     * including [Class.name] and their [System.identityHashCode].
     *
     * @see DetailedObjectFormatter
     */
    fun newDetailedObjectFormatter(): IObjectFormatter = DetailedObjectFormatter()

    /**
     * Creates an [IAssertionMessageFormatter] which puts messages of the form 'a: b' on the same line.
     *
     * @see SameLineAssertionMessageFormatter
     */
    fun newSameLineAssertionMessageFormatter(objectFormatter: IObjectFormatter): IAssertionMessageFormatter
        = SameLineAssertionMessageFormatter(objectFormatter)

    /**
     * Creates an [IReporter] which reports only failing assertions
     * and uses the given [assertionMessageFormatter] to format assertions and messages.
     *
     * @see OnlyFailureReporter
     */
    fun newOnlyFailureReporter(assertionMessageFormatter: IAssertionMessageFormatter): IReporter
        = OnlyFailureReporter(assertionMessageFormatter)

    /**
     * Creates an [IAssertionChecker] which throws [AssertionError]s in case an assertion fails
     * and uses the given [reporter] for reporting.
     *
     * @see ThrowingAssertionChecker
     */
    fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = ThrowingAssertionChecker(reporter)
}

