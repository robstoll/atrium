@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual object NoOpAtriumErrorAdjuster : NoOpAtriumErrorAdjusterCommon(), AtriumErrorAdjuster {
    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> = stackTrace
}
