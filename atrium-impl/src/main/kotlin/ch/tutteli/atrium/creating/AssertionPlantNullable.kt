package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.reporting.RawString

/**
 * An [IAssertionPlant] for nullable types.
 */
internal class AssertionPlantNullable<out T : Any?>(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : IAssertionPlantNullable<T> {

    /**
     * Checks whether the [subject] is `null` and if not uses [IAssertionPlantWithCommonFields.CommonFields.fail] to report a failing assertion
     */
    override fun isNull() {
        if (subject != null) {
            commonFields.fail(OneMessageAssertion("to be", RawString.NULL, false))
        }
    }
}
