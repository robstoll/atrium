package ch.tutteli.atrium.reporting

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its `stack` might be filtered so that reporting does not include all stack frames.
 * This depends on the chosen [AtriumErrorAdjuster] - so theoretically more than the stack trace
 * could be adjusted.
 *
 * As side notice, `stack` is a property of Error which is currently not visible in Kotlin.
 *
 * To create such an error you need to use the [AtriumError.Companion.create][Companion.create] function.
 */
actual class AtriumError internal actual constructor(message: String) : AssertionError(message) {

    actual companion object {
        /**
         * Creates an [AtriumError] and might filter the stack.
         */
        actual fun create(message: String, errorAdjuster: AtriumErrorAdjuster): AtriumError
            = createAtriumError(message, errorAdjuster)
    }
}
