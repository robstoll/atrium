//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.key
import ch.tutteli.atrium.logic.value

/**
 * Creates an [Expect] for the property [Map.Entry.key] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapEntryFeatureExtractorSamples.keyFeature
 */
val <K, T : Map.Entry<K, *>> Expect<T>.key: Expect<K>
    get() = _logic.key().transform()

/**
 * Expects that the property [Map.Entry.key] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapEntryFeatureExtractorSamples.key
 */
fun <K, V, T : Map.Entry<K, V>> Expect<T>.key(assertionCreator: Expect<K>.() -> Unit): Expect<T> =
    _logic.key().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.Entry.value] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapEntryFeatureExtractorSamples.valueFeature
 */
val <V, T : Map.Entry<*, V>> Expect<T>.value: Expect<V>
    get() = _logic.value().transform()

/**
 * Expects that the property [Map.Entry.value] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapEntryFeatureExtractorSamples.value
 */
fun <K, V, T : Map.Entry<K, V>> Expect<T>.value(assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    _logic.value().collectAndAppend(assertionCreator)
