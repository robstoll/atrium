package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveRunnerAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {
    override fun Sequence<String>.filterUndesiredStackFrames(): Sequence<String> = takeWhile {
        !runnerRegex.containsMatchIn(it)
    }

    companion object {
        val runnerRegex = Regex("[\\\\|/]mocha[\\\\|/]")
    }
}
