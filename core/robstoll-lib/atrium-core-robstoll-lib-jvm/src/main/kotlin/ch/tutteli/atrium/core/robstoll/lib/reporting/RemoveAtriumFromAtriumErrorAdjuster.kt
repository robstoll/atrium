package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveAtriumFromAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {
    override fun Sequence<StackTraceElement>.filterUndesiredFrames(): Sequence<StackTraceElement> = dropWhile {
        it.className.startsWith("ch.tutteli.atrium")
    }
}
