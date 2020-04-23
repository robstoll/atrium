// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
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
infix fun <E, T : Iterable<E>> Assert<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain): IterableContains.Builder<E, T, NoOpSearchBehaviour>
    = AssertImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Assert<T>.notTo(@Suppress("UNUSED_PARAMETER") contain: contain): NotCheckerOption<E, T, NotSearchBehaviour>
    = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains the [expected] value.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.contains(expected: E)
    = o to contain inAny order atLeast 1 value expected

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains the expected [values].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Values(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [values].[expected][Values.expected] is defined as `'a'` and
 * one [values].[otherExpected][Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value 'a'`
 * instead of:
 *   `contains Values('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.contains(values: Values<E>): AssertionPlant<T>
    = o to contain inAny order atLeast 1 the values

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = o to contain inAny order atLeast 1 entry assertionCreatorOrNull

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding the
 * assertions created by [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or an entry
 * which is `null` in case [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each of the
 * [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(entries: Entries<E>): AssertionPlant<T>
    = o to contain inAny order atLeast 1 the entries


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only
 * the [expected] value.
 *
 * It is a shortcut for `to contain inGiven order and only value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsExactly(expected: E): AssertionPlant<T>
    = o to contain inGiven order and only value expected

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only
 * the expected [values] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsExactly(values: Values<E>): AssertionPlant<T>
    = o to contain inGiven order and only the values

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only one entry
 * which is holding the assertions created by [assertionCreatorOrNull] or
 * only one entry which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is a shortcut for `to contain inGiven order and only entry assertionCreatorOrNull`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsExactly(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = o to contain inGiven order and only entry assertionCreatorOrNull

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only an entry holding
 * the assertions created by [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or
 * `null` in case [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each
 * [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsExactly(entries: Entries<E>): AssertionPlant<T>
    = o to contain inGiven order and only the entries


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain the [expected] value.
 *
 * It is a shortcut for `notTo contain value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(expected: E): AssertionPlant<T>
    = o notTo contain value expected

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain the expected [values].
 *
 * It is a shortcut for `notTo contain the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(values: Values<E>): AssertionPlant<T>
    = o notTo contain the values


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding
 * the assertions created by the [assertionCreatorOrNull] or an entry
 * which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.any(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = o to contain inAny order atLeast 1 entry assertionCreatorOrNull


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull]
 * or does not contain a single entry which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `notTo contain entry assertionCreatorOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.none(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = o notTo contain entry assertionCreatorOrNull

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] has at least one element and
 * that every element holds all assertions created by the
 * [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Suppress("DEPRECATION")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.all(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreatorOrNull))
