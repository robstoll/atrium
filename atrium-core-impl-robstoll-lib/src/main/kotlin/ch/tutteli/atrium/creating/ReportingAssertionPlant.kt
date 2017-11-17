package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.InvisibleAssertionGroup

/**
 * An [IAssertionPlant] which checks each added [IAssertion] immediately.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 *
 * @constructor An [IAssertionPlant] which checks each added [IAssertion] immediately.
 * @param commonFields The [IAssertionPlantWithCommonFields.CommonFields] of this [IAssertionPlant].
 *
 * This class is not thread-safe, but is also not intended for long-running procedures.
 */
class ReportingAssertionPlant<out T : Any>(
    commonFields: IAssertionPlantWithCommonFields.CommonFields<T>
) : BaseReportingAssertionPlant<T, IAssertionPlant<T>>(commonFields), IReportingAssertionPlant<T> {
    override val self = this

    override fun addAssertionsCreatedBy(assertionCreator: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
        val plant = AtriumFactory.newCollectingPlant { subject }
        plant.assertionCreator()
        addAssertion(InvisibleAssertionGroup(plant.getAssertions()))
        return this
    }
}
