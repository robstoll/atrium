package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.AtriumError.Companion

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its stack trace (`stackTrace` in JVM, `stack` in JS) might be filtered so that reporting does not include
 * all stack frames.
 *
 * To create such an error you need to use the [Companion.create] function.
 */
expect class AtriumError internal constructor(message: String) : AssertionError {

    companion object {
        fun create(message: String): AtriumError
    }
}
