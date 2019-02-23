@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderCreatorsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.addAssertion
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * within the [Iterable].
 *
 * Delegates to `the Values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected][expectedOrNull]
 * nullable value shall be searched within the [Iterable].
 *
 * Delegates to `the NullableValues(expectedOrNull)`.
 *
 * @param expectedOrNull The value which is expectedOrNull to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("nullableValue")
infix fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expectedOrNull: E): AssertionPlant<T>
    = this the NullableValues(expectedOrNull)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [values].[expected][Values.expected] is defined as `'a'` and one [values].[otherExpected][Values.otherExpected]
 * is defined as `'a'` as well, then both match, even though they match the same entry.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value 'a'`
 * instead of:
 *   `to contain inAny order exactly 1 the Values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, values.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [nullableValues]
 * shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [nullableValues].[expected][NullableValues.expected] is defined as `'a'` and one
 * [nullableValues].[otherExpected][NullableValues.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value 'a'`
 * instead of:
 *   `to contain inAny order exactly 1 the NullableValues('a', 'a')`
 *
 * @param nullableValues The nullableValues which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E: Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(nullableValues: NullableValues<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, nullableValues.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create.
 *
 * Delegates to `the Entries(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this the Entries(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreatorOrNull] might create or needs to be `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * Delegates to `the NullableEntries(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("nullableEntry")
infix fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.entry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the NullableEntries(assertionCreatorOrNull)


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [entries].[assertionCreator][Entries.expected] might create -- likewise an entry
 * (can be the same) is searched for each of
 * the [entries].[otherAssertionCreators][Entries.otherExpected].
 *
 * @param entries The parameter object which contains the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(entries: Entries<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrder(this, entries.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [nullableEntries].[assertionCreatorOrNull][NullableEntries.expected] might create or
 * needs to be `null` in case [nullableEntries].[assertionCreatorOrNull][NullableEntries.expected]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each of
 * the [nullableEntries].[otherAssertionCreatorsOrNulls][NullableEntries.otherExpected].
 *
 * @param nullableEntries The parameter object which contains the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.the(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrder(this, nullableEntries.toList()))
