@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable

/**
 * An [AssertionPlant] for nullable types.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 *
 * @constructor An [AssertionPlant] for nullable types.
 * @param commonFields The [AssertionPlantWithCommonFields.CommonFields] of this [AssertionPlant].
 */
class ReportingAssertionPlantNullableImpl<out T : Any?>(
    commonFields: AssertionPlantWithCommonFields.CommonFields<T>
) : MutableListBasedReportingAssertionPlant<T, AssertionPlantNullable<T>>(commonFields),
    ReportingAssertionPlantNullable<T> {
    override val self = this
}
