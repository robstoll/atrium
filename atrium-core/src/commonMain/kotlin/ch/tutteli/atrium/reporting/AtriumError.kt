package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.AtriumError.Companion

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its stack trace (`stackTrace` in JVM, `stack` in JS) might be filtered so that reporting does not include
 * all stack frames. This depends on the chosen [AtriumErrorAdjuster] - so theoretically more than the stack trace
 * could be adjusted or nothing at all.
 *
 * To create such an error you need to use the [Companion.create] function.
 */
expect class AtriumError internal constructor(message: String, rootAssertion: Assertion) : AssertionError {

    //TODO 1.3.0 replace with rootProof
    /**
     * Represents the [Assertion] which lead to this AtriumError.
     *
     * @since 1.3.0
     */
    val rootAssertion: Assertion

    companion object {
        /**
         * Creates an [AtriumError] and adjusts it with the given [atriumErrorAdjuster] before it is returned.
         * @return The newly created [AtriumError]
         */
        fun create(
            message: String,
            rootAssertion: Assertion,
            atriumErrorAdjuster: AtriumErrorAdjuster,
        ): AtriumError
    }
}

internal fun createAtriumError(message: String, rootAssertion: Assertion, errorAdjuster: AtriumErrorAdjuster): AtriumError =
    AtriumError(message, rootAssertion).also {
        errorAdjuster.adjust(it)
    }
