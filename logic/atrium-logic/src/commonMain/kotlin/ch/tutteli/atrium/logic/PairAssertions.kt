//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Pair] type.
 */
//TODO 1.3.0 deprecate
interface PairAssertions {
    fun <K, T : Pair<K, *>> first(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, K>
    fun <V, T : Pair<*, V>> second(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, V>
}
