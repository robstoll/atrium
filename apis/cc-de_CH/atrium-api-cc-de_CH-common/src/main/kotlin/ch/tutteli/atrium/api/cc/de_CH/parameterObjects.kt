@file:JvmMultifileClass
@file:JvmName("ParameterObjectsKt")
@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * In case `null` is used for the identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   [assertionCreator] is defined as `null`.
 */
class Eintrag<in T : Any>(
    val assertionCreator: ((Assert<T>) -> Unit)?
) : GroupWithoutNullableEntries<((Assert<T>) -> Unit)?>, GroupWithNullableEntries<((Assert<T>) -> Unit)?> {
    override fun toList(): List<((Assert<T>) -> Unit)?> = listOf(assertionCreator)
}

/**
 * Parameter object to express a [Group] of identification lambdas.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   [assertionCreatorOrNull] is defined as `null`.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class Eintraege<in T : Any>(
    val assertionCreatorOrNull: ((Assert<T>) -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: ((Assert<T>) -> Unit)?
) : GroupWithoutNullableEntries<((Assert<T>) -> Unit)?>, GroupWithNullableEntries<((Assert<T>) -> Unit)?>, VarArgHelper<((Assert<T>) -> Unit)?> {
    override val expected get() = assertionCreatorOrNull
    override val otherExpected get() = otherAssertionCreatorsOrNulls

    override fun toList(): List<((Assert<T>) -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}


/**
 * Parameter object to express a key/value [Pair] whose value type is a lambda with an
 * [Assert][AssertionPlant] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Assert<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Assert<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String
        = "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}

/**
 * Represents a [GroupWithoutNullableEntries] with a single value.
 */
data class Wert<out T>(val expected: T) : GroupWithNullableEntries<T>, GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [GroupWithoutNullableEntries] of multiple values.
 */
class Werte<out T>(
    override val expected: T,
    override vararg val otherExpected: T
) : GroupWithoutNullableEntries<T>, GroupWithNullableEntries<T>, VarArgHelper<T> {
    override fun toList() = listOf(expected, *otherExpected)
}
