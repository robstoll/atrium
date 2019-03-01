@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 */
class Entry<T : Any, A: ((Assert<T>) -> Unit)?>(
    val assertionCreator: A
) : GroupWithoutNullableEntries<A>, GroupWithNullableEntries<A> {
    override fun toList(): List<A> = listOf(assertionCreator)
}

/**
 * Parameter object to express a [Group] of nullable identification lambdas.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   [assertionCreatorOrNull] is defined as `null`.
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
 * Parameter object to express a key/value [Pair] whose value type is a nullable lambda with an
 * [Assert][AssertionPlant] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyValue<out K, V : Any, A: ((Assert<V>) -> Unit)?>(val key: K, val valueAssertionCreatorOrNull: A) {
    fun toPair(): Pair<K, A> = key to valueAssertionCreatorOrNull
    override fun toString(): String
        = "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
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
