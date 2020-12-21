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

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain the given [keyValuePair].
 *
 * Delegates to `the pairs(keyValuePair)`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.entry(keyValuePair: Pair<K, V>): Expect<T> =
    this the pairs(keyValuePair)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain the given key-value [pairs] where it does not matter
 * in which order they appear.
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and one of the [Pair]
 * in [pairs] is defined as `'a' to 1` and another one is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.the(
    pairs: Pairs<K, V>
): Expect<T> = _logicAppend { keyValuePairsInAnyOrder(pairs.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain an entry with a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the keyValues(keyValue)`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderSearchBehaviour>.entry(
    keyValue: KeyWithValueCreator<K, V>
): Expect<T> = this the keyValues(keyValue)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderSearchBehaviour>.the(
    keyValues: KeyValues<K, V>
): Expect<T> = _logicAppend {
    keyWithValueAssertionsInAnyOrder(V::class, keyValues.toList().map { it.toPair() })
}

/**
 * Helper function to create a [KeyValues] based on the given [keyValue] and [otherKeyValues]
 * -- allows to express `Pair<K, V>, vararg Pair<K, V>`.
 */
// TODO 1.0.0: consider to rename to entries once updated to Kotlin 1.4 maybe the type inference is better and there
// is no longer a clash with Iterable entries
fun <K, V : Any> keyValues(
    keyValue: KeyWithValueCreator<K, V>,
    vararg otherKeyValues: KeyWithValueCreator<K, V>
): KeyValues<K, V> = KeyValues(keyValue, otherKeyValues)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedMapLike] is not
 *   a [Map], [Sequence] or one of the [Array] types
 *   or the given [expectedMapLike] does not have elements (is empty).
 *
 * @since 0.15.0
 */
inline infix fun <reified K, reified V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.entriesOf(
    expectedMapLike: MapLike
): Expect<T> = _logic.toVarArgPairs<K, V>(expectedMapLike).let { (first, rest) ->
    this the pairs(first, *rest)
}
