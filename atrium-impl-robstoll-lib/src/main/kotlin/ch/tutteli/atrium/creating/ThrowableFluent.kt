package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IThrowableFluent.AssertionDescription.IS_A
import ch.tutteli.atrium.creating.IThrowableFluent.AssertionDescription.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KClass

/**
 * Provides [toThrow] methods for making assertions about a [Throwable]
 * which one expects was thrown.
 *
 * @property commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] is used for failure reporting etc.
 *
 * @constructor Use [AtriumFactory.newThrowableFluent] to create an instance.
 * @param commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] could be used for failure reporting etc.
 */
class ThrowableFluent internal constructor(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>
) : IThrowableFluent {

    private constructor(assertionVerb: ITranslatable, throwable: Throwable?, assertionChecker: IAssertionChecker)
        : this(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, throwable, assertionChecker))

    /**
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is `null` or another type
     * than the expected one.
     *
     * @return This builder to support a fluent-style API.
     *
     * @throws AssertionError In case the made assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    inline fun <reified TExpected : Throwable> toThrow(): IAssertionPlant<TExpected>
        = toThrow(TExpected::class)

    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>): IAssertionPlant<TExpected>
        = AtriumFactory.newDownCastBuilder(IS_A, expectedType, commonFields)
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .cast()

    /**
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is null or another type
     * than the expected one -- furthermore it [createAssertions] which are checked additionally as well.
     *
     * @return This builder to support a fluent-style API.
     *
     * @throws AssertionError In case the made assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    inline fun <reified TExpected : Throwable> toThrow(noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
        = toThrow(TExpected::class, createAssertions)

    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
        = AtriumFactory.newDownCastBuilder(IS_A, expectedType, commonFields)
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .withLazyAssertions(createAssertions)
        .cast()

    companion object {
        /**
         * Creates a [ThrowableFluent] where executing [act] will determine the
         * [subject](IAssertionPlantWithCommonFields.CommonFields.subject) of [commonFields].
         *
         * @return The newly created [ThrowableFluent].
         */
        fun create(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
            var throwable: Throwable? = null
            try {
                act()
            } catch(t: Throwable) {
                throwable = t
            }
            return ThrowableFluent(assertionVerb, throwable, assertionChecker)
        }
    }
}
