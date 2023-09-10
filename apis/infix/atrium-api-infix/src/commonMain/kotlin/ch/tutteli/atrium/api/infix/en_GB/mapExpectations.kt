package ch.tutteli.atrium.api.infix.en_GB

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
 * Starts a sophisticated `toContain` assertion building process based on this [Expect].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainBuilder
 *
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.toContain(
    @Suppress("UNUSED_PARAMETER") o: o
): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> = _logic.builderContainsInMapLike(::identity)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'it toContain o inAny order entry keyValuePair'.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainPair
 *
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.toContain(keyValuePair: Pair<K, V>): Expect<T> =
    it toContain o inAny order entry keyValuePair

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only one entry with a key as defined by
 * [keyValuePair]'s [Pair.first] and a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'it toContain o inAny order but only entry keyValuePair'.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyPair
 *
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.toContainOnly(keyValuePair: Pair<K, V>): Expect<T> =
    it toContain o inAny order but only entry keyValuePair

/**
 * Expects the subject of `this` expectation (a [Map]) contains for each key-value pair in [pairs],
 * a key as defined by that entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * Delegates to `it toContain o inAny order the pairs`
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one of the [Pair]
 * in [pairs] is defined as `'a' to 1` and another one is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @param pairs The key-value [Pairs] expected to be contained within this [Map]
 *   -- use the function `pairs(x to y, ...)` to create a [Pairs].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainPairs
 *
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.toContain(pairs: Pairs<K, V>): Expect<T> =
    it toContain o inAny order the pairs

/**
 * Expects the subject of `this` expectation (a [Map]) contains only (in any order) for each key-value pair in [pairs],
 * a key as defined by that entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * Delegates to `it toContain o inAny order but only the pairs`
 *
 * @param pairs The key-value [Pairs] expected to be contained within this [Map]
 *   -- use the function `pairs(x to y, ...)` to create a [Pairs].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyPairs
 *
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.toContainOnly(pairs: Pairs<K, V>): Expect<T> =
    it toContain o inAny order but only the pairs

/**
 * Expects that the subject of `this` expectation (a [Map]) contains a key as defined by [keyValue]'s [KeyWithValueCreator.key]
 * with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it toContain o inAny order keyValue keyValue`
 *
 * @param keyValue The [KeyWithValueCreator] whose key is expected to be contained within this [Map] and
 *   where the corresponding value holds all assertions the  [KeyWithValueCreator.valueAssertionCreatorOrNull] creates
 *   or needs to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *   -- use the function `keyValue(x) { ... }` to create a [KeyWithValueCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainKeyValue
 *
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.toContain(keyValue: KeyWithValueCreator<K, V>): Expect<T> =
    it toContain o inAny order entry keyValue

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only one entry with a key as defined by
 * [keyValue]'s [KeyWithValueCreator.key] with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it toContain o inAny order but only entry keyValue`
 *
 * @param keyValue The [KeyWithValueCreator] whose key is expected to be contained within this [Map] and
 *   where the corresponding value holds all assertions the  [KeyWithValueCreator.valueAssertionCreatorOrNull] creates
 *   or needs to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *   -- use the function `keyValue(x) { ... }` to create a [KeyWithValueCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyKeyValue
 *
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.toContainOnly(keyValue: KeyWithValueCreator<K, V>): Expect<T> =
    it toContain o inAny order but only entry keyValue

/**
 * Expects that the subject of `this` expectation (a [Map]) contains for each [KeyWithValueCreator] in [keyValues],
 * a key as defined by [KeyWithValueCreator.key] with a corresponding value which either holds all
 * assertions [KeyWithValueCreator]'s [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs
 * to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it toContain o inAny order the keyValues`
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and
 * one [KeyWithValueCreator] in [keyValues] is defined as `Key('a') { isGreaterThan(0) }` and
 * another one is defined as `Key('a') { isLessThan(2) }`, then both match, even though they match the same entry.
 *
 * @param keyValues The [KeyWithValueCreator]s -- use the function
 *   `keyValues(keyValue(key1) { ... }, keyValue(key2) { ... }, ...)` to create a [KeyValues].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainKeyValues
 *
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.toContain(
    keyValues: KeyValues<K, V>
): Expect<T> = it toContain o inAny order the entries(keyValues.expected, *keyValues.otherExpected)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only (in any order) for each [KeyWithValueCreator]
 * in [keyValues], a key as defined by [KeyWithValueCreator.key] with a corresponding value which either holds all
 * assertions [KeyWithValueCreator]'s [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs
 * to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it toContain o inAny order but only the keyValues`
 *
 * @param keyValues The [KeyWithValueCreator]s -- use the function
 *   `keyValues(keyValue(key1) { ... }, keyValue(key2) { ... }, ...)` to create a [KeyValues].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyKeyValues
 *
 */
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.toContainOnly(
    keyValues: KeyValues<K, V>
): Expect<T> = it toContain o inAny order but only the entries(keyValues.expected, *keyValues.otherExpected)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the key-value pairs of the given [mapLike].
 *
 * Delegates to `it toContain o inAny order entriesOf`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainEntriesOf
 *
 */
infix fun <K, V : Any, T : Map<out K, V?>> Expect<T>.toContainEntriesOf(
    mapLike: MapLike
): Expect<T> = it toContain o inAny order entriesOf mapLike

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only (in any order) the key-value pairs of
 * the given [mapLike].
 *
 * Delegates to `it toContain o inAny order but only entriesOf`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyEntriesOf
 *
 */
infix fun <K, V : Any, T : Map<out K, V?>> Expect<T>.toContainOnlyEntriesOf(
    mapLike: MapLike
): Expect<T> = it toContain o inAny order but only entriesOf mapLike


/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainKey
 *
 */
infix fun <K, T : Map<out K, *>> Expect<T>.toContainKey(key: K): Expect<T> =
    _logicAppend { containsKey(::identity, key) }

/**
 * Expects that the subject of `this` expectation (a [Map]) does not contain the given [key].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.notToContainKey
 */
infix fun <K, T : Map<out K, *>> Expect<T>.notToContainKey(key: K): Expect<T> =
    _logicAppend { containsNotKey(::identity, key) }

/**
 * Expects that the subject of `this` expectation (a [Map]) is an empty [Map].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toBeEmpty
 *
 */
infix fun <T : Map<*, *>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isEmpty(::toEntries) }

/**
 * Expects that the subject of `this` expectation (a [Map]) is not an empty [Map].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.notToBeEmpty
 *
 */
infix fun <T : Map<*, *>> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isNotEmpty(::toEntries) }

private fun <T : Map<*, *>> toEntries(t: T): Collection<*> = t.entries
