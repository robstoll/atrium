package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Creates an [Expect] for the property [Pair.first] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <K, T : Pair<K, *>> Expect<T>.first: Expect<K>
    get() = ExpectImpl.pair.first(this).getExpectOfFeature()

/**
 * Expects that the property [Pair.first] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this [Expect].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <K, V, T : Pair<K, V>> Expect<T>.first(assertionCreator: Expect<K>.() -> Unit): Expect<T> =
    ExpectImpl.pair.first(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [Pair.second] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <V, T : Pair<*, V>> Expect<T>.second: Expect<V>
    get() = ExpectImpl.pair.second(this).getExpectOfFeature()

/**
 * Expects that the property [Pair.second] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this [Expect].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <K, V, T : Pair<K, V>> Expect<T>.second(assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    ExpectImpl.pair.second(this).addToInitial(assertionCreator)
