package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Map.Entry] type.
 */
interface MapEntryAssertions {
    fun <K, V, T : Map.Entry<K, V>> isKeyValue(container: AssertionContainer<T>, key: K, value: V): Assertion
    fun <K, T : Map.Entry<K, *>> key(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, K>
    fun <V, T : Map.Entry<*, V>> value(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, V>
}
