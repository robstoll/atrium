package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class RemoveRunnerAtriumErrorAdjuster : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> = stackTrace.takeWhile {
        !runnerMochaRegex.containsMatchIn(it) &&
            !runnerJasmineRegex.containsMatchIn(it)
    }

    companion object {
        val runnerMochaRegex: Regex = Regex("[\\\\|/]mocha[\\\\|/]")
        val runnerJasmineRegex: Regex = Regex("[\\\\|/]jasmine[\\\\|/]")
    }
}
