package ch.tutteli.atrium.creating

@Suppress("UNUSED_PARAMETER", "unused")
class ThrowableFluent
/**
 * Might be that the actual implementation does not have a private constructor.
 * We hide it here for information hiding purposes, use the [AtriumFactory] instead.
 */
private constructor(val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>) {

    /**
     * Makes an assertion about the [commonFields]'s [IAssertionPlantWithCommonFields.CommonFields.subject] that it
     * is of the expected type [TExpected] and reports an error if subject is null or another type.
     *
     * @throws AssertionError might throw an [AssertionError] if the assertion fails.
     */
    inline fun <reified TExpected : Throwable> toThrow(): IAssertionPlant<TExpected> {
        throw UnsupportedOperationException("The atrium-api-late-binding should only be used as a compileOnly dependency, " +
            "meaning as a substitute for a real implementation")
    }

    /**
     * Makes an assertion about the [commonFields]'s [IAssertionPlantWithCommonFields.CommonFields.subject] that it
     * is of the expected type [TExpected] and reports an error if subject is null or another type  -- furthermore it [createAssertions] which are checked additionally as well.
     *
     * @throws AssertionError might throw an [AssertionError] if an assertion fails.
     */
    inline fun <reified TExpected : Throwable> toThrow(noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected> {
        throw UnsupportedOperationException("The atrium-api-late-binding should only be used as a compileOnly dependency, " +
            "meaning as a substitute for a real implementation")
    }
}
