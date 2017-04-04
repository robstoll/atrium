package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.RawString

/**
 * A builder for down-casting assertions.
 */
@Suppress("UNUSED_PARAMETER", "unused")
class DownCastBuilder<out T : Any, out TSub : T>(private val subClass: Class<TSub>,
                                                 private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>,
                                                 private val assertion: IAssertion) {
    init {
        throw UnsupportedOperationException(ErrorMsg.ERROR_MSG)
    }

    /**
     * Use this method if you want to use your own `null` representation (default is [RawString.NULL])
     */
    fun withNullRepresentation(representation: String): DownCastBuilder<T, TSub> {
        throw UnsupportedOperationException(ErrorMsg.ERROR_MSG)
    }

    /**
     * Use this method if you want to add several assertions which are checked lazily after the down cast is performed.
     */
    fun withLazyAssertions(assertions: IAssertionPlant<TSub>.() -> Unit): DownCastBuilder<T, TSub> {
        throw UnsupportedOperationException(ErrorMsg.ERROR_MSG)
    }

    /**
     * Performs the down-cast
     *
     * @throws AssertionError in case the down-cast fails
     * @throws IllegalStateException in case reporting a failure does not throw an [Exception]
     */
    fun cast(): IAssertionPlant<TSub> {
        throw UnsupportedOperationException(ErrorMsg.ERROR_MSG)
    }
}
