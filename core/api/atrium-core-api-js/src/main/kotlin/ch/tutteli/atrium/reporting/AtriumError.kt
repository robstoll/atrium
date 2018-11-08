package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.core.polyfills.stack

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its `stack` might be filtered so that reporting does not include all stack frames.
 * As side notice, `stack` is a property of Error which is currently not visible in Kotlin.
 *
 * To create such an error you need to use the [Companion.create] function.
 */
actual class AtriumError internal actual constructor(message: String) : AssertionError(message) {

    actual companion object {
        /**
         * Creates an [AtriumError] and might filter the stack.
         */
        actual fun create(message: String): AtriumError {
            val ex = AtriumError(message)
            val filteredStack = ex.stack.asSequence()
                .takeWhile { !it.contains(Regex("[\\\\|/]mocha[\\\\|/]")) }
            val prefix = "    at "
            asDynamic().stack = filteredStack.joinToString("\n$prefix", prefix)
            return ex
        }
    }
}
