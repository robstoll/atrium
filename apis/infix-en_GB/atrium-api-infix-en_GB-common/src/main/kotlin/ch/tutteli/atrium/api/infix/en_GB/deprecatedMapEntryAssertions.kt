package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.isKeyValue
import ch.tutteli.atrium.logic.key
import ch.tutteli.atrium.logic.value

/**
 * Expects that the property [Map.Entry.key] of the subject of `this` expectation
 * is equal to the given [key] and the property [Map.Entry.value] is equal to the given [value].
 *
 * Kind of a shortcut for `and { key { it toEqual key }; value { it toEqual value } }` where `and` denotes an assertion group
 * block. Yet, the actual behaviour depends on implementation - could also be fail fast for instance or augment
 * reporting etc.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.MapEntryAssertionSamples.isKeyValue
 */
@Deprecated("Use toEqualKeyValue; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEqualKeyValue<K, V, T>(keyValuePair)"))
infix fun <K, V, T : Map.Entry<K, V>> Expect<T>.isKeyValue(keyValuePair: Pair<K, V>): Expect<T> =
    _logicAppend { isKeyValue(keyValuePair.first, keyValuePair.second) }
