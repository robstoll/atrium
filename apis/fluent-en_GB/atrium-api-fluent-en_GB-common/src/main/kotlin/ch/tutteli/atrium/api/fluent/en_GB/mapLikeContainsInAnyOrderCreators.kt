package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyValuePairsInAnyOrder
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyWithValueAssertionsInAnyOrder
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.utils.toVarArgPairs
import ch.tutteli.kbox.glue

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain the given [keyValuePair].
 *
 * Delegates to [entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.entry(keyValuePair: Pair<K, V>): Expect<T> =
    entries(keyValuePair)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain the given [keyValuePair] as well as the [otherPairs] where it does not matter
 * in which order they appear.
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and [keyValuePair] is
 * defined as `'a' to 1` and one of the [otherPairs] is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.entries(
    keyValuePair: Pair<K, V>,
    vararg otherPairs: Pair<K, V>
): Expect<T> = _logicAppend { keyValuePairsInAnyOrder(keyValuePair glue otherPairs) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain an entry with a key as defined by [keyValue]'s [KeyValue.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to [entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
inline fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderSearchBehaviour>.entry(
    keyValue: KeyValue<K, V>
): Expect<T> = entries(keyValue)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain the given [keyValue] as well as the [otherKeyValues] where it does not matter
 * in which order they appear -- an entry is contained if it has a key as defined by [keyValue]'s [KeyValue.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and [keyValue] is
 * defined as `Key('a') { isGreaterThan(0) }` and one of the [otherKeyValues] is defined as `Key('a') { isLessThan(2) }`
 * , then both match, even though they match the same entry.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
inline fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderSearchBehaviour>.entries(
    keyValue: KeyValue<K, V>,
    vararg otherKeyValues: KeyValue<K, V>
): Expect<T> = _logicAppend {
    keyWithValueAssertionsInAnyOrder(V::class, (keyValue glue otherKeyValues).map { it.toPair() })
}


/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain all entries of the given [expectedMapLike] where it does not matter
 * in which order they appear.
 *
 * Delegates to [entries] which also means that it does not search for unique matches
 * (see [entries] for more information).
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
inline fun <reified K, reified V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.entriesOf(
    expectedMapLike: MapLike
): Expect<T> = _logic.toVarArgPairs<K, V>(expectedMapLike).let { (first, rest) -> entries(first, *rest) }
