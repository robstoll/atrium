package ch.tutteli.atrium.api.infix.en_GB.creating.map

import ch.tutteli.atrium.creating.Expect

/**
 * Parameter object to express a key/value [Pair] whose value type is a nullable lambda with an
 * [Expect] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Expect<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Expect<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String =
        "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}
