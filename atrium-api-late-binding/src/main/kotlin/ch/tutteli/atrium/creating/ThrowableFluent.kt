package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory

/**
 * Provides [toThrow] methods for making assertions about a [Throwable]
 * which one expects was thrown.
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
@Suppress("UNUSED_PARAMETER", "unused")
class ThrowableFluent
private constructor(val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>) {

    /**
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is `null` or another type
     * than the expected one.
     *
     * @return This builder to support a fluent-style API.
     *
     * @throws AssertionError Might throw an [AssertionError] if the assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw an [Exception].
     */
    inline fun <reified TExpected : Throwable> toThrow(): IAssertionPlant<TExpected> {
        throw UnsupportedOperationException("The atrium-api-late-binding should only be used as a compileOnly dependency, " +
            "meaning as a substitute for a real implementation")
    }

    /**
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject] that it
     * is of the expected type [TExpected] and reports an error if subject is null or another type
     * than the expected one -- furthermore it [createAssertions] which are checked additionally as well.
     *
     * @return This builder to support a fluent-style API.
     *
     * @throws AssertionError Might throw an [AssertionError] if an assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    inline fun <reified TExpected : Throwable> toThrow(noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected> {
        throw UnsupportedOperationException("The atrium-api-late-binding should only be used as a compileOnly dependency, " +
            "meaning as a substitute for a real implementation")
    }
}
