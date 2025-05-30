// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.maplike.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.impl.DefaultMapLikeContainsAssertions


fun <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InAnyOrderSearchBehaviour>.keyValuePairsInAnyOrder(keyValuePairs: List<Pair<K, V>>): Assertion = impl.keyValuePairsInAnyOrder(this, keyValuePairs)

fun <K, V : Any, T : MapLike> MapLikeContains.EntryPointStepLogic<K, out V?, T, InAnyOrderSearchBehaviour>.keyWithValueAssertionsInAnyOrder(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>): Assertion =
    impl.keyWithValueAssertionsInAnyOrder(this, valueType, keyValues)


fun <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InAnyOrderOnlySearchBehaviour>.keyValuePairsInAnyOrderOnly(keyValuePairs: List<Pair<K, V>>): Assertion = impl.keyValuePairsInAnyOrderOnly(this, keyValuePairs)

fun <K, V : Any, T : MapLike> MapLikeContains.EntryPointStepLogic<K, out V?, T, InAnyOrderOnlySearchBehaviour>.keyWithValueAssertionsInAnyOrderOnly(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>): Assertion =
    impl.keyWithValueAssertionsInAnyOrderOnly(this, valueType, keyValues)


fun <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InOrderOnlySearchBehaviour>.keyValuePairsInOrderOnly(keyValuePairs: List<Pair<K, V>>, reportingOptions: InOrderOnlyReportingOptions.() -> Unit): Assertion =
    impl.keyValuePairsInOrderOnly(this, keyValuePairs, reportingOptions)

fun <K, V : Any, T : MapLike> MapLikeContains.EntryPointStepLogic<K, out V?, T, InOrderOnlySearchBehaviour>.keyWithValueAssertionsInOrderOnly(valueType: KClass<V>, keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>, reportingOptions: InOrderOnlyReportingOptions.() -> Unit): Assertion =
    impl.keyWithValueAssertionsInOrderOnly(this, valueType, keyValues, reportingOptions)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <K, V, T : Any, S : MapLikeContains.SearchBehaviour> MapLikeContains.EntryPointStepLogic<K, V, T, S>.impl: MapLikeContainsAssertions
    get() = container.getImpl(MapLikeContainsAssertions::class) { DefaultMapLikeContainsAssertions() }
