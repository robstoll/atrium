package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.reporting.RawString

internal class AssertionFactoryNullable<out T : Any?> constructor(
    override val commonFields: IAssertionFactoryBase.CommonFields<T>) : IAssertionFactoryNullable<T> {

    override fun isNull() {
        if (subject != null) {
            commonFields.fail(OneMessageAssertion("to be", RawString.NULL, false))
        }
    }
}
