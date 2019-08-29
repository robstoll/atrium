package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveRunnerAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {

    override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        stackTrace.takeWhile {
            !it.className.startsWith("org.junit") &&
                !it.className.startsWith("org.jetbrains.spek") &&
                !it.className.startsWith("org.spekframework.spek2") &&
                !it.className.startsWith("io.kotlintest")
        }
}
