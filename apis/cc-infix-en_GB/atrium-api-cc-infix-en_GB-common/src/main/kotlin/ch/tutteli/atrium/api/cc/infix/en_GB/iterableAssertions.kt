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
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains the [expected] value.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(expected: E)
    = o to contain inAny order atLeast 1 value expected

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains the
 * [expectedOrNull] nullable value.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 value expectedOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsNullable")
infix fun <E : Any?, T : Iterable<E>> Assert<T>.contains(expectedOrNull: E)
    = o to contain inAny order atLeast 1 value expectedOrNull


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains the expected [values].
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
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(values: Values<E>): AssertionPlant<T>
    = o to contain inAny order atLeast 1 the values

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains the expected [nullableValues].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the NullableValues(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [nullableValues].[expected][Values.expected] is defined as `'a'` and
 * one [nullableValues].[otherExpected][Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value 'a'`
 * instead of:
 *   `contains NullableValues('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> Assert<T>.contains(nullableValues: NullableValues<E>): AssertionPlant<T>
    = o to contain inAny order atLeast 1 the nullableValues


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = o to contain inAny order atLeast 1 entry assertionCreator

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsNullable")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = o to contain inAny order atLeast 1 entry assertionCreatorOrNull

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [entries].[assertionCreator][Entries.expected] -- likewise an entry (can be the same) is searched for each
 * of the [entries].[otherAssertionCreators][Entries.otherExpected].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(entries: Entries<E>): AssertionPlant<T>
    = o to contain inAny order atLeast 1 the entries

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains an entry holding the
 * assertions created by [nullableEntries].[assertionCreatorOrNull][NullableEntries.expected] or an entry
 * which is `null` in case [nullableEntries].[assertionCreatorOrNull][NullableEntries.expected]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each of the
 * [nullableEntries].[otherAssertionCreatorsOrNulls][NullableEntries.otherExpected].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the NullableEntries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = o to contain inAny order atLeast 1 the nullableEntries


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only the [expected] value.
 *
 * It is a shortcut for `to contain inGiven order and only value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsExactly(expected: E): AssertionPlant<T>
    = o to contain inGiven order and only value expected

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains only
 * the [expectedOrNull] nullable value.
 *
 * It is a shortcut for `to contain inGiven order and only value expectedOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsExactlyNullable")
infix fun <E, T : Iterable<E>> Assert<T>.containsExactly(expectedOrNull: E): AssertionPlant<T>
    = o to contain inGiven order and only value expectedOrNull


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only the expected [values] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsExactly(values: Values<E>): AssertionPlant<T>
    = o to contain inGiven order and only the values

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains only
 * the expected [nullableValues] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the NullableValues(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsExactly(nullableValues: NullableValues<E>): AssertionPlant<T>
    = o to contain inGiven order and only the nullableValues

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only one entry which is holding the assertions created
 * by the [assertionCreator].
 *
 * It is a shortcut for `to contain inGiven order and only the entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsExactly(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = o to contain inGiven order and only entry assertionCreator

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains only one entry
 * which is holding the assertions created by [assertionCreatorOrNull] or
 * only one entry which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is a shortcut for `to contain inGiven order and only entry assertionCreatorOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsExactlyNullable")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsExactly(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = o to contain inGiven order and only entry assertionCreatorOrNull

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [entries].[assertionCreator][Entries.expected] and an additional entry
 * for each [entries].[otherAssertionCreators][Entries.otherExpected] (if given) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `to contain inGiven order and only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsExactly(entries: Entries<E>): AssertionPlant<T>
    = o to contain inGiven order and only the entries

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains only an entry holding
 * the assertions created by [nullableEntries].[assertionCreatorOrNull][NullableEntries.expected] or
 * `null` in case [nullableEntries].[assertionCreatorOrNull][NullableEntries.expected] is defined as `null`
 * and likewise an additional entry for each
 * [nullableEntries].[otherAssertionCreatorsOrNulls][NullableEntries.otherExpected] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the NullableEntries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsExactly(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = o to contain inGiven order and only the nullableEntries


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] does not contain the [expected] value.
 *
 * It is a shortcut for `notTo contain value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsNot(expected: E): AssertionPlant<T>
    = o notTo contain value expected

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] does not contain the expected [values].
 *
 * It is a shortcut for `notTo contain the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsNot(values: Values<E>): AssertionPlant<T>
    = o notTo contain the values


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry assertionCreator`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.any(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = o to contain inAny order atLeast 1 entry assertionCreator

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) contains an entry holding
 * the assertions created by the [assertionCreatorOrNull].[assertionCreator][NullableEntry.assertionCreator] or an entry
 * which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("anyOfNullable")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.any(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = o to contain inAny order atLeast 1 entry assertionCreatorOrNull


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] does not contain a single entry which holds all assertions
 * created by the [assertionCreator].
 *
 *  It is a shortcut for `notTo contain entry assertionCreator`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.none(assertionCreator: (Assert<E>.() -> Unit))
    = o notTo contain entry assertionCreator

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull]
 * or does not contain a single entry which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `notTo contain entry assertionCreatorOrNull`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("noneOfNullable")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.none(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = o notTo contain entry assertionCreatorOrNull


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] has at least one element and that every element holds all
 * assertions created by the [assertionCreator].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.all(assertionCreator: Assert<E>.() -> Unit)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreator))

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] (which has a nullable element type) has at least one element and
 * that every element holds all assertions created by the
 * [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("allOfNullable")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.all(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreatorOrNull))
