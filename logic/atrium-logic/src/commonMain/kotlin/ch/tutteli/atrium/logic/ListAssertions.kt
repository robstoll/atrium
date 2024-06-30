//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder


/**
 * Collection of assertion functions and builders which are applicable to subjects with a [List] type.
 */
//TODO 1.3.0 deprecate
interface ListAssertions {
    //TODO 1.3.0 change to ListLike in order that it works as well for arrays
    fun <E, T : List<E>> get(container: AssertionContainer<T>, index: Int): FeatureExtractorBuilder.ExecutionStep<T, E>
}
