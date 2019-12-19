@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual object NoOpAtriumErrorAdjuster : NoOpAtriumErrorAdjusterCommon(), AtriumErrorAdjuster {
    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> = stackTrace
}
