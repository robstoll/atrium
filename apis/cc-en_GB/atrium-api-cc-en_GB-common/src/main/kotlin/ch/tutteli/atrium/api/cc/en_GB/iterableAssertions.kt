// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.contains"
    )
)
val <E, T : Iterable<E>> Assert<T>.contains: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = AssertImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsNot",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.containsNot"
    )
)
val <E, T : Iterable<E>> Assert<T>.containsNot: NotCheckerOption<E, T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))


/**c
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains the
 * [expected] value and the [otherExpected] values (if given).
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).values(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).value('a')`
 * instead of:
 *   `contains('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains(expected, *otherExpected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.contains"
    )
)
fun <E, T: Iterable<E>> Assert<T>.contains(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).values(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains(asSubExpect(assertionCreatorOrNull)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.contains"
    )
)
fun <E: Any, T: Iterable<E?>> Assert<T>.contains(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().contains(\n" +
            "asSubExpect(assertionCreatorOrNull),\n" +
            "*otherAssertionCreatorsOrNulls.map { asSubExpect(it)}.toTypedArray()\n" +
            ").asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.contains"
    )
)
fun <E: Any, T: Iterable<E?>> Assert<T>.contains(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only
 * the [expected] value and the [otherExpected] values (if given) in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.values(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsExactly(expected, *otherExpected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.containsExactly"
    )
)
fun <E, T : Iterable<E>> Assert<T>.containsExactly(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inOrder.only.values(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inOrder.only.entry(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsExactly(asSubExpect(assertionCreatorOrNull)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.containsExactly"
    )
)
fun <E : Any, T : Iterable<E?>> Assert<T>.containsExactly(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inOrder.only.entry(assertionCreatorOrNull)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or `null` in case [assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each [otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsExactly(\n" +
            "asSubExpect(assertionCreatorOrNull),\n" +
            "*otherAssertionCreatorsOrNulls.map { asSubExpect(it) }.toTypedArray()\n" +
            ").asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.containsExactly"
    )
)
fun <E : Any, T : Iterable<E?>> Assert<T>.containsExactly(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain the [expected] value
 * and neither one of the [otherExpected] values (if given).
 *
 *  It is a shortcut for `containsNot.values(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().containsNot(expected, *otherExpected).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.containsNot"
    )
)
fun <E, T : Iterable<E>> Assert<T>.containsNot(expected: E, vararg otherExpected: E)
    = containsNot.values(expected, *otherExpected)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().any(asSubExpect(assertionCreatorOrNull)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.any"
    )
)
fun <E : Any, T : Iterable<E?>> Assert<T>.any(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull] or does not contain a single entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `containsNot.entry(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().none(asSubExpect(assertionCreatorOrNull)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.none"
    )
)
fun <E : Any, T : Iterable<E?>> Assert<T>.none(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = containsNot.entry(assertionCreatorOrNull)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] has at least one element and
 * that every element holds all assertions created by the [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().all(asSubExpect(assertionCreatorOrNull)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.all"
    )
)
fun <E : Any, T : Iterable<E?>> Assert<T>.all(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreatorOrNull))
