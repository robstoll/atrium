package ch.tutteli.atrium.creating.feature.impl

import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.creating.feature.FeatureInfo
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement

@ExperimentalFeatureInfo
expect class StackTraceBasedFeatureInfo(): FeatureInfo {
     override fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String
}

