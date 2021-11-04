@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

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

/**
 * Defines the minimum set of `contains` assertion functions for [Iterable],
 * which an implementation of the domain logic of Atrium has to provide.
 */
interface MapLikeContainsAssertions {

    fun <K, V, T : MapLike> keyValuePairsInAnyOrder(
        entryPointStepLogic: MapLikeContains.EntryPointStepLogic<K, V, T, InAnyOrderSearchBehaviour>,
        keyValuePairs: List<Pair<K, V>>
    ): Assertion

    fun <K, V : Any, T : MapLike> keyWithValueAssertionsInAnyOrder(
        entryPointStepLogic: MapLikeContains.EntryPointStepLogic<K, out V?, T, InAnyOrderSearchBehaviour>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ): Assertion


    fun <K, V, T : MapLike> keyValuePairsInAnyOrderOnly(
        entryPointStepLogic: MapLikeContains.EntryPointStepLogic<K, V, T, InAnyOrderOnlySearchBehaviour>,
        keyValuePairs: List<Pair<K, V>>
    ): Assertion

    fun <K, V : Any, T : MapLike> keyWithValueAssertionsInAnyOrderOnly(
        entryPointStepLogic: MapLikeContains.EntryPointStepLogic<K, out V?, T, InAnyOrderOnlySearchBehaviour>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ): Assertion


    fun <K, V, T : MapLike> keyValuePairsInOrderOnly(
        entryPointStepLogic: MapLikeContains.EntryPointStepLogic<K, V, T, InOrderOnlySearchBehaviour>,
        keyValuePairs: List<Pair<K, V>>,
        reportingOptions: InOrderOnlyReportingOptions.() -> Unit
    ): Assertion

    //TODO remove with 0.18.0 only here for backward compatibility with specs
    fun <K, V : Any, T : MapLike> keyWithValueAssertionsInOrderOnly(
        entryPointStepLogic: MapLikeContains.EntryPointStepLogic<K, out V?, T, InOrderOnlySearchBehaviour>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>
    ): Assertion

    fun <K, V : Any, T : MapLike> keyWithValueAssertionsInOrderOnly(
        entryPointStepLogic: MapLikeContains.EntryPointStepLogic<K, out V?, T, InOrderOnlySearchBehaviour>,
        valueType: KClass<V>,
        keyValues: List<Pair<K, (Expect<V>.() -> Unit)?>>,
        reportingOptions: InOrderOnlyReportingOptions.() -> Unit
    ): Assertion
}
