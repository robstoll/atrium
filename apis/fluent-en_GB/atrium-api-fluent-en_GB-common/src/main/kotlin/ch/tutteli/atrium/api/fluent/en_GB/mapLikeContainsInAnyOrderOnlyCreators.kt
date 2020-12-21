package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyValuePairsInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyWithValueAssertionsInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.utils.toVarArgPairs
import ch.tutteli.kbox.glue

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry where key and value have to equal the given [keyValuePair].
 *
 * Delegates to [entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderOnlySearchBehaviour>.entry(keyValuePair: Pair<K, V>): Expect<T> =
    entries(keyValuePair)

// TODO already implement https://github.com/robstoll/atrium/issues/292 for this one in 0.15.0
/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValuePair] as well as the [otherPairs] where it does not matter
 * in which order they appear.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
fun <K, V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderOnlySearchBehaviour>.entries(
    keyValuePair: Pair<K, V>,
    vararg otherPairs: Pair<K, V>
): Expect<T> = _logicAppend { keyValuePairsInAnyOrderOnly(keyValuePair glue otherPairs) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry with a key as defined by [keyValue]'s [KeyValue.key] and
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
inline fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderOnlySearchBehaviour>.entry(
    keyValue: KeyValue<K, V>
): Expect<T> = entries(keyValue)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValue] as well as the [otherKeyValues] where it does not matter
 * in which order they appear -- an entry is contained if it has a key as defined by [keyValue]'s [KeyValue.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
inline fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InAnyOrderOnlySearchBehaviour>.entries(
    keyValue: KeyValue<K, V>,
    vararg otherKeyValues: KeyValue<K, V>
): Expect<T> = _logicAppend {
    keyWithValueAssertionsInAnyOrderOnly(V::class, (keyValue glue otherKeyValues).map { it.toPair() })
}


/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only and all entries of the given [expectedMapLike] where it does not matter
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
inline fun <reified K, reified V, T : MapLike> EntryPointStep<K, V, T, InAnyOrderOnlySearchBehaviour>.entriesOf(
    expectedMapLike: MapLike
): Expect<T> = _logic.toVarArgPairs<K, V>(expectedMapLike).let { (first, rest) -> entries(first, *rest) }
