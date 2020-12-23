package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [List] type.
 */
interface ListAssertions {

    fun <E, T : List<E>> get(container: AssertionContainer<T>, index: Int): FeatureExtractorBuilder.ExecutionStep<T, E>
}
