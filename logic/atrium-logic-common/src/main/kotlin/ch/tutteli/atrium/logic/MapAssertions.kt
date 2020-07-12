@file:Suppress(/* TODO remove annotation with 1.0.0 */ "DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

interface MapAssertions {
    fun <K, V, T : Map<out K, V>> contains(
        container: AssertionContainer<T>,
        keyValuePairs: List<Pair<K, V>>
    ): Assertion

    fun <K, V : Any, T : Map<out K, V?>> containsKeyWithValueAssertions(
        container: AssertionContainer<T>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ): Assertion

    fun <K, T : Map<out K, *>> containsKey(container: AssertionContainer<T>, key: K): Assertion
    fun <K, T : Map<out K, *>> containsNotKey(container: AssertionContainer<T>, key: K): Assertion

    fun <T : Map<*, *>> isEmpty(container: AssertionContainer<T>): Assertion
    fun <T : Map<*, *>> isNotEmpty(container: AssertionContainer<T>): Assertion

    fun <K, V, T : Map<out K, V>> getExisting(container: AssertionContainer<T>, key: K): ExtractedFeaturePostStep<T, V>

    fun <T : Map<*, *>> size(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, Int>
}
