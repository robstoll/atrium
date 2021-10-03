//TODO rename file to mapLikeToContain... in 0.18.0
package ch.tutteli.atrium.api.fluent.en_GB

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
import ch.tutteli.kbox.glue
import kotlin.reflect.KClass

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry where key and value have to equal the given [keyValuePair].
 *
 * Delegates to [entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entry(keyValuePair: Pair<K, V>): Expect<T> =
    entries(keyValuePair)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValuePair] as well as the [otherPairs] in the specified order.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entries(
    keyValuePair: Pair<K, V>,
    vararg otherPairs: Pair<K, V>
    //TODO 0.18.0 add the following
    //report: InOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend { keyValuePairsInOrderOnly(keyValuePair glue otherPairs, {}) }


/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry with a key as defined by [keyValue]'s [KeyValue.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to [entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
inline fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.entry(
    keyValue: KeyValue<K, V>
): Expect<T> = entries(keyValue)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValue] as well as the [otherKeyValues] in the specified order -- an entry
 * is contained if it has a key as defined by [keyValue]'s [KeyValue.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
inline fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.entries(
    keyValue: KeyValue<K, V>,
    vararg otherKeyValues: KeyValue<K, V>
    //TODO 0.18.0 add the following
    //report: InOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = entries(V::class, keyValue glue otherKeyValues)

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <K, V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.entries(
    kClass: KClass<V>,
    keyValues: List<KeyValue<K, V>>
    //TODO 0.18.0 add the following
    //report: InOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend {
    keyWithValueAssertionsInOrderOnly(kClass, keyValues.map { it.toPair() }, { })
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only and all entries of the given [expectedMapLike] in the specified order.
 *
 * Delegates to [entries].
 *
 * Notice that a runtime check applies which assures that only [Map] and [IterableLike]
 * (i.e. [Iterable], [Sequence] or one of the [Array] types) are passed (this can be changed via
 * [MapLikeToIterablePairTransformer] and [IterableLikeToIterableTransformer]).
 * This function expects [MapLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedMapLike] is not
 *   a [Map], [Sequence] or one of the [Array] types
 *   or the given [expectedMapLike] does not have elements (is empty).
 *
 * @since 0.15.0
 */
fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entriesOf(
    expectedMapLike: MapLike
    //TODO 0.18.0 add the following
    //report: InOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logic.toVarArgPairs<K, V>(expectedMapLike).let { (first, rest) -> entries(first, *rest) }

