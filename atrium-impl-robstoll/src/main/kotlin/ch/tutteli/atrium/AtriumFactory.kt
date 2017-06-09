package ch.tutteli.atrium

import ch.tutteli.atrium.checking.FeatureAssertionChecker
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.*
import java.util.*
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
object AtriumFactory : IAtriumFactory {

    override fun <T : Any> newCheckLazily(assertionVerb: ITranslatable, subject: T, reporter: IReporter)
        = newCheckLazily(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    override fun <T : Any> newCheckLazily(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckLazily(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    override fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckLazily(commonFields)

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
    inline fun <T : Any> newCheckLazilyAtTheEnd(assertionVerb: ITranslatable, subject: T, reporter: IReporter, createAssertions: IAssertionPlant<T>.() -> Unit)
        = AtriumFactory.newCheckLazily(assertionVerb, subject, reporter).createAssertionsAndCheckThem(createAssertions)


    override fun <T : Any> newCheckImmediately(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IAssertionPlant<T>
        = newCheckImmediately(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    override fun <T : Any> newCheckImmediately(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
        = newCheckImmediately(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    override fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckImmediately(commonFields)


    override fun <T : Any?> newNullable(assertionVerb: ITranslatable, subject: T, reporter: IReporter): IAssertionPlantNullable<T>
        = newNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    override fun <T : Any?> newNullable(assertionVerb: ITranslatable, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>
        = newNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

    override fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>
        = AssertionPlantNullable(commonFields)

    override fun newTranslator(translationSupplier: ITranslationSupplier, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator
        = Translator(translationSupplier, primaryLocale, fallbackLocales)

    override fun newDetailedObjectFormatter(translator: ITranslator): IObjectFormatter
        = DetailedObjectFormatter(translator)

    override fun newSameLineAssertionFormatter(objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = SameLineAssertionFormatter(objectFormatter, translator)

    override fun newOnlyFailureReporter(assertionFormatter: IAssertionFormatter): IReporter
        = OnlyFailureReporter(assertionFormatter)

    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = ThrowingAssertionChecker(reporter)

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = FeatureAssertionChecker(subjectPlant)

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
    fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, reporter: IReporter): ThrowableFluent
        = newThrowableFluent(assertionVerb, act, newThrowingAssertionChecker(reporter))

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
    fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent
        = ThrowableFluent.create(assertionVerb, act, assertionChecker)

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
    inline fun <reified TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, commonFields: CommonFields<T?>): DownCastBuilder<T, TSub>
        = newDownCastBuilder(description, TSub::class, commonFields)

    /**
     * Use the overload with reified type parameter whenever possible.
     *
     * Prepares a down cast; use [DownCastBuilder.cast] to perform the down cast.
     *
     * Call [DownCastBuilder.withLazyAssertions]/[DownCastBuilder.withNullRepresentation] to specialise the down-cast.
     *
     * @param description The description of the down-cast.
     * @param subType The resulting type of the down-cast.
     * @param commonFields The commonFields which will be used to create a [DownCastBuilder].
     *
     * @return The newly created [DownCastBuilder].
     *
     * @see DownCastBuilder
     */
    fun <TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, subType: KClass<TSub>, commonFields: CommonFields<T?>): DownCastBuilder<T, TSub>
        = DownCastBuilder(description, subType, commonFields)
}
