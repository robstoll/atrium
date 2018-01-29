package ch.tutteli.atrium.creating

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
) : MutableListBasedReportingAssertionPlant<T, AssertionPlantNullable<T>>(commonFields), ReportingAssertionPlantNullable<T> {
    override val self = this
}
