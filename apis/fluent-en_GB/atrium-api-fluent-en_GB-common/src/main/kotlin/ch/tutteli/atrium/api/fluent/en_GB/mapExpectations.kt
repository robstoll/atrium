package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.kbox.identity

/**
 * Starts a sophisticated `toContain` expectation building process based on this [Expect].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainBuilder
 *
 * @since 0.17.0
 */
val <K, V, T : Map<out K, V>> Expect<T>.toContain: MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour>
    get() = _logic.builderContainsInMapLike(::identity)


/**
 * Expects that the subject of `this` expectation (a [Map]) contains a key as defined by [keyValuePair]'s [Pair.first]
 * with a corresponding value as defined by [keyValuePair]'s [Pair.second] -- optionally the same assertions
 * are created for the [otherPairs].
 *
 * Delegates to `toContain.inAnyOrder.entries(keyValuePair, *otherPairs)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and [keyValuePair] is
 * defined as `'a' to 1` and one of the [otherPairs] is defined as `'a' to 1` as well, then both match,
 * even though they match the same entry.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainPair
 *
 * @since 0.17.0
 */
fun <K, V, T : Map<out K, V>> Expect<T>.toContain(
    keyValuePair: Pair<K, V>,
    vararg otherPairs: Pair<K, V>
): Expect<T> = toContain.inAnyOrder.entries(keyValuePair, *otherPairs)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only (in any order) a key as defined by
 * [keyValuePair]'s [Pair.first] with a corresponding value as defined by [keyValuePair]'s [Pair.second] -- optionally
 * the same assertions are created for the [otherPairs].
 *
 * Delegates to `toContain.inAnyOrder.only.entries(keyValuePair, *otherPairs)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainOnlyPair
 *
 * @since 0.17.0
 */
fun <K, V, T : Map<out K, V>> Expect<T>.toContainOnly(
    keyValuePair: Pair<K, V>,
    vararg otherPairs: Pair<K, V>
): Expect<T> = toContain.inAnyOrder.only.entries(keyValuePair, *otherPairs)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains a key as defined by [keyValue]'s [KeyValue.key]
 * with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`
 * -- optionally the same assertions are created for the [otherKeyValues].
 *
 * Delegates to `toContain.inAnyOrder.entries(keyValue, *otherKeyValues)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the map is `mapOf('a' to 1)` and [keyValue] is
 * defined as `Key('a') { isGreaterThan(0) }` and one of the [otherKeyValues] is defined as `Key('a') { isLessThan(2) }`
 * , then both match, even though they match the same entry.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainKeyValue
 *
 * @since 0.17.0
 */
inline fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.toContain(
    keyValue: KeyValue<K, V>,
    vararg otherKeyValues: KeyValue<K, V>
): Expect<T> = toContain.inAnyOrder.entries(keyValue, *otherKeyValues)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only (in any order) a key as defined by
 * [keyValue]'s [KeyValue.key] with a corresponding value which either holds all assertions [keyValue]'s
 * [KeyValue.valueAssertionCreatorOrNull] creates or needs to be `null` in case
 * [KeyValue.valueAssertionCreatorOrNull] is defined as `null`
 * -- optionally the same assertions are created for the [otherKeyValues].
 *
 * Delegates to `toContain.inAnyOrder.only.entries(keyValue, *otherKeyValues)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainOnlyKeyValue
 *
 * @since 0.17.0
 */
inline fun <K, reified V : Any, T : Map<out K, V?>> Expect<T>.toContainOnly(
    keyValue: KeyValue<K, V>,
    vararg otherKeyValues: KeyValue<K, V>
): Expect<T> = toContain.inAnyOrder.only.entries(keyValue, *otherKeyValues)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the key-value pairs of the given [mapLike].
 *
 * Delegates to ` toContain.inAnyOrder.entriesOf`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainEntriesOf
 *
 * @since 0.17.0
 */
