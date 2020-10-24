package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.first
import ch.tutteli.atrium.logic.second

/**
 * Creates an [Expect] for the property [Pair.first] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PairAssertionSamples.firstFeature
 */
val <K, T : Pair<K, *>> Expect<T>.first: Expect<K>
    get() : Expect<K> = _logic.first().transform()

/**
 * Expects that the property [Pair.first] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PairAssertionSamples.first
 */
fun <K, V, T : Pair<K, V>> Expect<T>.first(assertionCreator: Expect<K>.() -> Unit): Expect<T> =
    _logic.first().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Pair.second] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PairAssertionSamples.secondFeature
 */
val <V, T : Pair<*, V>> Expect<T>.second: Expect<V>
    get() : Expect<V> = _logic.second().transform()

/**
 * Expects that the property [Pair.second] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PairAssertionSamples.second
 */
fun <K, V, T : Pair<K, V>> Expect<T>.second(assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    _logic.second().collectAndAppend(assertionCreator)
