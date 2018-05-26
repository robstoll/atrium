package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.ReportingAssertionPlant

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
) : MutableListBasedReportingAssertionPlant<T, AssertionPlant<T>>(commonFields),
    ReportingAssertionPlant<T> {

    override val self = this

    override fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): AssertionPlant<T> {
        val assertions = coreFactory.newCollectingPlant({ subject })
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        return addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())
    }
}
