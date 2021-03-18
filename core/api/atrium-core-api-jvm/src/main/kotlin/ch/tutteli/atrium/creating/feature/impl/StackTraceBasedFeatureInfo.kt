@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.creating.feature.impl

import ch.tutteli.atrium.creating.feature.FeatureInfo

actual class StackTraceBasedFeatureInfo actual constructor()  : FeatureInfo {

    actual override fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String {
        val stackTraces = Exception().stackTrace
        val index = stacksToDrop + 1
        require(index < stackTraces.size) {
            "dropping stacksToDrop is not possible as there are only ${stackTraces.size} stacktraces available"
        }

        val stackTrace = stackTraces[index]
        return "its.definedIn(${stackTrace.fileName}:${stackTrace.lineNumber})"
    }
}

