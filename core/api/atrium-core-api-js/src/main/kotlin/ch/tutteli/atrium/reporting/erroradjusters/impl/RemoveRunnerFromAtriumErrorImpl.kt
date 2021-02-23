package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerFromAtriumError

actual class RemoveRunnerFromAtriumErrorImpl : FilterAtriumErrorAdjuster(), RemoveRunnerFromAtriumError {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        stackTrace.takeWhile { !runnerRegex.containsMatchIn(it) }

    companion object {
        val runnerRegex: Regex = Regex("[\\\\|/]node_modules[\\\\|/](mocha|jasmine)[\\\\|/]")
    }
}
