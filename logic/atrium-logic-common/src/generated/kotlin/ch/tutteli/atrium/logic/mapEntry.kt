//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

fun <K, V, T : Map.Entry<K, V>> AssertionContainer<T>.isKeyValue(key: K, value: V): Assertion =
    _mapEntryImpl.isKeyValue(this, key, value)
fun <K, T : Map.Entry<K, *>> AssertionContainer<T>.key(): FeatureExtractorBuilder.ExecutionStep<T, K> = _mapEntryImpl.key(this)
fun <V, T : Map.Entry<*, V>> AssertionContainer<T>.value(): FeatureExtractorBuilder.ExecutionStep<T, V> = _mapEntryImpl.value(this)
