package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.KeyWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.kbox.identity

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key],
 * creates an [Expect] for the corresponding value and returns the newly created [Expect],
 * so that further fluent calls are expectations about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.getExistingFeature
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K): Expect<V> =
    _logic.getExisting(::identity, key).transform()

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key] and that
 * the corresponding value holds all assertions the given [KeyWithCreator.assertionCreator] creates for it.
 *
 * @param key Use the function `key(...) { ... }` to create a [KeyWithCreator] where the first parameter corresponds
 *  to the key and the second is the `assertionCreator`-lambda
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.getExisting
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: KeyWithCreator<K, V>): Expect<T> =
    _logic.getExisting(::identity, key.key).collectAndAppend(key.assertionCreator)

/**
 * Helper function to create an [KeyWithCreator] based on the given [key] and [assertionCreator].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.getExisting
 */
fun <K, V> key(key: K, assertionCreator: Expect<V>.() -> Unit): KeyWithCreator<K, V> =
    KeyWithCreator(key, assertionCreator)

//TODO consider with 2.0.0 if we should add the filler `o` as well (or in addition)
/**
 * Creates an [Expect] for the property [Map.keys] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.keysFeature
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.keys
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.keys(assertionCreator: Expect<Set<K>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, *>::keys).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.values] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.valuesFeature
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.values
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.values(assertionCreator: Expect<Collection<V>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, V>::values).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Collection.size] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.sizeFeature
 *
 * @since 0.15.0
 */
val <T : Map<*, *>> Expect<T>.size: Expect<Int>
    get() = _logic.size(::toEntries).transform()

/**
 * Expects that the property [Collection.size] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapFeatureExtractorSamples.size
 *
 * @since 0.15.0
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.size(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    _logic.size(::toEntries).collectAndAppend(assertionCreator)

private fun <T : Map<*, *>> toEntries(t: T): Collection<*> = t.entries
