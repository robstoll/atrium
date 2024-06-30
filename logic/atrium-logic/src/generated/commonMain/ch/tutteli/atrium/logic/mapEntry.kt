// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Map.Entry] type.
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultMapEntryAssertions

fun <K, V, T : Map.Entry<K, V>> AssertionContainer<T>.isKeyValue(key: K, value: V): Assertion =
    impl.isKeyValue(this, key, value)
fun <K, T : Map.Entry<K, *>> AssertionContainer<T>.key(): FeatureExtractorBuilder.ExecutionStep<T, K> = impl.key(this)
fun <V, T : Map.Entry<*, V>> AssertionContainer<T>.value(): FeatureExtractorBuilder.ExecutionStep<T, V> = impl.value(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: MapEntryAssertions
    get() = getImpl(MapEntryAssertions::class) { DefaultMapEntryAssertions() }
