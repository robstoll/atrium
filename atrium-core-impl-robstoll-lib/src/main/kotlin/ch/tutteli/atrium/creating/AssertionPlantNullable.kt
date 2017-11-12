package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.RawString

/**
 * An [IAssertionPlant] for nullable types.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 *
 * @constructor An [IAssertionPlant] for nullable types.
 * @param commonFields The [IAssertionPlantWithCommonFields.CommonFields] of this [IAssertionPlant].
 */
class AssertionPlantNullable<out T : Any?>(
    commonFields: IAssertionPlantWithCommonFields.CommonFields<T>
) : BaseReportingAssertionPlant<T, IAssertionPlantNullable<T>>(commonFields), IReportingAssertionPlantNullable<T> {

    override val self = this

    override fun addAssertion(assertion: IAssertion): IAssertionPlantNullable<T> {
        super.addAssertion(assertion)
        checkAssertions()
        return this
    }

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
