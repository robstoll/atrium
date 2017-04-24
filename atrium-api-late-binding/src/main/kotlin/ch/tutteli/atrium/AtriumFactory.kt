package ch.tutteli.atrium

import ch.tutteli.atrium.ErrorMsg.ERROR_MSG
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslator
import kotlin.reflect.KClass

/**
 * The `abstract factory` of atrium.
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IReporter]
 * - [IAssertionFormatter]
 * - [IObjectFormatter]
 * - [ThrowableFluent]
 * - [DownCastBuilder]
 */
@Suppress("UNUSED_PARAMETER")
object AtriumFactory : IAtriumFactory {

    override fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, reporter: IReporter)
        = newCheckLazily(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    override fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckLazily(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    override fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T> {
        throw UnsupportedOperationException(ERROR_MSG)
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
    inline fun <T : Any> newCheckLazilyAtTheEnd(assertionVerb: String, subject: T, reporter: IReporter, createAssertions: IAssertionPlant<T>.() -> Unit)
        = AtriumFactory.newCheckLazily(assertionVerb, subject, reporter).createAssertionsAndCheckThem(createAssertions)

    override fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlant<T>
        = newCheckImmediately(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    override fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckImmediately(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    override fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun <T : Any?> newNullable(assertionVerb: String, subject: T, reporter: IReporter): IAssertionPlantNullable<T>
        = newNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    override fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>
        = newNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    override fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun newTranslator(): ITranslator {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun newDetailedObjectFormatter(translator: ITranslator): IObjectFormatter {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun newSameLineAssertionFormatter(objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun newOnlyFailureReporter(assertionFormatter: IAssertionFormatter): IReporter {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Creates a [ThrowableFluent] based on the given [assertionVerb] and the [act] function.
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
     * @return The newly created [ThrowableFluent].
     *
     * @see ThrowableFluent
     */
    fun newThrowableFluent(assertionVerb: String, act: () -> Unit, reporter: IReporter): ThrowableFluent {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Creates a [ThrowableFluent] based on the given [assertionVerb] and the [act] function.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param act The function which is expected to throw a [Throwable] which in turn will be used as subject
     *        for postulated [IAssertion]s (see [ThrowableFluent] and
     *        [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker Used to report failures (see [IAssertionChecker.fail]
     *        and [IAssertionPlantWithCommonFields.CommonFields.assertionChecker])).
     *
     * @return The newly created [ThrowableFluent].
     *
     * @see ThrowableFluent
     */
    fun newThrowableFluent(assertionVerb: String, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Prepares a down cast; use [DownCastBuilder.cast] to perform the down cast.
     *
     * Call [DownCastBuilder.withLazyAssertions]/[DownCastBuilder.withNullRepresentation] to specialise the down-cast.
     *
     * @param description The description of the down-cast.
     * @param commonFields The commonFields which will be used to create a [DownCastBuilder].
     *
     * @return The newly created [DownCastBuilder].
     *
     * @see DownCastBuilder
     */
    inline fun <reified TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>): DownCastBuilder<T, TSub>
        = newDownCastBuilder(description, TSub::class, commonFields)

    /**
     * Use the overload with reified type parameter whenever possible.
     *
     * Prepares a down cast; use [DownCastBuilder.cast] to perform the down cast.
     *
     * Call [DownCastBuilder.withLazyAssertions]/[DownCastBuilder.withNullRepresentation] to specialise the down-cast.
     *
     * @param description The description of the down-cast.
     * @param commonFields The commonFields which will be used to create a [DownCastBuilder].
     *
     * @return The newly created [DownCastBuilder].
     *
     * @see DownCastBuilder
     */
    fun <TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, clazz: KClass<TSub>, commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>): DownCastBuilder<T, TSub>
        = DownCastBuilder(description, clazz, commonFields)

}