fun <K, V : Any, T : Map<out K, V?>> Expect<T>.toContainEntriesOf(
    mapLike: MapLike
): Expect<T> = toContain.inAnyOrder.entriesOf(mapLike)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains only (in any order) the key-value pairs of
 * the given [mapLike].
 *
 * Delegates to `toContain.inAnyOrder.only.entriesOf`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainOnlyEntriesOf
 *
 * @since 0.17.0
 */
fun <K, V : Any, T : Map<out K, V?>> Expect<T>.toContainOnlyEntriesOf(
    mapLike: MapLike
): Expect<T> = toContain.inAnyOrder.only.entriesOf(mapLike)

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toContainKey
 *
 * @since 0.17.0
 */
fun <K, T : Map<out K, *>> Expect<T>.toContainKey(key: K): Expect<T> =
    _logicAppend { containsKey(::identity, key) }

/**
 * Expects that the subject of `this` expectation (a [Map]) does not contain the given [key].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.notToContainKey
 *
 * @since 0.17.0
 */
fun <K, T : Map<out K, *>> Expect<T>.notToContainKey(key: K): Expect<T> =
    _logicAppend { containsNotKey(::identity, key) }

/**
 * Expects that the subject of `this` expectation (a [Map]) is an empty [Map].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.toBeEmpty
 *
 * @since 0.17.0
 */
fun <T : Map<*, *>> Expect<T>.toBeEmpty(): Expect<T> =
    _logicAppend { isEmpty(::toEntries) }

/**
 * Expects that the subject of `this` expectation (a [Map]) is not an empty [Map].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.notToBeEmpty
 *
 * @since 0.17.0
 */
fun <T : Map<*, *>> Expect<T>.notToBeEmpty(): Expect<T> =
    _logicAppend { isNotEmpty(::toEntries) }

private fun <T : Map<*, *>> toEntries(t: T): Collection<*> = t.entries

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key],
 * creates an [Expect] for the corresponding value and returns the newly created [Expect],
 * so that further fluent calls are expectations about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.getExistingFeature
 */
fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K): Expect<V> =
    _logic.getExisting(::identity, key).transform()

/**
 * Expects that the subject of `this` expectation (a [Map]) contains the given [key] and that
 * the corresponding value holds all assertions the given [assertionCreator] creates for it.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.getExisting
 */
fun <K, V, T : Map<out K, V>> Expect<T>.getExisting(key: K, assertionCreator: Expect<V>.() -> Unit): Expect<T> =
    _logic.getExisting(::identity, key).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.keys] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.keysFeature
 */
val <K, T : Map<out K, *>> Expect<T>.keys: Expect<Set<K>>
    get() = _logic.property(Map<out K, *>::keys).transform()

/**
 * Expects that the property [Map.keys] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.keys
 */
fun <K, V, T : Map<out K, V>> Expect<T>.keys(assertionCreator: Expect<Set<K>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, *>::keys).collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Map.values] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.valuesFeature
 */
val <V, T : Map<*, V>> Expect<T>.values: Expect<Collection<V>>
    get() = _logic.property(Map<out Any?, V>::values).transform()

/**
 * Expects that the property [Map.keys] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.values
 */
fun <K, V, T : Map<out K, V>> Expect<T>.values(assertionCreator: Expect<Collection<V>>.() -> Unit): Expect<T> =
    _logic.property(Map<out K, V>::values).collectAndAppend(assertionCreator)

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.asEntriesFeature
 */
fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(): Expect<Set<Map.Entry<K, V>>> =
    _logic.changeSubject.unreported { it.entries }

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>` and expects that it holds all assertions the given
 * [assertionCreator] creates for it.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapExpectationSamples.asEntries
 */
fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(
    assertionCreator: Expect<Set<Map.Entry<K, V>>>.() -> Unit
): Expect<T> = apply { asEntries()._logic.appendAsGroup(assertionCreator) }
