package ch.tutteli.atrium.creating.feature.impl

import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.creating.feature.FeatureInfo

@ExperimentalFeatureInfo
expect class StackTraceBasedFeatureInfo constructor(): FeatureInfo {
     override fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String
}

