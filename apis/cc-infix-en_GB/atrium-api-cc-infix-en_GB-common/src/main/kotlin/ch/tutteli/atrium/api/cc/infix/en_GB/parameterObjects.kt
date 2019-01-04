package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 */
class Entry<in T : Any>(
    val assertionCreator: Assert<T>.() -> Unit
) : GroupWithoutNullableEntries<Assert<T>.() -> Unit> {
    override fun toList(): List<Assert<T>.() -> Unit> = listOf(assertionCreator)
}

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
class NullableEntry<in T : Any>(
    val assertionCreator: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = listOf(assertionCreator)
}

/**
 * Parameter object to express a [GroupWithoutNullableEntries] of identification lambdas.
 *
 * It is also used to express `Assert<T>.() -> Unit, vararg Assert<T>.() -> Unit` at other places the infix-api.
 *
 * @param assertionCreator The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 * @param otherAssertionCreators A variable amount of additional identification lambdas.
 */
class Entries<in T : Any>(
    val assertionCreator: Assert<T>.() -> Unit,
    vararg val otherAssertionCreators: Assert<T>.() -> Unit
) : GroupWithoutNullableEntries<Assert<T>.() -> Unit> {
    override fun toList(): List<Assert<T>.() -> Unit> = assertionCreator glue otherAssertionCreators
}

/**
 * Parameter object to express a [GroupWithNullableEntries] of nullable identification lambdas.
 *
 * It is also used to express `(Assert<T>.() -> Unit)?, vararg (Assert<T>.() -> Unit)?` at other places the infix-api.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create or if it is `null` in case
 *   [assertionCreatorOrNull] is defined as `null`.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class NullableEntries<in T : Any>(
    val assertionCreatorOrNull: (Assert<T>.() -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}

/**
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>` in the infix-api.
 */
class Order<T, G : Group<T>>(
    val firstGroup: G,
    val secondGroup: G,
    vararg val otherExpectedGroups: G
)

/**
 * Parameter object to express `Pair<K, V>, vararg Pair<K, V>` in the infix-api.
 */
class Pairs<out K, out V>(val expected: Pair<K, V>, vararg val otherExpected: Pair<K, V>) {
    fun toList(): List<Pair<K, V>> = expected glue otherExpected
}

/**
 * Parameter object to express `String, vararg String` in the infix-api.
 */
class RegexPatterns(val pattern: String, vararg val otherPatterns: String) {
    fun toList(): List<String> = pattern glue otherPatterns
}

/**
 * Parameter object to express a [Group] with a single element.
 */
class Value<out T : Any>(val expected: T) : GroupWithoutNullableEntries<T> {
    override fun toList(): List<T> = listOf(expected)
}

/**
 * Represents a [Group] with a single nullable value.
 */
class NullableValue<out T : Any?>(val expected: T) : GroupWithNullableEntries<T> {
    override fun toList(): List<T> = listOf(expected)
}

/**
 * Parameter object to express `(T, vararg T) where T: Any` in the infix-api.
 */
class Values<out T : Any>(val expected: T, vararg val otherExpected: T) : GroupWithoutNullableEntries<T> {
    override fun toList(): List<T> = expected glue otherExpected
}

/**
 * Parameter object to express `(T, vararg T) where T: Any?` in the infix-api.
 */
class NullableValues<out T>(val expected: T, vararg val otherExpected: T) : GroupWithNullableEntries<T> {
    override fun toList(): List<T> = expected glue otherExpected
}
