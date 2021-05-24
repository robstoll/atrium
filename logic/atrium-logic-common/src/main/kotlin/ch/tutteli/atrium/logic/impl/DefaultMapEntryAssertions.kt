package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

class DefaultMapEntryAssertions : MapEntryAssertions {

    override fun <K, V, T : Map.Entry<K, V>> isKeyValue(container: AssertionContainer<T>, key: K, value: V): Assertion =
        container.collect {
            _logic.key().collectAndLogicAppend { toBe(key) }
            _logic.value().collectAndLogicAppend { toBe(value) }
        }

    override fun <K, T : Map.Entry<K, *>> key(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, K> =
        container.property(Map.Entry<K, *>::key)

    override fun <V, T : Map.Entry<*, V>> value(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, V> =
        container.property(Map.Entry<*, V>::value)
}
