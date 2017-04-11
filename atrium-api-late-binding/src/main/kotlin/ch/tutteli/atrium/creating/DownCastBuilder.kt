package ch.tutteli.atrium.creating

import ch.tutteli.atrium.ErrorMsg.ERROR_MSG
import ch.tutteli.atrium.reporting.RawString
import kotlin.reflect.KClass

/**
 * A builder for down-casting assertions.
 */
@Suppress("UNUSED_PARAMETER", "unused")
class DownCastBuilder<out T : Any, out TSub : T>(private val description: String,
                                                 private val subClass: KClass<TSub>,
                                                 private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>) {
    init {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Use this method if you want to use your own `null` representation (default is [RawString.NULL]).
     */
    fun withNullRepresentation(representation: String): DownCastBuilder<T, TSub> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Use this method if you want to add several assertions which are checked lazily after the down cast is performed.
     */
    fun withLazyAssertions(assertions: IAssertionPlant<TSub>.() -> Unit): DownCastBuilder<T, TSub> {
        throw UnsupportedOperationException(ERROR_MSG)
    }

    /**
     * Performs the down-cast if possible; reports a failure otherwise.
     *
     * Down-Casting is possible if [commonFields]'s [IAssertionPlantWithCommonFields.CommonFields.subject]
     * is not null and its type is [subClass] (or a sub-class).
     *
     * @throws AssertionError in case the down-cast cannot be performed
     *         or an additionally specified assertion (using [withLazyAssertions]) does not hold.
     * @throws IllegalStateException in case reporting a failure does not throw an [Exception].
     */
    fun cast(): IAssertionPlant<TSub> {
        throw UnsupportedOperationException(ERROR_MSG)
    }
}
