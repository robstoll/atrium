package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * An [IAssertionPlant] which checks each added [IAssertion] immediately.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 *
 * @constructor
 * @param commonFields The [IAssertionPlantWithCommonFields.CommonFields] of this [IAssertionPlant].
 *
 * This class is not thread-safe, but is also not intended for long-running procedures.
 */
class AssertionPlantCheckImmediately<out T : Any>(
    commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : AssertionPlantCheckLazily<T>(commonFields) {

    override fun addAssertion(assertion: IAssertion): IAssertionPlant<T> {
        super.addAssertion(assertion)
        checkAssertions()
        return this
    }
}
