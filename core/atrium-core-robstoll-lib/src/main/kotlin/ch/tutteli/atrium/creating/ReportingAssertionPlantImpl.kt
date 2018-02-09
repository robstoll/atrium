package ch.tutteli.atrium.creating

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup

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
        val assertions = CoreFactory.newCollectingPlant({ subject })
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        return addAssertion(AssertionBuilder.invisibleGroup.create(assertions))
    }
}
