package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.All
import ch.tutteli.atrium.api.infix.en_GB.creating.KeyWithCreator
import ch.tutteli.atrium.api.infix.en_GB.creating.Pairs
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValues
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.kbox.identity

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect].
 *
 * @return The newly created builder.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(
    @Suppress("UNUSED_PARAMETER") o: o
): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> = _logic.builderContainsInMapLike(::identity)

/**
 * Expects that the subject of the assertion (a [Map]) contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'it contains o inAny order entry keyValuePair'.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(keyValuePair: Pair<K, V>): Expect<T> =
    it contains o inAny order entry keyValuePair

/**
 * Expects that the subject of the assertion (a [Map]) contains only one entry with a key as defined by
 * [keyValuePair]'s [Pair.first] and a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'it contains o inAny order but only entry keyValuePair'.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.containsOnly(keyValuePair: Pair<K, V>): Expect<T> =
    it contains o inAny order but only entry keyValuePair

/**
 * Expects the subject of the assertion (a [Map]) contains for each entry in [keyValuePairs],
 * a key as defined by that entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * Delegates to `it contains o inAny order keyValuePairs keyValuePairs`
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one of the [Pair]
 * in [keyValuePairs] is defined as `'a' to 1` and another one is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @param keyValuePairs The key-value [Pairs] expected to be contained within this [Map]
 *   -- use the function `pairs(x to y, ...)` to create a [Pairs].
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(keyValuePairs: Pairs<K, V>): Expect<T> =
    it contains o inAny order the keyValuePairs

/**
 * Expects the subject of the assertion (a [Map]) contains only (in any order) for each entry in [keyValuePairs],
 * a key as defined by that entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * Delegates to `it contains o inAny order but only the keyValuePairs`
 *
 * @param keyValuePairs The key-value [Pairs] expected to be contained within this [Map]
 *   -- use the function `pairs(x to y, ...)` to create a [Pairs].
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.containsOnly(keyValuePairs: Pairs<K, V>): Expect<T> =
    it contains o inAny order but only the keyValuePairs

/**
 * Expects that the subject of the assertion (a [Map]) contains a key as defined by [keyValue]'s [KeyWithValueCreator.key]
 * with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it contains o inAny order keyValue keyValue`
 *
 * @param keyValue The [KeyWithValueCreator] whose key is expected to be contained within this [Map] and
 *   where the corresponding value holds all assertions the  [KeyWithValueCreator.valueAssertionCreatorOrNull] creates
 *   or needs to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *   -- use the function `keyValue(x) { ... }` to create a [KeyWithValueCreator].
 *
 * @return An [Expect] for the current subject of the assertion.
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(keyValue: KeyWithValueCreator<K, V>): Expect<T> =
    it contains o inAny order entry keyValue

/**
 * Expects that the subject of the assertion (a [Map]) contains only one entry with a key as defined by
 * [keyValue]'s [KeyWithValueCreator.key] with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it contains o inAny order but only entry keyValue`
 *
 * @param keyValue The [KeyWithValueCreator] whose key is expected to be contained within this [Map] and
 *   where the corresponding value holds all assertions the  [KeyWithValueCreator.valueAssertionCreatorOrNull] creates
 *   or needs to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *   -- use the function `keyValue(x) { ... }` to create a [KeyWithValueCreator].
 *
 * @return An [Expect] for the current subject of the assertion.
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.containsOnly(keyValue: KeyWithValueCreator<K, V>): Expect<T> =
    it contains o inAny order but only entry keyValue

/**
 * Helper function to create a [KeyWithValueCreator] based on the given [key] and [valueAssertionCreatorOrNull]
 * -- allows to express `Pair<K, V>, vararg Pair<K, V>`.
 */
fun <K, V : Any> keyValue(key: K, valueAssertionCreatorOrNull: (Expect<V>.() -> Unit)?): KeyWithValueCreator<K, V> =
    KeyWithValueCreator(key, valueAssertionCreatorOrNull)

