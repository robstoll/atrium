package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * An [IAssertionPlant] which does not check each added [IAssertion] immediately but only if
 * [checkAssertions] is called and does not fail fast.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 *
 * @constructor An [IAssertionPlant] which does not check each added [IAssertion] immediately but only if
 *              [checkAssertions] is called and does not fail fast.
 * @param commonFields The [IAssertionPlantWithCommonFields.CommonFields] of this [IAssertionPlant].
 *
 * This class is not thread-safe, but is also not intended for long-running procedures.
 */
class AssertionPlantCheckLazily<out T : Any>(
    commonFields: IAssertionPlantWithCommonFields.CommonFields<T>
) : BaseReportingAssertionPlant<T, IAssertionPlant<T>>(commonFields), IReportingAssertionPlant<T> {
    override val self = this

    override fun addAssertionsCreatedBy(createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
        this.createAssertions()
        checkAssertions()
        return this
    }
}
