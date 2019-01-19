package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.*
import ch.tutteli.kbox.glue

/**
 * Represents a parameter object used to express the arguments `T, vararg T`
 * and provides utility functions to transform them.
 */
interface VarArgHelper<out T> {
    val expected: T
    val otherExpected: Array<out T>

    val mapArguments get() = ArgumentMapperBuilder(expected, otherExpected)

    fun toList(): List<T> = expected glue otherExpected
}

/**
 * Parameter object to express `T, vararg T` in the infix-api.
 */
class All<T>(override val expected: T, override vararg val otherExpected: T) : VarArgHelper<T>


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
    assertionCreator: Assert<T>.() -> Unit,
    vararg otherAssertionCreators: Assert<T>.() -> Unit
) : GroupWithoutNullableEntries<Assert<T>.() -> Unit>, VarArgHelper<Assert<T>.() -> Unit> {
    override val expected = assertionCreator
    override val otherExpected = otherAssertionCreators

    override fun toList() = super.toList()
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
 *   `assertionCreatorOrNull` is defined as `null`.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
class NullableEntries<in T : Any>(
    assertionCreatorOrNull: (Assert<T>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?>, VarArgHelper<(Assert<T>.() -> Unit)?> {
    override val expected = assertionCreatorOrNull
    override val otherExpected = otherAssertionCreatorsOrNulls

    override fun toList() = super.toList()
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
        = "KeyNullableValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"}"
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
class Values<out T : Any>(override val expected: T, override vararg val otherExpected: T) : GroupWithoutNullableEntries<T>,
    VarArgHelper<T> {
    override fun toList(): List<T> = super.toList()
}

/**
 * Parameter object to express `(T, vararg T) where T: Any?` in the infix-api.
 */
class NullableValues<out T>(override val expected: T, override vararg val otherExpected: T) : GroupWithNullableEntries<T>,
    VarArgHelper<T> {
    override fun toList(): List<T> = super.toList()
}
