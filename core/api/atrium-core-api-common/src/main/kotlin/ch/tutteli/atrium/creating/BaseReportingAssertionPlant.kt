@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
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
@Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
interface BaseReportingAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>>
    : BaseAssertionPlant<T, A>, AssertionPlantWithCommonFields<T>
