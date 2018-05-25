package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertMarker
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.addAssertion
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

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
@AssertMarker
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] nullable value
 * shall be searched within the [Iterable].
 *
 * Delegates to `the NullableValues(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
infix fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.nullableValue(expected: E): AssertionPlant<T>
    = this the NullableValues(expected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values.expected] is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
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
@AssertMarker
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, values.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected nullable [values]
 * shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values.expected] is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 nullableValue 'a'`
 * instead of:
 *   `to contain inAny order exactly 1 the NullableValues('a', 'a')`
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
infix fun <E: Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(values: NullableValues<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, values.toList()))


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
@AssertMarker
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this the Entries(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreator] might create or needs to be `null` in case [assertionCreator]
 * is `null` as well.
 *
 * Delegates to `the NullableEntries(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
infix fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.nullableEntry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the NullableEntries(assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [Entries.assertionCreator] might create -- likewise an entry (can be the same) is searched for each
 * of the [Entries.otherAssertionCreators].
 *
 * @param entries The parameter object which contains the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(entries: Entries<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrder(this, entries.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [NullableEntries.assertionCreator] might create or needs to be `null` in case
 * [NullableEntries.assertionCreator] is `null` as well -- likewise an entry (can be the same) is searched for each
 * of the [NullableEntries.otherAssertionCreators].
 *
 * @param entries The parameter object which contains the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
infix fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.the(entries: NullableEntries<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrder(this, entries.toList()))
