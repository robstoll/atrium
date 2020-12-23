// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultMapLikeAssertions


fun <T : MapLike, K, V> AssertionContainer<T>.builderContainsInMapLike(converter: (T) -> Map<out K, V>): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> = impl.builderContainsInMapLike(this, converter)

fun <K, T: MapLike> AssertionContainer<T>.containsKey(converter: (T) -> Map<out K, *>, key: K): Assertion =
    impl.containsKey(this, converter, key)

fun <K, T: MapLike> AssertionContainer<T>.containsNotKey(converter: (T) -> Map<out K, *>, key: K): Assertion =
    impl.containsNotKey(this, converter, key)

fun <K, V, T: MapLike> AssertionContainer<T>.getExisting(converter: (T) -> Map<out K, V>, key: K): FeatureExtractorBuilder.ExecutionStep<T, V> =
    impl.getExisting(this, converter, key)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: MapLikeAssertions
    get() = getImpl(MapLikeAssertions::class) { DefaultMapLikeAssertions() }
