package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.reporting.RawString

/**
 * An [IAssertionPlant] for nullable types.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
internal class AssertionPlantNullable<out T : Any?>(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : IAssertionPlantNullable<T> {

     /**
     * Checks whether [subject] is `null` and uses [commonFields]' method
     * [fail][IAssertionPlantWithCommonFields.CommonFields.fail] to report an error if not.
     *
     * @throws AssertionError In case [subject] is not `null`.
     *
     * @see IAssertionPlantWithCommonFields.CommonFields.fail
     */
    override fun isNull() {
        if (subject != null) {
            commonFields.fail(BasicAssertion(IAssertionPlantNullable.AssertionDescription, RawString.NULL, false))
        }
    }
}
