package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.All
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValue
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithCreator
import ch.tutteli.atrium.api.infix.en_GB.creating.Pairs
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the subject of the assertion (a [Map]) contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'contains Pairs(keyValuePair)'.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(keyValuePair: Pair<K, V>) =
    contains(Pairs(keyValuePair))

/**
 * Expects the subject of the assertion (a [Map]) contains for each entry in [keyValuePairs],
 * a key as defined by that entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one of the [Pair]
 * in [keyValuePairs] is defined as `'a' to 1` and another one is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(keyValuePairs: Pairs<K, V>): Expect<T> =
    addAssertion(ExpectImpl.map.contains(this, keyValuePairs.toList()))

/**
 * Expects that the subject of the assertion (a [Map]) contains a key as defined by [keyValue]'s [KeyValue.key]
 * with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(keyValue: KeyValue<K, V>): Expect<T> =
    contains(All(keyValue))

/**
 * Expects that the subject of the assertion (a [Map]) contains for each [KeyValue] in [keyValues],
 * a key as defined by [KeyValue.key] with a corresponding value which either holds all
 * assertions [KeyValue]'s [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one [KeyValue] in
 * [keyValues] is defined as `Key('a') { isGreaterThan(0) }` and another one is defined as `Key('a') { isLessThan(2) }`
 * , then both match, even though they match the same entry.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(keyValues: All<KeyValue<K, V>>) =
    addAssertion(ExpectImpl.map.containsKeyWithValueAssertions(this, V::class, keyValues.toList().map { it.toPair() }))

/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, T : Map<out K, *>> Expect<T>.containsKey(key: K) = addAssertion(ExpectImpl.map.containsKey(this, key))

/**
 * Expects that the subject of the assertion (a [Map]) does not contain the given [key].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, T : Map<out K, *>> Expect<T>.containsNotKey(key: K) =
    addAssertion(ExpectImpl.map.containsNotKey(this, key))

/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key],
 * creates an [Expect] for the corresponding value and returns the newly created assertion container,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the given [key] does not exist.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K): Expect<V> =
    ExpectImpl.map.getExisting(this, key).getExpectOfFeature()

/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key] and that
 * the corresponding value holds all assertions the given [KeyWithCreator.assertionCreator] creates for it.
 *
 * @param key Use the function `key(...) { ... }` to create a [KeyWithCreator] where the first parameter corresponds
 *  to the key and the second is the `assertionCreator`-lambda
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] the given [key] does not exist or
 *   if the assertion made is not correct.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: KeyWithCreator<K, V>): Expect<T> =
    ExpectImpl.map.getExisting(this, key.key).addToInitial(key.assertionCreator)

/**
 * Helper function to create an [KeyWithCreator] based on the given [key] and [assertionCreator].
 */
fun <K, V> key(key: K, assertionCreator: Expect<V>.() -> Unit) = KeyWithCreator(key, assertionCreator)


/**
 * Creates an [Expect] for the property [Map.keys] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <K, T : Map<out K, *>> Expect<T>.keys: Expect<Set<K>>
    get() = ExpectImpl.feature.property(this, Map<out K, *>::keys).getExpectOfFeature()

/**
 * Expects that the property [Map.keys] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.keys(assertionCreator: Expect<Set<K>>.() -> Unit): Expect<T> =
    ExpectImpl.feature.property(this, Map<out K, *>::keys).addToInitial(assertionCreator)

/**
 * Expects that the subject of the assertion (a [Map]) is an empty [Map].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty) =
    addAssertion(ExpectImpl.map.isEmpty(this))

/**
 * Expects that the subject of the assertion (a [Map]) is not an empty [Map].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") Empty: Empty) =
    addAssertion(ExpectImpl.map.isNotEmpty(this))

/**
 * Creates an [Expect] for the property [Map.values] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <V, T : Map<*, V>> Expect<T>.values: Expect<Collection<V>>
    get() = ExpectImpl.feature.property(this, Map<out Any?, V>::values).getExpectOfFeature()

/**
 * Expects that the property [Map.keys] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map<K, V>> Expect<T>.values(assertionCreator: Expect<Collection<V>>.() -> Unit): Expect<T> =
    ExpectImpl.feature.property(this, Map<out K, V>::values).addToInitial(assertionCreator)

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(
    @Suppress("UNUSED_PARAMETER") o: o
): Expect<Set<Map.Entry<K, V>>> = ExpectImpl.changeSubject(this).unreported { it.entries }

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>` and expects that it holds all assertions the given
 * [assertionCreator] creates for it.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(
    assertionCreator: Expect<Set<Map.Entry<K, V>>>.() -> Unit
): Expect<T> = apply { asEntries(o).addAssertionsCreatedBy(assertionCreator) }

