package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Pairs
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValues
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyValuePairsInOrderOnly
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyWithValueAssertionsInOrderOnly
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.creating.typeutils.MapLikeToIterablePairTransformer
import ch.tutteli.atrium.logic.utils.toVarArgPairs
import kotlin.reflect.KClass

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry where key and value have to equal the given [keyValuePair].
 *
 * Delegates to `the pairs(keyValuePair)`.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entry(keyValuePair: Pair<K, V>): Expect<T> =
    this the pairs(keyValuePair)

// TODO 0.16.0 implement https://github.com/robstoll/atrium/issues/292 for this one as well
/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValuePairs] in the specified order.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.the(
    keyValuePairs: Pairs<K, V>
): Expect<T> = _logicAppend { keyValuePairsInOrderOnly(keyValuePairs.toList()) }


/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry with a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the keyValues(keyValue)`.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @since 0.15.0
 */
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.entry(
    keyValue: KeyWithValueCreator<K, V>
): Expect<T> = this the keyValues(keyValue)

// TODO 0.16.0 implement https://github.com/robstoll/atrium/issues/292 for this one as well
/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValues] in the specified order -- an entry
 * is contained if it has a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @since 0.15.0
 */
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.the(
    keyValues: KeyValues<K, V>
): Expect<T> = entries(V::class, keyValues.toList())

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <K, V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.entries(
    kClass: KClass<V>,
    keyValues: List<KeyWithValueCreator<K, V>>
): Expect<T> = _logicAppend {
    keyWithValueAssertionsInOrderOnly(kClass, keyValues.map { it.toPair() })
}

// TODO 0.16.0 implement https://github.com/robstoll/atrium/issues/292 for this one as well
/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only and all entries of the given [expectedMapLike] in the specified order.
 *
 * Delegates to [keyValue].
 *
 * Notice that a runtime check applies which assures that only [Map] and [IterableLike]
 * (i.e. [Iterable], [Sequence] or one of the [Array] types) are passed (this can be changed via
 * [MapLikeToIterablePairTransformer] and [IterableLikeToIterableTransformer]).
 * This function expects [MapLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws IllegalArgumentException in case [expectedMapLike] is not
 *   a [Map], [Sequence] or one of the [Array] types
 *   or the given [expectedMapLike] does not have elements (is empty).
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entriesOf(
    expectedMapLike: MapLike
): Expect<T> = _logic.toVarArgPairs<K, V>(expectedMapLike).let { (first, rest) ->
    this the pairs(first, *rest)
}

