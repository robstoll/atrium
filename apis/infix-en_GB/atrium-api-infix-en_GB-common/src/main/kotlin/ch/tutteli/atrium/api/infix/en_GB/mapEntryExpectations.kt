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

infix fun <K, T : Map.Entry<K, *>> Expect<T>.toEqualKey(key: K): Expect<T> =
    _logicAppend { isKey(key) }

infix fun <V, T : Map.Entry<*, V>> Expect<T>.toEqualValue(value: V): Expect<T> =
    _logicAppend { isValue(value) }
