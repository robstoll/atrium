//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.getExisting
import ch.tutteli.atrium.logic.property
import ch.tutteli.atrium.logic.size
import ch.tutteli.kbox.identity

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key],
 * creates an [Expect] for the corresponding value and returns the newly created [Expect],
 * so that further fluent calls are expectations about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.getExistingFeature
 */
fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K): Expect<V> =
    _logic.getExisting(::identity, key).transform()

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key] and that
 * the corresponding value holds all assertions the given [assertionCreator] creates for it.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.getExisting
 */
fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K, assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    _logic.getExisting(::identity, key).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.keys] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.keysFeature
 */
val <K, T : Map<out K, *>> Expect<T>.keys: Expect<Set<K>>
    get() = _logic.property(Map<out K, *>::keys).transform()

/**
 * Expects that the property [Map.keys] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.keys
 */
fun <K, V, T : Map<out K, V>> Expect<T>.keys(assertionCreator: Expect<Set<K>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, *>::keys).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.values] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.valuesFeature
 */
val <V, T : Map<*, V>> Expect<T>.values: Expect<Collection<V>>
    get() = _logic.property(Map<out Any?, V>::values).transform()

/**
 * Expects that the property [Map.keys] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.values
 */
fun <K, V, T : Map<out K, V>> Expect<T>.values(assertionCreator: Expect<Collection<V>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, V>::values).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.size] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.15.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.sizeFeature
 */
val <T : Map<*, *>> Expect<T>.size: Expect<Int>
    get() = _logic.size(::toEntries).transform()

/**
 * Expects that the property [Map.size] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapFeatureExtractorSamples.size
 */
fun <K, V, T : Map<out K, V>> Expect<T>.size(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    _logic.size(::toEntries).collectAndAppend(assertionCreator)

private fun <T : Map<*, *>> toEntries(t: T): Collection<*> = t.entries
