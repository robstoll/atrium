package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.map.get.builders.MapGetOption
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the subject of the assertion (a [Map]) contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'contains Pairs(keyValuePair)'.
 *
 * @return This assertion container to support a fluent API.
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
 * @return This assertion container to support a fluent API.
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
 * @return This assertion container to support a fluent API.
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
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(keyValues: All<KeyValue<K, V>>) =
    addAssertion(ExpectImpl.map.containsKeyWithValueAssertions(this, V::class, keyValues.toList().map { it.toPair() }))

/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, T : Map<out K, *>> Expect<T>.containsKey(key: K) = addAssertion(ExpectImpl.map.containsKey(this, key))

/**
 * Expects that the subject of the assertion (a [Map]) does not contain the given [key].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, T : Map<out K, *>> Expect<T>.containsNotKey(key: K) =
    addAssertion(ExpectImpl.map.containsNotKey(this, key))

/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key],
 * creates an [Expect] for the corresponding value and returns the newly created assertion container,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the feature.
 * @throws AssertionError Might throw an [AssertionError] if the given [key] does not exist.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K): Expect<V> =
    ExpectImpl.map.getExisting(this, key).getExpectOfFeature()

/**
 * Prepares the assertion about the return value of calling [get][Map.get] with the given [key].
 *
 * @return A fluent builder to finish the assertion.
 * */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: Key<K>): MapGetOption<K, V, T> =
    MapGetOption.create(this, key.key)


/**
 * Creates an [Expect] for the property [Map.keys] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the feature.
 */
val <K, T : Map<out K, *>> Expect<T>.keys: Expect<Set<K>>
    get() = keys(this).getExpectOfFeature()

/**
 * Expects that the property [Map.keys] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.keys(assertionCreator: Expect<Set<K>>.() -> Unit): Expect<T> =
    keys(this).addToInitial(assertionCreator)

private fun <K, T : Map<out K, *>> keys(e: Expect<T>) = ExpectImpl.feature.property(e, Map<out K, *>::keys)

/**
 * Expects that the subject of the assertion (a [Map]) is an empty [Map].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty) =
    addAssertion(ExpectImpl.map.isEmpty(this))

/**
 * Expects that the subject of the assertion (a [Map]) is not an empty [Map].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") Empty: Empty) =
    addAssertion(ExpectImpl.map.isNotEmpty(this))

/**
 * Creates an [Expect] for the property [Map.values] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the feature.
 */
val <V, T : Map<*, V>> Expect<T>.values: Expect<Collection<V>>
    get() = values().getExpectOfFeature()

/**
 * Expects that the property [Map.keys] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V, T : Map<K, V>> Expect<T>.values(assertionCreator: Expect<Collection<V>>.() -> Unit): Expect<T> =
    values().addToInitial(assertionCreator)

private fun <K, V, T : Map<out K, V>> Expect<T>.values() = ExpectImpl.feature.property(this, Map<out K, V>::values)

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(): Expect<Set<Map.Entry<K, V>>> =
    ExpectImpl.changeSubject(this).unreported { it.entries }

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>` and expects that it holds all assertions the given
 * [assertionCreator] creates.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(
    assertionCreator: Expect<Set<Map.Entry<K, V>>>.() -> Unit
): Expect<T> = apply { asEntries().addAssertionsCreatedBy(assertionCreator) }
