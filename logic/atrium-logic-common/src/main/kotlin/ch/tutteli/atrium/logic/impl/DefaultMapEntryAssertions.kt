package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.*

class DefaultMapEntryAssertions : MapEntryAssertions {

    override fun <K, V, T : Map.Entry<K, V>> isKeyValue(container: AssertionContainer<T>, key: K, value: V): Assertion =
        container.collect {
            _logic.key().addToInitial { _logicAppend { toBe(key) } }
            _logic.value().addToInitial { _logicAppend { toBe(value) } }
        }

    override fun <K, T : Map.Entry<K, *>> key(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, K> =
        container.property(Map.Entry<K, *>::key)

    override fun <V, T : Map.Entry<*, V>> value(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, V> =
        container.property(Map.Entry<*, V>::value)
}
