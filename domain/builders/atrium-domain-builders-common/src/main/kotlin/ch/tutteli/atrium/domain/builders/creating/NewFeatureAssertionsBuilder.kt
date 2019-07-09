@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder


//TODO write KDoc
/**
 * Will be renamed to FeatureAssertionsBuilder with 1.0.0
 */
object NewFeatureAssertionsBuilder {

    /**
     *  Start a feature extraction with the help of the [FeatureExtractorBuilder].
     *
     * In case you do not want to extract a feature (e.g. get the first element of a `List`)
     * but merely want to transform the subject into another representation
     * (e.g. down-cast `Person` to `Student` or transform a `Sequence` into a `List`) then you should use
     * [ExpectImpl.changeSubject] instead.
     */
    inline fun <T> extractor(originalAssertionContainer: Expect<T>) =
        FeatureExtractorBuilder.builder(originalAssertionContainer)

}
