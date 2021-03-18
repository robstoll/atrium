package ch.tutteli.atrium.creating.feature

interface FeatureInfo {
    fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String
}

