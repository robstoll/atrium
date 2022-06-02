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
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain<K, V, T>(o)"))
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(
    @Suppress("UNUSED_PARAMETER") o: o
): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> = _logic.builderContainsInMapLike(::identity)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'it contains o inAny order entry keyValuePair'.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<K, V, T>(keyValuePair)")
)
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(keyValuePair: Pair<K, V>): Expect<T> =
    it toContain o inAny order entry keyValuePair

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only one entry with a key as defined by
 * [keyValuePair]'s [Pair.first] and a corresponding value as defined by [keyValuePair]'s [Pair.second]
 *
 * Delegates to 'it contains o inAny order but only entry keyValuePair'.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainOnly; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainOnly<K, V, T>(keyValuePair)")
)
infix fun <K, V, T : Map<out K, V>> Expect<T>.containsOnly(keyValuePair: Pair<K, V>): Expect<T> =
    it toContain o inAny order but only entry keyValuePair

/**
 * Expects the subject of `this` expectation (a [Map]) contains for each entry in [keyValuePairs],
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
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<K, V, T>(keyValuePairs)")
)
infix fun <K, V, T : Map<out K, V>> Expect<T>.contains(keyValuePairs: Pairs<K, V>): Expect<T> =
    it toContain o inAny order the keyValuePairs

/**
 * Expects the subject of `this` expectation (a [Map]) contains only (in any order) for each entry in [keyValuePairs],
 * a key as defined by that entry's [Pair.first] with a corresponding value as defined by entry's [Pair.second].
 *
 * Delegates to `it contains o inAny order but only the keyValuePairs`
 *
 * @param keyValuePairs The key-value [Pairs] expected to be contained within this [Map]
 *   -- use the function `pairs(x to y, ...)` to create a [Pairs].
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainOnly; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainOnly<K, V, T>(keyValuePairs)")
)
infix fun <K, V, T : Map<out K, V>> Expect<T>.containsOnly(keyValuePairs: Pairs<K, V>): Expect<T> =
    it toContain o inAny order but only the keyValuePairs

/**
 * Expects that the subject of `this` expectation (a [Map]) contains a key as defined by [keyValue]'s [KeyWithValueCreator.key]
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
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<K, V, T>(keyValue)")
)
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(keyValue: KeyWithValueCreator<K, V>): Expect<T> =
    it toContain o inAny order entry keyValue

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only one entry with a key as defined by
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
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainOnly; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainOnly<K, V, T>(keyValue)")
)
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.containsOnly(keyValue: KeyWithValueCreator<K, V>): Expect<T> =
    it toContain o inAny order but only entry keyValue

/**
 * Expects that the subject of `this` expectation (a [Map]) contains for each [KeyWithValueCreator] in [allKeyValues],
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
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<K, V, T>(allKeyValues)")
)
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(
    allKeyValues: KeyValues<K, V>
): Expect<T> = it toContain o inAny order the keyValues(allKeyValues.expected, *allKeyValues.otherExpected)

@Deprecated(
    "Use `toContain keyValues` instead; will be removed with 1.0.0 at the latest",
    ReplaceWith(
        "this toContain keyValues(all.expected, *all.otherExpected)",
        "ch.tutteli.atrium.api.infix.en_GB.toContain",
        "ch.tutteli.atrium.api.infix.en_GB.keyValues"
    )
)
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.contains(
    all: All<KeyWithValueCreator<K, V>>
): Expect<T> = it toContain keyValues(all.expected, *all.otherExpected)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only (in any order) for each [KeyWithValueCreator]
 * in [allKeyValues], a key as defined by [KeyWithValueCreator.key] with a corresponding value which either holds all
 * assertions [KeyWithValueCreator]'s [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs
 * to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *
 * Delegates to `it contains o inAny order but only the keyValues`
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainOnly; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainOnly<K, V, T>(allKeyValues)")
)
inline infix fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.containsOnly(
    allKeyValues: KeyValues<K, V>
): Expect<T> = it toContain o inAny order but only the keyValues(allKeyValues.expected, *allKeyValues.otherExpected)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the key-value pairs of the given [mapLike].
 *
 * Delegates to `it contains o inAny order entriesOf`
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainEntriesOf; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainEntriesOf<K, V, T>(mapLike)")
)
infix fun <K, V : Any, T : Map<out K, V?>> Expect<T>.containsEntriesOf(
    mapLike: MapLike
): Expect<T> = it toContain o inAny order entriesOf mapLike

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only (in any order) the key-value pairs of
 * the given [mapLike].
 *
 * Delegates to `it contains o inAny order but only entriesOf`
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainOnlyEntriesOf; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainOnlyEntriesOf<K, V, T>(mapLike)")
)
infix fun <K, V : Any, T : Map<out K, V?>> Expect<T>.containsOnlyEntriesOf(
    mapLike: MapLike
): Expect<T> = it toContain o inAny order but only entriesOf mapLike


/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key].
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainKey; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainKey<K, T>(key)")
)
infix fun <K, T : Map<out K, *>> Expect<T>.containsKey(key: K): Expect<T> =
    _logicAppend { containsKey(::identity, key) }

/**
 * Expects that the subject of `this` expectation (a [Map]) does not contain the given [key].
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use notToContainKey; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToContainKey<K, T>(key)")
)
infix fun <K, T : Map<out K, *>> Expect<T>.containsNotKey(key: K): Expect<T> =
    _logicAppend { containsNotKey(::identity, key) }
