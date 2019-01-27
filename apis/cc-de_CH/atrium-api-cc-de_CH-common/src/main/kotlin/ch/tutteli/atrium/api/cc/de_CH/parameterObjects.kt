package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [GroupWithoutNullableEntries] with a single identification lambda.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 */
class Eintrag<in T : Any>(
    val assertionCreator: Assert<T>.() -> Unit
) : GroupWithoutNullableEntries<Assert<T>.() -> Unit> {
    override fun toList(): List<Assert<T>.() -> Unit> = listOf(assertionCreator)
}

/**
 * Parameter object to express a [GroupWithNullableEntries] with a single nullable identification lambda.
 *
 * In case `null` is used for the identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   [assertionCreator] is defined as `null`.
 */
class NullableEintrag<in T : Any>(
    val assertionCreator: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = listOf(assertionCreator)
}


/**
 * Parameter object to express a [GroupWithoutNullableEntries] of identification lambdas.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 * @param otherAssertionCreators A variable amount of additional identification lambdas.
 */
class Eintraege<in T : Any>(
    val assertionCreator: Assert<T>.() -> Unit,
    vararg val otherAssertionCreators: Assert<T>.() -> Unit
) : GroupWithoutNullableEntries<Assert<T>.() -> Unit> {
    override fun toList(): List<Assert<T>.() -> Unit> = assertionCreator glue otherAssertionCreators
}

/**
 * Parameter object to express a [GroupWithNullableEntries] of nullable identification lambdas.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   [assertionCreatorOrNull] is defined as `null`.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class NullableEintraege<in T : Any>(
    val assertionCreatorOrNull: (Assert<T>.() -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}


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
        = "KeyNullableValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}


/**
 * Represents a [GroupWithoutNullableEntries] with a single value.
 */
data class Wert<T : Any>(val expected: T) : GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [GroupWithNullableEntries] with a single nullable value.
 */
data class NullableWert<T : Any?>(val expected: T) : GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected)
}


/**
 * Represents a [GroupWithoutNullableEntries] of multiple values.
 */
class Werte<T : Any>(private val expected: T, vararg val otherExpected: T) : GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected, *otherExpected)
}


/**
 * Represents a [GroupWithNullableEntries] of multiple nullable values.
 */
class NullableWerte<T : Any?>(private val expected: T, vararg val otherExpected: T) : GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected, *otherExpected)
}
