package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveAtriumFromAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {

    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        stackTrace.dropWhile { isAtriumStack(it) }

    private fun isAtriumStack(it: StackTraceElement) = it.className.startsWith("ch.tutteli.atrium")
}
