package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import kotlin.js.JsName
import kotlin.jvm.JvmName

/**
 * Expects that the property [Map.Entry.key] of the subject of the assertion
 * is equal to the given [key] and the property [Map.Entry.value] is equal to the given [value].
 *
 * Kind of a shortcut for `and { key { toBe(key) }; value { toBe(value) } }` where `and` denotes an assertion group
 * block. Yet, the actual behaviour depends on implementation - could also be fail fast for instance or augment
 * reporting etc.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K : Any, V : Any, T : Map.Entry<K, V>> Expect<T>.isKeyValue(keyValuePair: Pair<K, V>): Expect<T> =
    addAssertion(ExpectImpl.map.entry.isKeyValue(this, keyValuePair.first, keyValuePair.second))

/**
 * Expects that the property [Map.Entry.key] of the subject of the assertion
 * is equal to the given [key] and the property [Map.Entry.value] is equal to the given [value].
 *
 * Kind of a shortcut for `and { key { toBe(key) }; value { toBe(value) } }` where `and` denotes an assertion group
 * block. Yet, the actual behaviour depends on implementation - could also be fail fast for instance or augment
 * reporting etc.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@JvmName("isKeyValueNullable")
@JsName("isKeyValueNullable")
inline infix fun <reified K : Any, reified V : Any, T : Map.Entry<K?, V?>> Expect<T>.isKeyValue(keyValuePair: Pair<K, V>): Expect<T>
    = addAssertion(ExpectImpl.map.entry.isKeyValue(this, keyValuePair.first, keyValuePair.second, K::class, V::class))

/**
 * Creates an [Expect] for the property [Map.Entry.key] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 */
val <K, T : Map.Entry<K, *>> Expect<T>.key get() : Expect<K> = ExpectImpl.map.entry.key(this).getExpectOfFeature()

/**
 * Expects that the property [Map.Entry.key] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map.Entry<K, V>> Expect<T>.key(assertionCreator: Expect<K>.() -> Unit): Expect<T> =
    ExpectImpl.map.entry.key(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.Entry.value] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 */
val <V, T : Map.Entry<*, V>> Expect<T>.value get() : Expect<V> = ExpectImpl.map.entry.value(this).getExpectOfFeature()

/**
 * Expects that the property [Map.Entry.value] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map.Entry<K, V>> Expect<T>.value(assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    ExpectImpl.map.entry.value(this).addToInitial(assertionCreator)
