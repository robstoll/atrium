@file:Suppress("DEPRECATION" /** TODO remove suppress with 1.0.0 */)
package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Parameter object to express `T, vararg T`.
 */
class All<out T>(override val expected: T, override vararg val otherExpected: T) : VarArgHelper<T>

/**
 * Parameter object to express a key/value [Pair] whose value type is a nullable lambda with an
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

/**
 * Parameter object to express `String, vararg String` in the infix-api.
 */
class RegexPatterns(pattern: String, vararg otherPatterns: String) : VarArgHelper<String> {
    override val expected = pattern
    override val otherExpected = otherPatterns
}

/**
 * Represents a [Group] of multiple values.
 *
 * Note, [Values] will be made invariant once Kotlin 1.4 is out and Atrium depends on it (most likely with 1.0.0)
 */
//TODO remove `out` with Kotlin 1.4 (most likely with Atrium 1.0.0)
class Values<out T>(
    override val expected: T,
    override vararg val otherExpected: T
) : GroupWithoutNullableEntries<T>, GroupWithNullableEntries<T>, VarArgHelper<T> {
    override fun toList() = listOf(expected, *otherExpected)
}
