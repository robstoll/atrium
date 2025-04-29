package ch.tutteli.atrium.creating.feature.impl

import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.creating.feature.FeatureInfo
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement

@ExperimentalFeatureInfo
actual class StackTraceBasedFeatureInfo actual constructor() : FeatureInfo {

    actual override fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String {
        require(stacksToDrop >= 0) {
            "stacksToDrop needs to be greater or equal to 0"
        }
        val stackTraces = Exception().stackBacktrace
        val index = stacksToDrop + 1
        require(index < stackTraces.size) {
            "dropping stacksToDrop is not possible as there are only ${stackTraces.size} stacktraces available"
        }

        val stackTrace = stackTraces[index]
        return "its.definedIn(${stackTrace.substringAfterLast("/").substringAfterLast("\\")}"
    }
}

