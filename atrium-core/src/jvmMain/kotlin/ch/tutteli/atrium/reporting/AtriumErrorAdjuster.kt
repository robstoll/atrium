@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.reporting

/**
 * Responsible to adjust a given [AtriumError] for improved error reporting.
 *
 * Typically this involves filtering the [AtriumError.stackTrace] in some way or another.
 */
actual interface AtriumErrorAdjuster : AtriumErrorAdjusterCommon {

    /**
     * Adjusts the given [Throwable.stackTrace] in some way or another.
     *
     * @return The adjusted stack.
     */
    fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement>
}
