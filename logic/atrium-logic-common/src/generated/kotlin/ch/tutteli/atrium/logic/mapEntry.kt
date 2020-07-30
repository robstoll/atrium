//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultMapEntryAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: MapEntryAssertions
    get() = getImpl(MapEntryAssertions::class) { DefaultMapEntryAssertions() }

fun <K, V, T : Map.Entry<K, V>> AssertionContainer<T>.isKeyValue(key: K, value: V): Assertion =
    impl.isKeyValue(this, key, value)
fun <K, T : Map.Entry<K, *>> AssertionContainer<T>.key(): ExtractedFeaturePostStep<T, K> = impl.key(this)
fun <V, T : Map.Entry<*, V>> AssertionContainer<T>.value(): ExtractedFeaturePostStep<T, V> = impl.value(this)