/**
 * Expects that the subject of the assertion (a [Map]) contains for each [KeyWithValueCreator] in [allKeyValues],
 * a key as defined by [KeyWithValueCreator.key] with a corresponding value which either holds all
 * assertions [KeyWithValueCreator]'s [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs
 * to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it contains o inAny order the keyValues`
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and
 * one [KeyWithValueCreator] in [allKeyValues] is defined as `Key('a') { isGreaterThan(0) }` and
 * another one is defined as `Key('a') { isLessThan(2) }`, then both match, even though they match the same entry.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(
    allKeyValues: KeyValues<K, V>
): Expect<T> = it contains o inAny order the keyValues(allKeyValues.expected, *allKeyValues.otherExpected)

//TODO 0.16.0 check if we really go with `to contain` since `to` caused troubles in the past
@Deprecated(
    "Use `to contain keyValues` instead; will be removed with 1.0.0",
    ReplaceWith(
        "this toContain keyValues(all.expected, *all.otherExpected)",
        "ch.tutteli.atrium.api.infix.en_GB.keyValues"
    )
)
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(
    all: All<KeyWithValueCreator<K, V>>
): Expect<T> = it contains keyValues(all.expected, *all.otherExpected)

/**
 * Expects that the subject of the assertion (a [Map]) contains only (in any order) for each [KeyWithValueCreator]
 * in [allKeyValues], a key as defined by [KeyWithValueCreator.key] with a corresponding value which either holds all
 * assertions [KeyWithValueCreator]'s [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs
 * to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it contains o inAny order but only the keyValues`
 *
 * @return An [Expect] for the current subject of the assertion.
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.containsOnly(
    allKeyValues: KeyValues<K, V>
): Expect<T> = it contains o inAny order but only the keyValues(allKeyValues.expected, *allKeyValues.otherExpected)

/**
 * Expects that the subject of the assertion (a [Map]) contains the key-value pairs of the given [mapLike].
 *
 * Delegates to `it contains o inAny order entriesOf`
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V : Any, T : Map<out K, V?>> Expect<T>.containsEntriesOf(
    mapLike: MapLike
): Expect<T> = it contains o inAny order entriesOf mapLike

/**
 * Expects that the subject of the assertion (a [Map]) contains only (in any order) the key-value pairs of
 * the given [mapLike].
 *
 * Delegates to `it contains o inAny order but only entriesOf`
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V : Any, T : Map<out K, V?>> Expect<T>.containsOnlyEntriesOf(
    mapLike: MapLike
): Expect<T> = it contains o inAny order but only entriesOf mapLike


/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key].
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, T : Map<out K, *>> Expect<T>.containsKey(key: K): Expect<T> =
    _logicAppend { containsKey(::identity, key) }

/**
 * Expects that the subject of the assertion (a [Map]) does not contain the given [key].
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, T : Map<out K, *>> Expect<T>.containsNotKey(key: K): Expect<T> =
    _logicAppend { containsNotKey(::identity, key) }

/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key],
 * creates an [Expect] for the corresponding value and returns the newly created assertion container,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K): Expect<V> =
    _logic.getExisting(::identity, key).transform()

/**
 * Expects that the subject of the assertion (a [Map]) contains the given [key] and that
 * the corresponding value holds all assertions the given [KeyWithCreator.assertionCreator] creates for it.
 *
 * @param key Use the function `key(...) { ... }` to create a [KeyWithCreator] where the first parameter corresponds
 *  to the key and the second is the `assertionCreator`-lambda
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: KeyWithCreator<K, V>): Expect<T> =
    _logic.getExisting(::identity, key.key).collectAndAppend(key.assertionCreator)

/**
 * Helper function to create an [KeyWithCreator] based on the given [key] and [assertionCreator].
 */
fun <K, V> key(key: K, assertionCreator: Expect<V>.() -> Unit): KeyWithCreator<K, V> =
    KeyWithCreator(key, assertionCreator)


/**
 * Creates an [Expect] for the property [Map.keys] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <K, T : Map<out K, *>> Expect<T>.keys: Expect<Set<K>>
    get() = _logic.property(Map<out K, *>::keys).transform()

/**
 * Expects that the property [Map.keys] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.keys(assertionCreator: Expect<Set<K>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, *>::keys).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.values] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <V, T : Map<*, V>> Expect<T>.values: Expect<Collection<V>>
    get() = _logic.property(Map<out Any?, V>::values).transform()

/**
 * Expects that the property [Map.keys] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.values(assertionCreator: Expect<Collection<V>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, V>::values).collectAndAppend(assertionCreator)

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
): Expect<Set<Map.Entry<K, V>>> = _logic.changeSubject.unreported { it.entries }

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

//TODO move to mapCollectionLikeAssertions with 0.16.0
/**
 * Expects that the subject of the assertion (a [Map]) is an empty [Map].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <T : Map<*, *>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isEmpty(::toEntries) }

//TODO move to mapCollectionLikeAssertions with 0.16.0
/**
 * Expects that the subject of the assertion (a [Map]) is not an empty [Map].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <T : Map<*, *>> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isNotEmpty(::toEntries) }

//TODO remove with 0.16.0
private fun <T : Map<*, *>> toEntries(t: T): Collection<*> = t.entries
