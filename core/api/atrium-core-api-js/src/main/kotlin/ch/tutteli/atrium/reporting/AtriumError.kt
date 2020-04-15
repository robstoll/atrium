package ch.tutteli.atrium.reporting

actual open class AssertionFailedError(message: String) : AssertionError(message)

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
actual class AtriumError internal actual constructor(message: String) : AssertionFailedError(message) {

    actual companion object {
        /**
         * Creates an [AtriumError] and adjusts it with the given [atriumErrorAdjuster] before it is returned
         * (this might filter the `stack`).
         *
         * As side notice, `stack` is a property of Error which is currently not visible in Kotlin.
         *
         * @return The newly created [AtriumError]
         */
        actual fun create(message: String, atriumErrorAdjuster: AtriumErrorAdjuster): AtriumError =
            createAtriumError(message, atriumErrorAdjuster)
    }
}
