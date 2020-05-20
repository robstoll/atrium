// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
@file:JvmMultifileClass
@file:JvmName("ParameterObjectsKt")
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
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.all(expected, *otherExpected)")
)
class All<out T>(override val expected: T, override vararg val otherExpected: T) : VarArgHelper<T>


/**
 * Parameter object to express a [Group] with a single identification lambda.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.entry(assertionCreatorOrNull)")
)
class Entry<in T: Any>(
    val assertionCreatorOrNull: (Assert<T>.() -> Unit)?
): GroupWithoutNullableEntries<(Assert<T>.() -> Unit)?>, GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = listOf(assertionCreatorOrNull)
}

/**
 * Parameter object to express a [Group] of identification lambdas.
 *
 * It is also used to express `(Assert<T>.() -> Unit)?, vararg (Assert<T>.() -> Unit)?` at other places the infix-api.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)")
)
class Entries<in T : Any>(
    val assertionCreatorOrNull: (Assert<T>.() -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: (Assert<T>.() -> Unit)?
) : GroupWithoutNullableEntries<(Assert<T>.() -> Unit)?>, GroupWithNullableEntries<(Assert<T>.() -> Unit)?>, VarArgHelper<(Assert<T>.() -> Unit)?> {
    override val expected get() = assertionCreatorOrNull
    override val otherExpected get() = otherAssertionCreatorsOrNulls

    override fun toList(): List<(Assert<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}

/**
 * Wrapper for a single index -- can be used as distinguishable type for an overload where Int is already in use.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.index(index)")
)
data class Index(val index: Int)

/**
 * Wrapper for a single key -- can be used as distinguishable type for an overload where [K] is already in use.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.key(key)")
)
data class Key<out K>(val key: K)

/**
 * Parameter object to express a key/value [Pair] whose value type is a lambda with an
 * [Assert][AssertionPlant] receiver, which means one can either pass a lambda or `null`.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.keyValue(key, valueAssertionCreatorOrNull)")
)
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Assert<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Assert<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String
        = "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}


/**
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>` in the infix-api.
 *
 * Notice, most probably the type parameter G will be removed in the future, will be fixed to [Group].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.order(firstGroup, secondGroup, *otherExpectedGroups)")
)
class Order<out T, out G : Group<T>>(
    val firstGroup: G,
    val secondGroup: G,
    vararg val otherExpectedGroups: G
)

/**
 * Parameter object to express `Pair<K, V>, vararg Pair<K, V>` in the infix-api.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.pairs(expected, *otherExpected)")
)
class Pairs<out K, out V>(
    override val expected: Pair<K, V>,
    override vararg val otherExpected: Pair<K, V>
) : VarArgHelper<Pair<K, V>>

/**
 * Parameter object to express `String, vararg String` in the infix-api.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.regexPatterns(pattern, *otherPatterns)")
)
class RegexPatterns(pattern: String, vararg otherPatterns: String) : VarArgHelper<String> {
    override val expected = pattern
    override val otherExpected = otherPatterns
}


/**
 * Represents a [Group] with a single value.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.value(expected)")
)
data class Value<out T>(val expected: T) : GroupWithNullableEntries<T>, GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [Group] of multiple values.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith("ch.tutteli.atrium.api.infix.en_GB.values(expected, *otherExpected)")
)
class Values<out T>(
    override val expected: T,
    override vararg val otherExpected: T
) : GroupWithoutNullableEntries<T>, GroupWithNullableEntries<T>, VarArgHelper<T>  {
    override fun toList() = listOf(expected, *otherExpected)
}
