@file:JvmMultifileClass
@file:JvmName("ParameterObjectsKt")
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Parameter object to express a [Group] with a single nullable identification lambda.
 *
 * In case `null` is used for the identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   [assertionCreator] is defined as `null`.
 */
class Eintrag<T : Any, A: ((Assert<T>) -> Unit)?>(
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
class Eintraege<T : Any, A: ((Assert<T>) -> Unit)?>(
    val assertionCreatorOrNull: A,
    vararg val otherAssertionCreatorsOrNulls: A
) : GroupWithoutNullableEntries<A>, GroupWithNullableEntries<A> {
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
 * Represents a [GroupWithoutNullableEntries] with a single value.
 */
data class Wert<T>(val expected: T) : GroupWithNullableEntries<T>, GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [GroupWithoutNullableEntries] of multiple values.
 */
class Werte<T>(
    val expected: T,
    vararg val otherExpected: T
) : GroupWithoutNullableEntries<T>, GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected, *otherExpected)
}
