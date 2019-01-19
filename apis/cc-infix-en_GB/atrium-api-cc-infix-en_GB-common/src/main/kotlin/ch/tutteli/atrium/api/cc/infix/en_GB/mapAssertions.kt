package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetOption
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second].
 *
 * Delegates to `contains Pairs(entry)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V : Any, T: Map<K, V>> Assert<T>.contains(keyValuePair: Pair<K, V>)
    = this contains Pairs(keyValuePair)

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains for each entry in [keyValuePairs], a key as defined by
 * entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one pair in [keyValuePairs]
 * is defined as `'a' to 1` and another pair in [Pairs] is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V : Any, T: Map<K, V>> Assert<T>.contains(keyValuePairs: Pairs<K, V>)
    = addAssertion(AssertImpl.map.contains(this, keyValuePairs.toList()))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second].
 *
 * Delegates to `containsNullable Pairs(entry)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <K, reified V : Any, T: Map<K, V?>> Assert<T>.containsNullable(keyValuePair: Pair<K, V?>)
    = this containsNullable Pairs(keyValuePair)

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains for each entry in [keyValuePairs], a key as defined by
 * entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <K, reified V : Any, T: Map<K, V?>> Assert<T>.containsNullable(keyValuePairs: Pairs<K, V?>)
    = addAssertion(AssertImpl.map.containsNullable(this, V::class, keyValuePairs.toList()))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains a key as defined by [keyValue]'s [KeyValue.key]
 * with a corresponding value which holds all assertions [keyValue]'s [KeyValue.valueAssertionCreator] might create.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V : Any, T: Map<K, V>> Assert<T>.contains(keyValue: KeyValue<K, V>)
    = contains(All(keyValue))

/**
 * Makes the assertion that for each of the [KeyValue] in [keyValues], the [Assert.subject][AssertionPlant.subject] contains a key
 * as defined by keyValue's [KeyValue.key] with a corresponding value which holds all assertions keyValues's
 * [KeyValue.valueAssertionCreator] might create.
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one of the
 * [keyValues] is defined as `Key('a') { isGreaterThan(0) }` and another one is defined as `Key('a') { isLessThan(2) }`
 * then both match, even though they match the same entry.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K, V : Any, T: Map<K, V>> Assert<T>.contains(keyValues: All<KeyValue<K, V>>)
    = addAssertion(AssertImpl.map.containsKeyWithValueAssertions(this, keyValues.toList().map { it.toPair() }))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains a key as defined by [keyValue]'s [KeyNullableValue.key]
 * with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyNullableValue.valueAssertionCreatorOrNull] might create or needs to be `null` in case
 * [KeyNullableValue.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <K, reified V : Any, T: Map<K, V?>> Assert<T>.containsNullable(keyValue: KeyNullableValue<K, V>)
    = containsNullable(All(keyValue))

/**
 * Makes the assertion that for each of the [KeyNullableValue] in [keyValues], the [Assert.subject][AssertionPlant.subject] contains
 * a key as defined by keyValue's [KeyNullableValue.key] with a corresponding value which either holds all assertions
 * keyValue's [KeyNullableValue.valueAssertionCreatorOrNull] might create or needs to be `null` in case
 * [KeyNullableValue.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one of the
 * [keyValues] is defined as `Key('a') { isGreaterThan(0) }` and another one is defined as `Key('a') { isLessThan(2) }`
 * then both match, even though they match the same entry.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline infix fun <K, reified V : Any, T: Map<K, V?>> Assert<T>.containsNullable(keyValues: All<KeyNullableValue<K, V>>)
    = addAssertion(AssertImpl.map.containsKeyWithNullableValueAssertions(this, V::class, keyValues.toList().map { it.toPair() }))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] contains the given [key].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K> Assert<Map<K, *>>.containsKey(key: K)
    = addAssertion(AssertImpl.map.containsKey(this, key))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] does not contain the given [key].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <K> Assert<Map<K, *>>.containsNotKey(key: K)
    = addAssertion(AssertImpl.map.containsNotKey(this, key))

/**
 * Prepares the assertion about the return value of calling [get][Map.get] with the given [key].
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <K, V: Any, T: Map<K, V>> Assert<T>.getExisting(key: K): MapGetOption<K, V, T>
    = MapGetOption.create(this, key)

/**
 * Prepares the assertion about the nullable return value of calling [get][Map.get] with the given [key].
 *
 * Notice, that the corresponding value of the given [key] can be `null` even if the key exists as the [Map] has a
 * nullable value type.
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <K, V, T: Map<K, V>> Assert<T>.getExistingNullable(key: K): MapGetNullableOption<K, V, T>
    = MapGetNullableOption.create(this, key)

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s [Map.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Assert<T>.hasSize(size: Int)
    = addAssertion(AssertImpl.map.hasSize(this, size))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] is an empty [Map].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Assert<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(AssertImpl.map.isEmpty(this))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject] is not an empty [Map].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Assert<T>.notToBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(AssertImpl.map.isNotEmpty(this))


/**
 * Creates an [AssertionPlant] for the [Assert.subject][AssertionPlant.subject]'s property [keys][Map.keys] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K, V> Assert<Map<K, V>>.keys get() : Assert<Set<K>> = property(Map<K, V>::keys)

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s property [keys][Map.keys] holds all assertions the given
 * [assertionCreator] might create.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 */
infix fun <K, V, T: Map<K, V>> Assert<T>.keys(assertionCreator: Assert<Set<K>>.() -> Unit)
    = addAssertion(AssertImpl.map.keys(this, assertionCreator))

/**
 * Creates an [AssertionPlant] for the [Assert.subject][AssertionPlant.subject]'s property [values][Map.values] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K, V> Assert<Map<K, V>>.values get() : Assert<Collection<V>> = property(Map<K, V>::values)

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s property [values][Map.values] holds all assertions the given
 * [assertionCreator] might create.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 */
infix fun <K, V, T: Map<K, V>> Assert<T>.values(assertionCreator: Assert<Collection<V>>.() -> Unit)
    = addAssertion(AssertImpl.map.values(this, assertionCreator))

/**
 * Turns `Assert<Map<K, V>>` into `Assert<Set<Map.Entry<K, V>>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `property(subject::entries)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <K, V> Assert<Map<K, V>>.asEntries(): Assert<Set<Map.Entry<K, V>>>
    = AssertImpl.changeSubject(this) { subject.entries }

/**
 * Turns `Assert<Map<K, V>>` into `Assert<Set<Map.Entry<K, V>>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `property(subject::entries)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
infix fun <K, V> Assert<Map<K, V>>.asEntries(assertionCreator: Assert<Set<Map.Entry<K, V>>>.() -> Unit): Assert<Set<Map.Entry<K, V>>>
    = asEntries().addAssertionsCreatedBy(assertionCreator)
