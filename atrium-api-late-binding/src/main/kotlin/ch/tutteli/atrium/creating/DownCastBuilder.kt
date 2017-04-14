package ch.tutteli.atrium.creating

import ch.tutteli.atrium.ErrorMsg.ERROR_MSG
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.RawString
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * A builder for creating a down-casting [IAssertion].
 *
 * Or in other words, helps to make an assertion about [IAssertionPlant.subject] that it can be
 * down-casted to [subType].
 *
 * @property description The description of this down-cast; will be used for the creation of the [IAssertion].
 * @property subType The resulting type of the down-cast.
 * @property commonFields The [IAssertionPlantWithCommonFields.CommonFields] of the
 *        [IAssertionPlant]/[IAssertionPlantNullable] which uses this [DownCastBuilder].
 *        The down-cast will be performed on its [subject][IAssertionPlant.subject].
 *        Moreover, the containing information will inter alia be used in reporting.
 *
 * @constructor
 * @param description The description of this down-cast; will be used for the creation of the [IAssertion].
 * @param subType The resulting type of the down-cast.
 * @param commonFields The [IAssertionPlantWithCommonFields.CommonFields] of the
 *        [IAssertionPlant]/[IAssertionPlantNullable] which uses this [DownCastBuilder].
 *        The down-cast will be performed on its [subject][IAssertionPlant.subject].
 *        Moreover, the containing information will inter alia be used in reporting.
 *
 */
@Suppress("UNUSED_PARAMETER", "unused")
class DownCastBuilder<out T : Any, out TSub : T>(private val description: String,
                                                 private val subType: KClass<TSub>,
                                                 private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>) {
    init {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Use this method if you want to use your own `null` representation in error reporting
     * (default is [RawString.NULL]).
     *
     * @return This builder to support a fluent-style API.
     */
    fun withNullRepresentation(representation: String): DownCastBuilder<T, TSub> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Use this method if you want to add several assertions which are checked lazily after the down cast is performed.
     *
     * Or in other words, the given [createAssertions] function will be called,
     * which might add additional [IAssertion]s to the newly created [IAssertionPlant], which are then lazily checked
     * in [cast] after the down-cast was performed and before the newly created [IAssertionPlant] is returned.
     *
     * @return This builder to support a fluent-style API.
     */
    fun withLazyAssertions(createAssertions: IAssertionPlant<TSub>.() -> Unit): DownCastBuilder<T, TSub> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Performs the down-cast if possible; reports a failure otherwise.
     *
     * Down-Casting is possible if [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * is not null and its type is [subType] (or a sub-type of [subType]).
     *
     * In case the down-cast can be performed, it will create an [IAssertionPlant] and use the down-casted
     * [subject][IAssertionPlantWithCommonFields.CommonFields.subject] of [commonFields] as [IAssertionPlant.subject]
     * of the newly created [IAssertionPlant]. Furthermore, it will add an [IAssertion] -- representing the
     * performed down-cast (possibly using the [description]) -- to the newly created [IAssertionPlant].
     *
     * @return The newly created [IAssertionPlant] for the down-casted
     *         [subject][IAssertionPlantWithCommonFields.CommonFields.subject] of [commonFields].
     *
     * @throws AssertionError Might throw an [AssertionError] in case the down-cast cannot be performed
     *         or an additionally specified assertion (using [withLazyAssertions]) does not hold.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    fun cast(): IAssertionPlant<TSub> {
        throw UnsupportedOperationException(ERROR_MSG)
    }
}
