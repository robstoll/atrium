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
     * Makes the assertion that [subject] is `null`.
     *
     * @return Does not support a fluent API because: what else would you want to assert about `null` anyway?
     *
     * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
     */
    override fun isNull() {
        if (subject != null) {
            addAssertion(BasicAssertion(IAssertionPlantNullable.AssertionDescription, RawString.NULL, false))
        }
    }
}
