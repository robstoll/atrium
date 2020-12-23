// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.logic.creating.maplike.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.impl.DefaultMapLikeContainsExpectations


fun <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InAnyOrderSearchBehaviour>.keyValuePairsInAnyOrder(keyValuePairs: List<Pair<K, V>>): Assertion = impl.keyValuePairsInAnyOrder(this, keyValuePairs)

fun <K, V : Any, T : MapLike> MapLikeContains.EntryPointStepLogic<K, out V?, T, InAnyOrderSearchBehaviour>.keyWithValueAssertionsInAnyOrder(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>): Assertion =
    impl.keyWithValueAssertionsInAnyOrder(this, valueType, keyValues)


fun <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InAnyOrderOnlySearchBehaviour>.keyValuePairsInAnyOrderOnly(keyValuePairs: List<Pair<K, V>>): Assertion = impl.keyValuePairsInAnyOrderOnly(this, keyValuePairs)

fun <K, V : Any, T : MapLike> MapLikeContains.EntryPointStepLogic<K, out V?, T, InAnyOrderOnlySearchBehaviour>.keyWithValueAssertionsInAnyOrderOnly(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>): Assertion =
    impl.keyWithValueAssertionsInAnyOrderOnly(this, valueType, keyValues)


fun <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InOrderOnlySearchBehaviour>.keyValuePairsInOrderOnly(keyValuePairs: List<Pair<K, V>>): Assertion = impl.keyValuePairsInOrderOnly(this, keyValuePairs)

fun <K, V : Any, T : MapLike> MapLikeContains.EntryPointStepLogic<K, out V?, T, InOrderOnlySearchBehaviour>.keyWithValueAssertionsInOrderOnly(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>): Assertion =
    impl.keyWithValueAssertionsInOrderOnly(this, valueType, keyValues)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <K, V, T : Any, S : MapLikeContains.SearchBehaviour> MapLikeContains.EntryPointStepLogic<K, V, T, S>.impl: MapLikeContainsExpectations
    get() = container.getImpl(MapLikeContainsExpectations::class) { DefaultMapLikeContainsExpectations() }
