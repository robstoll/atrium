package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.reporting.RawString

internal class AssertionPlantNullable<out T : Any?> constructor(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : IAssertionPlantNullable<T> {

    override fun isNull() {
        if (subject != null) {
            commonFields.fail(OneMessageAssertion("to be", RawString.NULL, false))
        }
    }
}
