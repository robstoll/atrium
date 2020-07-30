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

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultMapAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: MapAssertions
    get() = getImpl(MapAssertions::class) { DefaultMapAssertions() }

fun <K, V, T : Map<out K, V>> AssertionContainer<T>.contains(keyValuePairs: List<Pair<K, V>>): Assertion = impl.contains(this, keyValuePairs)

fun <K, V : Any, T : Map<out K, V?>> AssertionContainer<T>.containsKeyWithValueAssertions(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>): Assertion =
    impl.containsKeyWithValueAssertions(this, valueType, keyValues)

fun <K, T : Map<out K, *>> AssertionContainer<T>.containsKey(key: K): Assertion = impl.containsKey(this, key)
fun <K, T : Map<out K, *>> AssertionContainer<T>.containsNotKey(key: K): Assertion = impl.containsNotKey(this, key)

fun <T : Map<*, *>> AssertionContainer<T>.isEmpty(): Assertion = impl.isEmpty(this)
fun <T : Map<*, *>> AssertionContainer<T>.isNotEmpty(): Assertion = impl.isNotEmpty(this)

fun <K, V, T : Map<out K, V>> AssertionContainer<T>.getExisting(key: K): ExtractedFeaturePostStep<T, V> = impl.getExisting(this, key)

fun <T : Map<*, *>> AssertionContainer<T>.size(): ExtractedFeaturePostStep<T, Int> = impl.size(this)
