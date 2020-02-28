package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectMarker
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Creates an [Expect] for the property [Pair.first] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 */
val <K, T : Pair<K, *>> Expect<T>.first get() : FeatureExpect<T, K> = ExpectImpl.pair.first(this).getExpectOfFeature()
infix fun <K, T : Pair<K, *>> Expect<T>.first(o: o): FeatureExpect<T, K> = first


/**
 * Expects that the property [Pair.first] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this [Expect].
 *
 * @return This [Expect] to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Pair<K, V>> Expect<T>.first(assertionCreator: Expect<K>.() -> Unit): Expect<T> =
    ExpectImpl.pair.first(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [Pair.second] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 */
val <V, T : Pair<*, V>> Expect<T>.second get() : FeatureExpect<T, V> = ExpectImpl.pair.second(this).getExpectOfFeature()
infix fun <V, T : Pair<*, V>>  Expect<T>.second(o: o): FeatureExpect<T, V> = second

/**
 * Expects that the property [Pair.second] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this [Expect].
 *
 * @return This [Expect] to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Pair<K, V>> Expect<T>.second(assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    ExpectImpl.pair.second(this).addToInitial(assertionCreator)
