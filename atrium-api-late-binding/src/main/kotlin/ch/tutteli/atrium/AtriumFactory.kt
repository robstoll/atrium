package ch.tutteli.atrium

import ch.tutteli.atrium.ErrorMsg.ERROR_MSG
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.IReporter
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
 * - [ITranslator]
 * - [IDownCastBuilder]
 * - [ThrowableFluent]
 */
@Suppress("UNUSED_PARAMETER")
object AtriumFactory : IAtriumFactory {

    override fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    override fun newTranslator(translationSupplier: ITranslationSupplier, primaryLocale: Locale, vararg fallbackLocales: Locale): ITranslator {
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

    override fun <TSub : T, T : Any> newDownCastBuilder(description: ITranslatable, subType: KClass<TSub>, commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>): IDownCastBuilder<T, TSub> {
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
    fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, reporter: IReporter): ThrowableFluent {
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
    fun newThrowableFluent(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
        throw UnsupportedOperationException(ERROR_MSG)
    }

}
