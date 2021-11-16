package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Pairs
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInOrderOnlyReportingOptions
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValues
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyValuePairsInOrderOnly
import ch.tutteli.atrium.logic.creating.maplike.contains.creators.keyWithValueAssertionsInOrderOnly
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.creating.typeutils.MapLikeToIterablePairTransformer
import ch.tutteli.atrium.logic.utils.toVarArgPairs
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry where key and value have to equal the given [keyValuePair].
 *
 * Delegates to `the pairs(keyValuePair)`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entry(keyValuePair: Pair<K, V>): Expect<T> =
    this the pairs(keyValuePair)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given key-value [pairs] in the specified order.
 *
 * @param pairs The key-value pairs which are expected to be contained within the [MapLike]
 *   -- use the function `pairs(x to y, ...)` to create a [Pairs].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.the(
    pairs: Pairs<K, V>
): Expect<T> = the(WithInOrderOnlyReportingOptions({}, pairs))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [pairs] in the specified order.
 *
 * @param pairs The key-value pairs which are expected to be contained within the [MapLike]
 *   plus a lambda configuring the [InOrderOnlyReportingOptions] -- use the function `pairs(t, ..., report = { ... })`
 *   to create a [WithInOrderOnlyReportingOptions] with a wrapped [Pairs].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.18.0
 */
@JvmName("thePairsWithReportingOption")
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.the(
    pairs: WithInOrderOnlyReportingOptions<Pairs<K, V>>
): Expect<T> = _logicAppend {
    keyValuePairsInOrderOnly(pairs.t.toList(), pairs.report)
}


/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain exactly one entry with a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
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
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.entry(
    keyValue: KeyWithValueCreator<K, V>
): Expect<T> = this the keyValues(keyValue)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValues] in the specified order -- an entry
 * is contained if it has a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * @param keyValues The [KeyWithValueCreator]s -- use the function
 *   `keyValues(keyValue(key1) { ... }, keyValue(key2) { ... }, ...)` to create a [KeyValues].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.the(
    keyValues: KeyValues<K, V>
): Expect<T> = the(WithInOrderOnlyReportingOptions({}, keyValues))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only the given [keyValues] in the specified order -- an entry
 * is contained if it has a key as defined by [keyValue]'s [KeyWithValueCreator.key] and
 * a corresponding value which either holds all assertions [keyValue]'s
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyWithValueCreator.valueAssertionCreatorOrNull] is defined as `null`.
 *
 * @param keyValues The [KeyWithValueCreator]s plus a lambda configuring the [InOrderOnlyReportingOptions]
 *   -- use the function `keyValues(keyValue(key1) { ... }, keyValue(key2) { ... }, ..., report = { ... })`
 *   to create a [WithInOrderOnlyReportingOptions] with a wrapped [KeyValues].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.18.0
 */
@JvmName("theKeyValuesWithReportingOption")
inline infix fun <K, reified V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.the(
    keyValues: WithInOrderOnlyReportingOptions<KeyValues<K, V>>
): Expect<T> = entries(V::class, keyValues)

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <K, V : Any, T : MapLike> EntryPointStep<K, out V?, T, InOrderOnlySearchBehaviour>.entries(
    kClass: KClass<V>,
    keyValues: WithInOrderOnlyReportingOptions<KeyValues<K, V>>
): Expect<T> = _logicAppend {
    keyWithValueAssertionsInOrderOnly(
        kClass,
        keyValues.t.toList().map { it.toPair() },
        keyValues.report
    )
}

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
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case the given [MapLike] is an unsupported type, or in other words,
 *   if the transformation from the given [MapLike] to `Iterable<Pair<K, V>>` fails,
 *   or it does not have elements (is empty).
 *
 * @since 0.15.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entriesOf(
    expectedMapLike: MapLike
): Expect<T> = entriesOf(WithInOrderOnlyReportingOptions({}, expectedMapLike))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (a [MapLike])
 * needs to contain only and all entries of the given [MapLike] in the specified order.
 *
 * Delegates to [keyValue].
 *
 * Notice that a runtime check applies which assures that only [Map] and [IterableLike]
 * (i.e. [Iterable], [Sequence] or one of the [Array] types) are passed (this can be changed via
 * [MapLikeToIterablePairTransformer] and [IterableLikeToIterableTransformer]).
 * This function expects [MapLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param entriesOf The [MapLike] whose elements are expected to be contained within
 *   this [MapLike] plus a lambda configuring the [InOrderOnlyReportingOptions] -- use the function
 *   `entriesOf(mapLike, report = { ... })` to create a [WithInOrderOnlyReportingOptions] with a wrapped [MapLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case the given [MapLike] is an unsupported type, or in other words,
 *   if the transformation from the given [MapLike] to `Iterable<Pair<K, V>>` fails,
 *   or it does not have elements (is empty).
 *
 * @since 0.18.0
 */
infix fun <K, V, T : MapLike> EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>.entriesOf(
    entriesOf: WithInOrderOnlyReportingOptions<MapLike>
): Expect<T> = _logic.toVarArgPairs<K, V>(entriesOf.t).let { (first, rest) ->
    this the pairs(first, *rest, report = entriesOf.report)
}
