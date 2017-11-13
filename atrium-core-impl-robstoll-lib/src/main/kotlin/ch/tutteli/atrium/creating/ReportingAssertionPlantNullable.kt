package ch.tutteli.atrium.creating

/**
 * An [IAssertionPlant] for nullable types.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 *
 * @constructor An [IAssertionPlant] for nullable types.
 * @param commonFields The [IAssertionPlantWithCommonFields.CommonFields] of this [IAssertionPlant].
 */
class ReportingAssertionPlantNullable<out T : Any?>(
    commonFields: IAssertionPlantWithCommonFields.CommonFields<T>
) : BaseReportingAssertionPlant<T, IAssertionPlantNullable<T>>(commonFields), IReportingAssertionPlantNullable<T> {
    override val self = this
}
