package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Pairs
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValues
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyValuePairsInAnyOrder
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyWithValueAssertionsInAnyOrder
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.utils.toVarArgPairs
import kotlin.reflect.KClass

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (a [MapLike])
 * needs to contain the given [keyValuePair].
 *
 * Delegates to `the pairs(keyValuePair)`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.entry(keyValuePair: Pair<K, V>): Expect<T> =
    this the pairs(keyValuePair)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (a [MapLike])
 * needs to contain the given key-value [pairs] where it does not matter
 * in which order they appear.
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one of the [Pair]
 * in [pairs] is defined as `'a' to 1` and another one is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @param pairs The key-value [Pairs] expected to be contained within this [MapLike]
 *   -- use the function `pairs(x to y, ...)` to create a [Pairs].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.the(
    pairs: Pairs<K, V>
): Expect<T> = _logicAppend { keyValuePairsInAnyOrder(pairs.toList()) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (a [MapLike])
 * needs to contain an entry with a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the keyValues(keyValue)`.
 *
 * @param keyValue The [KeyWithValueCreator] whose key is expected to be contained within this [MapLike] and
 *   where the corresponding value holds all assertions the  [KeyWithValueCreator.valueAssertionCreatorOrNull] creates
 *   or needs to be `null` in case [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`
 *   -- use the function `keyValue(x) { ... }` to create a [KeyWithValueCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderSearchBehaviour>.entry(
    keyValue: KeyWithValueCreator<K, V>
): Expect<T> = this the entries(keyValue)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (a [MapLike])
 * needs to contain the given [keyValues] where it does not matter
 * in which order they appear -- an entry is contained if it has
 * a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one [KeyWithValueCreator] in
 * [keyValues] is defined as `Key('a') { isGreaterThan(0) }` and another one is defined as `Key('a') { isLessThan(2) }`
 * , then both match, even though they match the same entry.
 *
 * @param keyValues The [KeyWithValueCreator]s -- use the function
 *   `keyValues(keyValue(key1) { ... }, keyValue(key2) { ... }, ...)` to create a [KeyValues].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderSearchBehaviour>.the(
    keyValues: KeyValues<K, V>
): Expect<T> = entries(V::class, keyValues.toList())

/**
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapLikeToContainInAnyOrderCreatorSamples.entries
 */
@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <K, V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderSearchBehaviour>.entries(
    kClass: KClass<V>,
    keyValues: List<KeyWithValueCreator<K, V>>
): Expect<T> = _logicAppend {
    keyWithValueAssertionsInAnyOrder(kClass, keyValues.map { it.toPair() })
}

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (a [MapLike])
 * needs to contain all entries of the given [expectedMapLike] where it does not matter
 * in which order they appear.
 *
 * Delegates to [keyValue] which also means that it does not search for unique matches
 * (see [keyValue] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [MapLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedMapLike The [MapLike] whose elements are expected to be contained within this [MapLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedMapLike] is not
 *   a [Map], [Sequence] or one of the [Array] types
 *   or the given [expectedMapLike] does not have elements (is empty).
 *
 * @since 0.15.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapLikeToContainInAnyOrderCreatorSamples.entriesOf
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.entriesOf(
    expectedMapLike: MapLike
): Expect<T> = _logic.toVarArgPairs<K, V>(expectedMapLike).let { (first, rest) ->
    this the pairs(first, *rest)
}
