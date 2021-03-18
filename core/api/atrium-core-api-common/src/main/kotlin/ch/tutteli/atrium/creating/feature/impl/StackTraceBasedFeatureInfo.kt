package ch.tutteli.atrium.creating.feature.impl

import ch.tutteli.atrium.creating.feature.FeatureInfo

expect class StackTraceBasedFeatureInfo constructor(): FeatureInfo {
     override fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String
}

