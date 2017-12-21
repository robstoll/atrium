package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup

/**
 * An [AssertionPlant] which checks each added [Assertion] immediately.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 *
 * @constructor An [AssertionPlant] which checks each added [Assertion] immediately.
 * @param commonFields The [AssertionPlantWithCommonFields.CommonFields] of this [AssertionPlant].
 *
 * This class is not thread-safe, but is also not intended for long-running procedures.
 */
class ReportingAssertionPlantImpl<out T : Any>(
    commonFields: AssertionPlantWithCommonFields.CommonFields<T>
) : MutableListBasedReportingAssertionPlant<T, AssertionPlant<T>>(commonFields), ReportingAssertionPlant<T> {
    override val self = this

    override fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): AssertionPlant<T> {
        val plant = AtriumFactory.newCollectingPlant { subject }
        plant.assertionCreator()
        addAssertion(AssertionGroup.Builder.invisible.create(plant.getAssertions()))
        return this
    }
}
