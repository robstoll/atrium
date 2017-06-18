package ch.tutteli.atrium

import ch.tutteli.atrium.checking.FeatureAssertionChecker
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.checking.ThrowingAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*
import kotlin.reflect.KClass

/**
 * The `abstract factory` of atrium.
 *
 * It provides factory methods to create:
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [IReporter]
 * - [IAssertionFormatterFacade]
 * - [IAssertionFormatterController]
 * - [IAssertionFormatter]
 * - [ITranslator]
 * - [IObjectFormatter]
 * - [IDownCastBuilder]
 * - [ThrowableFluent]
 */
object AtriumFactory : IAtriumFactory {


    override fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckLazily(commonFields)

    override fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
        = AssertionPlantCheckImmediately(commonFields)

    override fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>
        = AssertionPlantNullable(commonFields)

    override fun newTranslator(translationSupplier: ITranslationSupplier, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator
        = Translator(translationSupplier, primaryLocale, fallbackLocales)

    override fun newDetailedObjectFormatter(translator: ITranslator): IObjectFormatter
        = DetailedObjectFormatter(translator)

    override fun newMethodCallFormatter(): IMethodCallFormatter
        = MethodCallFormatter

    override fun newAssertionFormatterFacade(assertionFormatterController: IAssertionFormatterController): IAssertionFormatterFacade
        = AssertionFormatterFacade(assertionFormatterController)

    override fun newAssertionFormatterController(): IAssertionFormatterController
        = AssertionFormatterController()

    override fun newSameLineAssertionFormatter(assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator): IAssertionFormatter
        = SameLineAssertionFormatter(assertionFormatterController, objectFormatter, translator)

    override fun newOnlyFailureReporter(assertionFormatterFacade: IAssertionFormatterFacade): IReporter
        = OnlyFailureReporter(assertionFormatterFacade)

    override fun newThrowingAssertionChecker(reporter: IReporter): IAssertionChecker
        = ThrowingAssertionChecker(reporter)

    override fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker
        = FeatureAssertionChecker(subjectPlant)

    override fun <TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, subType: KClass<TSub>, commonFields: CommonFields<T?>): IDownCastBuilder<T, TSub>
        = DownCastBuilder(description, subType, commonFields)

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
}
