package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Expects that the property [Map.Entry.key] of the subject of `this` expectation
 * is equal to the given [key] and the property [Map.Entry.value] is equal to the given [value].
 *
 * Shortcut for `and { key { toEqual(key) }; value { toEqual(value) } }` where `and` denotes an
 * expectation-group.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.MapEntryAssertionSamples.isKeyValue
 */
@Deprecated("Use toEqualKeyValue; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEqualKeyValue<K, V, T>(key, value)"))
fun <K, V, T : Map.Entry<K, V>> Expect<T>.isKeyValue(key: K, value: V): Expect<T> =
    _logicAppend { isKeyValue(key, value) }
