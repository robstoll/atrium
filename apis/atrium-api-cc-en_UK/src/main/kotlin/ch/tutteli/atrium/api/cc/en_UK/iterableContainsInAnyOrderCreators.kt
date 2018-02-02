package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * within the [Iterable].
 *
 * Delegates to `objects(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.value(expected: E): AssertionPlant<T>
    = objects(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values shall be searched within the [Iterable].
 *
 * Delegates to `objects(expected, *otherExpected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = objects(expected, *otherExpected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched
 * within the [Iterable].
 *
 * Delegates to `objects(expected)`.
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.`object`(expected: E): AssertionPlant<T>
    = objects(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as the
 * [otherExpected] objects shall be searched within the iterable.
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).objects('a')`
 * instead of:
 *   `contains.inAnyOrder.atLeast(1).objects('a', 'a')`
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 * @param otherExpected Additional objects which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.objects(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(IterableContainsAssertions.containsObjectsInAnyOrder(this, expected, otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create.
 *
 * Delegates to `entries(expected)`.
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = entries(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create and search for entries which hold (one by one) the assertions
 * created by the [otherAssertionCreators].
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 * @param otherAssertionCreators Additional lambda functions which each kind of identify (separately) an entry
 *   which we are looking for.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.entries(
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = addAssertion(IterableContainsAssertions.containsEntriesInAnyOrder(this, assertionCreator, otherAssertionCreators))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreator] might create or is `null` in case [assertionCreator] is null as well.
 *
 * Delegates to `entries(expected)`.
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("nullableEntry")
fun <E : Any, T : Iterable<E?>> IterableContainsCheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>.entry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entries(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreator] might create or is `null` in case [assertionCreator] is null as well --
 * likewise an entry (can be the same) is searched for each of the [otherAssertionCreators].
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 * @param otherAssertionCreators Additional lambda functions which each kind of identify (separately) an entry
 *   which we are looking for.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("nullableEntries")
fun <E : Any, T : Iterable<E?>> IterableContainsCheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>.entries(
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = addAssertion(IterableContainsAssertions.containsNullableEntriesInAnyOrder(this, assertionCreator, otherAssertionCreators))
