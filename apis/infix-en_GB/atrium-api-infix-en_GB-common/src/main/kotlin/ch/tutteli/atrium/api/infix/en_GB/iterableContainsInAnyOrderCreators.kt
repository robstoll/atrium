package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertion
import ch.tutteli.atrium.domain.builders.utils.toVarArg
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.CheckerOption
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]
 * value shall be searched within the [Iterable].
 *
 * Delegates to `the values(expected)`.
 *
 * @param expected The value which is expected to be contained within this [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expected: E): Expect<T> =
    this the values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values] is defined as `values("a", "a")`, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value 'a'`
 * instead of:
 *   `to contain inAny order exactly 1 the values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the [Iterable]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(values: Values<E>): Expect<T> =
    addAssertion(ExpectImpl.iterable.contains.valuesInAnyOrder(this, values.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreatorOrNull] creates or needs to be `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * Delegates to `the entries(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> CheckerOption<E?, T, InAnyOrderSearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = this the entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] creates or
 * needs to be `null` in case [entries].[assertionCreatorOrNull][Entries.otherAssertionCreatorsOrNulls]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each of
 * the [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls].
 *
 * @param entries The entries which are expected to be contained within the [Iterable]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> CheckerOption<E?, T, InAnyOrderSearchBehaviour>.the(
    entries: Entries<E>
): Expect<T> = addAssertion(ExpectImpl.iterable.contains.entriesInAnyOrder(this, entries.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterable]
 * shall be searched within the [Iterable].
 *
 * Delegates to [values] which also means that it does not search for unique matches
 * (see [values] for more information).
 *
 * @param expectedIterable The [Iterable] whose elements are expected to be contained within this [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case the given [expectedIterable] does not have elements (is empty).
 *
 * @since 0.12.0
 */
inline infix fun <reified E, T : Iterable<E>> CheckerOption<E, T, InAnyOrderSearchBehaviour>.elementsOf(
    expectedIterable: Iterable<E>
): Expect<T> {
    val (first, rest) = toVarArg(expectedIterable)
    return this the values(first, *rest)
}
