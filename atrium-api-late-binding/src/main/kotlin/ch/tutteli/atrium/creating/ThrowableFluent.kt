package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.ErrorMsg
import ch.tutteli.atrium.IAtriumFactory
import kotlin.reflect.KClass

/**
 * Provides [toThrow] methods for making assertions about a [Throwable]
 * which one expects was thrown.
 *
 * The reason why this class does not implement an interface lies in the fact that interfaces cannot have inline
 * functions (at least so far) and we want to provide [toThrow] with the reified type parameter as first suggestion
 * in Intellij's auto-completion.
 * That is also the reason why we did not define the inline functions as extension functions of
 * [IThrowableFluent] (in contrast to [AtriumFactory] where we moved inline functions to [IAtriumFactory],
 * for instance, [IAtriumFactory.newCheckLazilyAtTheEnd] is such a case).
 *
 * @property commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] could be used for failure reporting etc.
 *
 * @constructor Might be that the actual implementation does not have a private constructor.
 *              We hide it here for information hiding purposes; always use
 *              [AtriumFactory.newThrowableFluent] instead to create an instance.
 *
 * @param commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] could be used for failure reporting etc.
 */
class ThrowableFluent
private constructor(override val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>) : IThrowableFluent {

    /**
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is `null` or another type
     * than the expected one.
     *
     * @return This builder to support a fluent-style API.
     *
     * @throws AssertionError Might throw an [AssertionError] if the assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    inline fun <reified TExpected : Throwable> toThrow(): IAssertionPlant<TExpected>
        = toThrow(TExpected::class)


    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>): IAssertionPlant<TExpected> {
        throw UnsupportedOperationException(ErrorMsg.ERROR_MSG)
    }


    /**
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is null or another type
     * than the expected one -- furthermore it [createAssertions] which are checked additionally as well.
     *
     * @return This builder to support a fluent-style API.
     *
     * @throws AssertionError Might throw an [AssertionError] if an assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    inline fun <reified TExpected : Throwable> toThrow(noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
        = toThrow(TExpected::class, createAssertions)

    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected> {
        throw UnsupportedOperationException(ErrorMsg.ERROR_MSG)
    }

}
