package ch.tutteli.atrium.reporting.erroradjusters.impl

import ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerFromAtriumError

actual class RemoveRunnerFromAtriumErrorImpl : FilterAtriumErrorAdjuster(), RemoveRunnerFromAtriumError {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        stackTrace.takeWhile { !runnerRegex.containsMatchIn(it) }

    companion object {
        val runnerRegex: Regex = Regex(
            // kotlin 1.6
            """([\\|/]packages_imported[\\|/]kotlin-test-js-runner[\\|/])|""" +
                // kotlin 1.3
                """([\\|/]node_modules[\\|/](mocha|jasmine|jest)[\\|/])|""" +
                // intellij specific
                """(node_modules[\\|/]src[\\|/]KotlinTestTeamCityConsoleAdapter\.ts)"""
        )
    }
}
