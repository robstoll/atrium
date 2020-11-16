package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains.CheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.entries
import ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.values
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.utils.toVarArg

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]
 * value shall be searched within the [IterableLike].
 *
 * Delegates to `the values(expected)`.
 *
 * @param expected The value which is expected to be contained within this [IterableLike].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> CheckerStep<E, T, InAnyOrderSearchBehaviour>.value(expected: E): Expect<T> =
    this the values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * shall be searched within the [IterableLike].
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
 * @param values The values which are expected to be contained within the [IterableLike]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> CheckerStep<E, T, InAnyOrderSearchBehaviour>.the(values: Values<E>): Expect<T> =
    _logicAppend { values(values.toList()) }

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
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E : Any, T : IterableLike> CheckerStep<out E?, T, InAnyOrderSearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = this the entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] creates or
 * needs to be `null` in case [entries].[assertionCreatorOrNull][Entries.otherAssertionCreatorsOrNulls]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each of
 * the [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls].
 *
 * @param entries The entries which are expected to be contained within the [IterableLike]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E : Any, T : IterableLike> CheckerStep<out E?, T, InAnyOrderSearchBehaviour>.the(
    entries: Entries<E>
): Expect<T> = _logicAppend { entries(entries.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterableLike]
 * shall be searched within the [IterableLike].
 *
 * Delegates to [values] which also means that it does not search for unique matches
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [IterableLike].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an
 *   [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 */
inline infix fun <reified E, T : IterableLike> CheckerStep<E, T, InAnyOrderSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = _logic.toVarArg<E>(expectedIterableLike).let { (first, rest) -> this the values(first, *rest) }
