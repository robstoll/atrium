package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion

/**
 * An [IAssertionPlant] which checks each added [IAssertion] immediately.
 *
 * This class is not thread-safe, but is also not intended for long-running procedures.
 */
internal class AssertionPlantCheckImmediately<out T : Any> constructor(
    commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : AssertionPlantCheckLazily<T>(commonFields) {

    override fun addAssertion(assertion: IAssertion): IAssertionPlant<T> {
        super.addAssertion(assertion)
        checkAssertions()
        return this
    }
}
