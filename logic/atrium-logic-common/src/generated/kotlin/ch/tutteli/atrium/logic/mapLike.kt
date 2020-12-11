// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultMapLikeAssertions


fun <T : MapLike, K, V> AssertionContainer<T>.builderContainsInMapLike(converter: (T) -> Map<out K, V>): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> = impl.builderContainsInMapLike(this, converter)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: MapLikeAssertions
    get() = getImpl(MapLikeAssertions::class) { DefaultMapLikeAssertions() }
