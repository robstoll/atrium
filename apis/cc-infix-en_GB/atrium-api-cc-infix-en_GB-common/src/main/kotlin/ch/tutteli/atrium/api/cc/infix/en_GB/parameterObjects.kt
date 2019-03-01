@file:JvmMultifileClass
@file:JvmName("ParameterObjectsKt")
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.*
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName


/**
 * Parameter object to express `T, vararg T` in the infix-api.
 */
class All<T>(override val expected: T, override vararg val otherExpected: T) : VarArgHelper<T>


/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   `assertionCreatorOrNull` is defined as `null`.
 */
class Entry<T: Any, A: ((Assert<T>) -> Unit)?>(
    val assertionCreatorOrNull: A
): GroupWithoutNullableEntries<A>, GroupWithNullableEntries<A> {
    override fun toList(): List<A> = listOf(assertionCreatorOrNull)
}

/**
 * Parameter object to express a [Group] of nullable identification lambdas.
 *
 * It is also used to express `(Assert<T>.() -> Unit)?, vararg (Assert<T>.() -> Unit)?` at other places the infix-api.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   `assertionCreatorOrNull` is defined as `null`.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class Entries<T : Any, A: ((Assert<T>) -> Unit)?>(
    val assertionCreatorOrNull: A,
    vararg val otherAssertionCreatorsOrNulls: A
) : GroupWithoutNullableEntries<A>, GroupWithNullableEntries<A>, VarArgHelper<(Assert<T>.() -> Unit)?> {
    override val expected get() = assertionCreatorOrNull
    override val otherExpected get() = otherAssertionCreatorsOrNulls

    override fun toList(): List<A> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}

/**
 * Wrapper for a single index -- can be used as distinguishable type for an overload where Int is already in use.
 */
data class Index(val index: Int)

/**
 * Wrapper for a single key -- can be used as distinguishable type for an overload where [K] is already in use.
 */
data class Key<out K>(val key: K)

/**
 * Parameter object to express a key/value [Pair] whose value type is a nullable lambda with an
 * [Assert][AssertionPlant] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyValue<out K, V : Any, A: ((Assert<V>) -> Unit)?>(val key: K, val valueAssertionCreatorOrNull: A) {
    fun toPair(): Pair<K, A> = key to valueAssertionCreatorOrNull
    override fun toString(): String
        = "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}


/**
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>` in the infix-api.
 *
 * Notice, most probably the type parameter G will be removed in the future, will be fixed to [Group].
 */
class Order<T, out G : Group<T>>(
    val firstGroup: G,
    val secondGroup: G,
    vararg val otherExpectedGroups: G
)

/**
 * Parameter object to express `Pair<K, V>, vararg Pair<K, V>` in the infix-api.
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
 */
class Values<T>(
    val expected: T,
    vararg val otherExpected: T
) : GroupWithoutNullableEntries<T>, GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected, *otherExpected)
}
