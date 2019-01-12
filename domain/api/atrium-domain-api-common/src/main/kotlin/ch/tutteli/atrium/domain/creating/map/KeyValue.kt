package ch.tutteli.atrium.domain.creating.map

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Parameter object to express a key/value [Pair] whose value type is a lambda with an
 * [Assert][AssertionPlant] receiver.
 */
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreator: Assert<V>.() -> Unit) {
    fun toPair(): Pair<K, Assert<V>.() -> Unit> = key to valueAssertionCreator
    override fun toString(): String = "KeyValue(key=$key)"
}

/**
 * Parameter object to express a key/value [Pair] whose value type is a nullable lambda with an
 * [Assert][AssertionPlant] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyNullableValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Assert<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Assert<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String
        = "KeyNullableValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"}"
}
