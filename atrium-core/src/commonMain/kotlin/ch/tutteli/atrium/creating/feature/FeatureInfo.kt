package ch.tutteli.atrium.creating.feature

import ch.tutteli.atrium.reporting.reportables.InlineElement

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
    //TODO 1.4.0 switch to Diagnostic as return type?
    fun <T, R> determine(extractor: T.() -> R, stacksToDrop: Int): String
}

