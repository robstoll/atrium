//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress(/* TODO remove annotation with 1.0.0 */ "DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

fun <K, V, T : Map<out K, V>> AssertionContainer<T>.contains(keyValuePairs: List<Pair<K, V>>): Assertion = _mapImpl.contains(this, keyValuePairs)

fun <K, V : Any, T : Map<out K, V?>> AssertionContainer<T>.containsKeyWithValueAssertions(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>): Assertion =
    _mapImpl.containsKeyWithValueAssertions(this, valueType, keyValues)

fun <K, T : Map<out K, *>> AssertionContainer<T>.containsKey(key: K): Assertion = _mapImpl.containsKey(this, key)
fun <K, T : Map<out K, *>> AssertionContainer<T>.containsNotKey(key: K): Assertion = _mapImpl.containsNotKey(this, key)

fun <T : Map<*, *>> AssertionContainer<T>.isEmpty(): Assertion = _mapImpl.isEmpty(this)
fun <T : Map<*, *>> AssertionContainer<T>.isNotEmpty(): Assertion = _mapImpl.isNotEmpty(this)

fun <K, V, T : Map<out K, V>> AssertionContainer<T>.getExisting(key: K): ExtractedFeaturePostStep<T, V> = _mapImpl.getExisting(this, key)

fun <T : Map<*, *>> AssertionContainer<T>.size(): ExtractedFeaturePostStep<T, Int> = _mapImpl.size(this)
