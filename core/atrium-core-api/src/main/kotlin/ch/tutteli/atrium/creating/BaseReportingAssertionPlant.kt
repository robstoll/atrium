package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a plant for [Assertion]s and offers methods to [addAssertion]s to this plant.
 *
 * It defines what [ReportingAssertionPlant] and [ReportingAssertionPlantNullable] have in common.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 * @param A A subtype of [BaseAssertionPlant] which is used in the fluent style API.
 */
interface BaseReportingAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>>
    : BaseAssertionPlant<T, A>, AssertionPlantWithCommonFields<T> {
    /**
     * The subject for which this plant will create, check and report [Assertion]s.
     */
    override val subject get() = commonFields.subject
}
