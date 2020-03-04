package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the property [Map.Entry.key] of the subject of the assertion
 * is equal to the given [key] and the property [Map.Entry.value] is equal to the given [value].
 *
 * Kind of a shortcut for `and { key { toBe(key) }; value { toBe(value) } }` where `and` denotes an assertion group
 * block. Yet, the actual behaviour depends on implementation - could also be fail fast for instance or augment
 * reporting etc.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <K, V, T : Map.Entry<K, V>> Expect<T>.isKeyValue(key: K, value: V): Expect<T> =
    addAssertion(ExpectImpl.map.entry.isKeyValue(this, key, value))

/**
 * Creates an [Expect] for the property [Map.Entry.key] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <K, T : Map.Entry<K, *>> Expect<T>.key: Expect<K>
    get() = ExpectImpl.map.entry.key(this).getExpectOfFeature()

/**
 * Expects that the property [Map.Entry.key] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <K, V, T : Map.Entry<K, V>> Expect<T>.key(assertionCreator: Expect<K>.() -> Unit): Expect<T> =
    ExpectImpl.map.entry.key(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.Entry.value] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <V, T : Map.Entry<*, V>> Expect<T>.value: Expect<V>
    get() = ExpectImpl.map.entry.value(this).getExpectOfFeature()

/**
 * Expects that the property [Map.Entry.value] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <K, V, T : Map.Entry<K, V>> Expect<T>.value(assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    ExpectImpl.map.entry.value(this).addToInitial(assertionCreator)
