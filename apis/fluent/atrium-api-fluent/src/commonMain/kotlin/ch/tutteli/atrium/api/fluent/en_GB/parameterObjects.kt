package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.utils.VarArgHelper
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * In case `null` is used for the identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 */
class Entry<T : Any>(
    val assertionCreatorOrNull: (Expect<T>.() -> Unit)?
) : Group<(Expect<T>.() -> Unit)?> {
    override fun toList(): List<(Expect<T>.() -> Unit)?> = listOf(assertionCreatorOrNull)
}

/**
 * Parameter object to express a [Group] of identification lambdas.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class Entries<T : Any>(
    val assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?
) : Group<(Expect<T>.() -> Unit)?>,
    VarArgHelper<(Expect<T>.() -> Unit)?> {
    override val expected: (Expect<T>.() -> Unit)? get() = assertionCreatorOrNull
    override val otherExpected: Array<out (Expect<T>.() -> Unit)?> get() = otherAssertionCreatorsOrNulls

    override fun toList(): List<(Expect<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}

/**
 * Parameter object to express a key/value [Pair] whose value type is a nullable lambda with an
 * [Expect] receiver, which means one can either pass a lambda or `null`.
 */
//TODO 2.0.0 remove data?
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Expect<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Expect<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull

    override fun toString(): String =
        "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}

/**
 * Represents a [Group] with a single value.
 */
//TODO 2.0.0 remove data?
data class Value<out T>(val expected: T) : Group<T> {
    override fun toList(): List<T> = listOf(expected)
}

/**
 * Represents a [Group] of multiple values.
 */
class Values<out T>(
    override val expected: T,
    override vararg val otherExpected: T
) : Group<T>, VarArgHelper<T> {
    override fun toList(): List<T> = listOf(expected, *otherExpected)
}
