package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Parameter object to express `T, vararg T`.
 */
class All<out T>(override val expected: T, override vararg val otherExpected: T) : VarArgHelper<T>

/**
 * Wrapper for a single index -- can be used as distinguishable type for an overload where Int is already in use.
 */
data class Index(val index: Int)

data class Key<out K>(val key: K)

/**
 * Parameter object to express a key/value [Pair] whose value type is a lambda with an
 * [Expect] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Expect<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Expect<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String =
        "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}



/**
 * Parameter object to express `Pair<K, V>, vararg Pair<K, V>`.
 */
class Pairs<out K, out V>(
    override val expected: Pair<K, V>,
    override vararg val otherExpected: Pair<K, V>
) : VarArgHelper<Pair<K, V>>
