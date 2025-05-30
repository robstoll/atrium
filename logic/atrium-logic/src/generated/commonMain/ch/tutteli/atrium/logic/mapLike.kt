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
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Collection of assertion functions and builders which are applicable to subjects which can be transformed to a
 * [Map] - intended for types which are Map like such as [IterableLike] with an element type [Pair].
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultMapLikeAssertions


fun <T : MapLike, K, V> AssertionContainer<T>.builderContainsInMapLike(converter: (T) -> Map<out K, V>): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> = impl.builderContainsInMapLike(this, converter)

fun <K, T: MapLike> AssertionContainer<T>.containsKey(converter: (T) -> Map<out K, *>, key: K): Assertion =
    impl.containsKey(this, converter, key)

fun <K, T: MapLike> AssertionContainer<T>.containsNotKey(converter: (T) -> Map<out K, *>, key: K): Assertion =
    impl.containsNotKey(this, converter, key)

fun <K, V, T: MapLike> AssertionContainer<T>.getExisting(converter: (T) -> Map<out K, V>, key: K): FeatureExtractorBuilder.ExecutionStep<T, V> =
    impl.getExisting(this, converter, key)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: MapLikeAssertions
    get() = getImpl(MapLikeAssertions::class) { DefaultMapLikeAssertions() }
