package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveRunnerAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {
    override fun Sequence<StackTraceElement>.filterUndesiredFrames(): Sequence<StackTraceElement> = takeWhile {
        !it.className.startsWith("org.junit") && !it.className.startsWith("org.jetbrains.spek")
    }
}
