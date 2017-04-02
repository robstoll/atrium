package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.checking.FeatureAssertionChecker
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
object AtriumFactory : IAtriumFactory {
    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    override fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, reporter: IReporter)
        = newCheckLazily(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    override fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckLazily(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    override fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckLazily(commonFields)


    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    override fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlant<T>
        = newCheckImmediately(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    override fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckImmediately(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlant] which immediately checks added [IAssertion]s.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    override fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckImmediately(commonFields)


    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     */
    override fun <T : Any?> newNullable(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlantNullable<T>
        = newNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It uses the given [assertionChecker] for assertion checking.
     */
    override fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>
        = newNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    /**
     * Creates an [IAssertionPlantNullable].
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for assertion checking.
     */
    override fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>
        = AssertionPlantNullable(commonFields)


    /**
     * Creates an [IObjectFormatter] which represents objects by using their [Object.toString] representation
     * including [Class.name] and their [System.identityHashCode].
     *
     * @see DetailedObjectFormatter
     */
    override fun newDetailedObjectFormatter(): IObjectFormatter = DetailedObjectFormatter()

    /**
     * Creates an [IAssertionMessageFormatter] which puts messages of the form 'a: b' on the same line.
     *
     * @see SameLineAssertionMessageFormatter
     */
    override fun newSameLineAssertionMessageFormatter(objectFormatter: IObjectFormatter): IAssertionMessageFormatter
        = SameLineAssertionMessageFormatter(objectFormatter)

    /**
     * Creates an [IReporter] which reports only failing assertions
     * and uses the given [assertionMessageFormatter] to format assertions and messages.
     *
     * @see OnlyFailureReporter
     */
    override fun newOnlyFailureReporter(assertionMessageFormatter: IAssertionMessageFormatter): IReporter
        = OnlyFailureReporter(assertionMessageFormatter)

    /**
     * Creates an [IAssertionChecker] which throws [AssertionError]s in case an assertion fails
     * and uses the given [reporter] for reporting.
     *
     * @see ThrowingAssertionChecker
     */
    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = ThrowingAssertionChecker(reporter)

    /**
     * Creates an [IAssertionChecker] which creates [IFeatureAssertionGroup] instead of checking assertions
     * and delegates this task to the given [subjectPlant] by adding (see [IAssertionPlant.addAssertion]
     * the created [IFeatureAssertionGroup] to it.
     *
     * @see FeatureAssertionChecker
     */
    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    /**
     * Creates a [ThrowableFluent] based on the given [assertionVerb] and the [act] function.
     *
     * It uses the given [reporter] for reporting.
     */
    fun newThrowableFluent(assertionVerb: String, act: () -> Unit, reporter: IReporter): ThrowableFluent
        = ThrowableFluent.create(assertionVerb, act, ThrowingAssertionChecker(reporter))

    /**
     * Prepares a down cast; use [DownCastBuilder.cast] to perform it and first call
     * [DownCastBuilder.withLazyAssertions]/[DownCastBuilder.withNullRepresentation] to specialise it further.
     */
    inline fun <reified TSub : T, T : Any> newDownCastBuilder(commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>, assertion: IAssertion): DownCastBuilder<T, TSub>
        = DownCastBuilder(TSub::class.java, commonFields, assertion)
}

