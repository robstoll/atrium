package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.AtriumError.Companion

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its [stackTrace] might be filtered so that reporting does not include all stack frames.
 * This depends on the chosen [AtriumErrorAdjuster] - so theoretically more than the stack trace
 * could be adjusted.
 *
 * To create such an error you need to use the [AtriumError.Companion.create][Companion.create] function.
 */
actual class AtriumError internal actual constructor(message: String) : AssertionError(message) {

    actual companion object {
        /**
         * * Creates an [AtriumError] and adjusts it with the given [atriumErrorAdjuster] before it is returned
         * (adjusting might filter the [stackTrace]).
         */
        actual fun create(message: String, atriumErrorAdjuster: AtriumErrorAdjuster): AtriumError =
            createAtriumError(message, atriumErrorAdjuster)
    }
}
