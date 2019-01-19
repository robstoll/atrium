package ch.tutteli.atrium.reporting

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
expect class AtriumError internal constructor(message: String) : AssertionError {

    companion object {
        fun create(message: String, errorAdjuster: AtriumErrorAdjuster): AtriumError
    }
}

internal fun createAtriumError(message: String, errorAdjuster: AtriumErrorAdjuster): AtriumError {
    val err = AtriumError(message)
    errorAdjuster.adjust(err)
    return err
}
