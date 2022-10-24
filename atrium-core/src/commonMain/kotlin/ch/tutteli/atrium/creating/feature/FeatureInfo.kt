package ch.tutteli.atrium.creating.feature

@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class ExperimentalFeatureInfo


/**
 * Responsible to determine the feature description for a given feature extractor.
 */
@ExperimentalFeatureInfo
interface FeatureInfo {
    /**
     * Returns the feature description for the feature extracted by the given [extractor] where [stacksToDrop] indicates
     * how many stack frames away the actual extraction was defined.
     */
    fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String
}

