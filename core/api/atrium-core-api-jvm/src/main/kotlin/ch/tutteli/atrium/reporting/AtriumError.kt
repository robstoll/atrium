package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.AtriumError.Companion

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its [stackTrace] might be filtered so that reporting does not include all stack frames.
 *
 * To create such an error you need to use the [Companion.create] function.
 */
actual class AtriumError internal actual constructor(message: String) : AssertionError(message) {

    actual companion object {
        /**
         * Creates an [AtriumError] and might filter the [stackTrace].
         */
        actual fun create(message: String): AtriumError {
            val ex = AtriumError(message)
            val filteredStackTrace = ex.stackTrace.asSequence()
                .takeWhile {
                    !it.className.startsWith("org.junit") && !it.className.startsWith("org.jetbrains.spek")
                }
            ex.stackTrace = filteredStackTrace.toList().toTypedArray()
            return ex
        }
    }
}
