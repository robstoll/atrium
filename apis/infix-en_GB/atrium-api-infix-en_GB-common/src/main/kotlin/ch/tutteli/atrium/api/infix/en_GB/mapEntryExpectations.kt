package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Expects that the property [Map.Entry.key] of the subject of `this` expectation
 * is equal to the given [key] and the property [Map.Entry.value] is equal to the given [value].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <K, V, T : Map.Entry<K, V>> Expect<T>.toEqualKeyValue(keyValuePair: Pair<K, V>): Expect<T> =
    _logicAppend { isKeyValue(keyValuePair.first, keyValuePair.second) }
