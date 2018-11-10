package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveAtriumFromAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {
    override fun Sequence<String>.filterUndesiredFrames(): Sequence<String> = dropWhile {
        atriumRegex.containsMatchIn(it)
    }

    companion object {
        val atriumRegex = Regex("[\\\\|/]atrium-[a-zA-Z-]+.js")
    }
}
