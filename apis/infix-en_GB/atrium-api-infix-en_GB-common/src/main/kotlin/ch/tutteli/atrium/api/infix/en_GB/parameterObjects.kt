@file:Suppress("DEPRECATION" /* TODO remove suppress with 1.0.0 */)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper
import ch.tutteli.kbox.glue

/**
 * Parameter object to express `T, vararg T`.
 */
class All<out T>(override val expected: T, override vararg val otherExpected: T) : VarArgHelper<T>

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
) : GroupWithoutNullableEntries<(Expect<T>.() -> Unit)?>, GroupWithNullableEntries<(Expect<T>.() -> Unit)?> {
    override fun toList(): List<(Expect<T>.() -> Unit)?> = listOf(assertionCreatorOrNull)
}

/**
 * Parameter object to express a [Group] of identification lambdas.
 *
 * It is also used to express `(Expect<T>.() -> Unit)?, vararg (Expect<T>.() -> Unit)?` at other places the infix-api.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class Entries<T>(
    val assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?
) : GroupWithoutNullableEntries<(Expect<T>.() -> Unit)?>, GroupWithNullableEntries<(Expect<T>.() -> Unit)?>,
    VarArgHelper<(Expect<T>.() -> Unit)?> {
    override val expected get() = assertionCreatorOrNull
    override val otherExpected get() = otherAssertionCreatorsOrNulls

    override fun toList(): List<(Expect<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}

/**
 * Parameter object to express a key/value [Pair] whose value type is a nullable lambda with an
 * [Expect] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Expect<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Expect<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String =
        "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}

//TODO #63 move to iterable and introduce function instead
/**
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>` in the infix-api.
 *
 * Notice, most probably the type parameter G will be removed in the future, will be fixed to [Group].
 */
class Order<out T, out G : Group<T>>(
    val firstGroup: G,
    val secondGroup: G,
    vararg val otherExpectedGroups: G
)

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
 * Represents a [Group] with a single value.
 */
data class Value<T>(val expected: T) : GroupWithNullableEntries<T>, GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected)
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
