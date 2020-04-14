package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * Creates an [IterableContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Expect<T>.contains(
    @Suppress("UNUSED_PARAMETER") o: o
): IterableContains.Builder<E, T, NoOpSearchBehaviour> = ExpectImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Expect<T>.containsNot(
    @Suppress("UNUSED_PARAMETER") o: o
): NotCheckerOption<E, T, NotSearchBehaviour> = NotCheckerOptionImpl(ExpectImpl.iterable.containsNotBuilder(this))

/**
 * Creates an [Expect] for the result of calling `min()` on the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.11.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    ExpectImpl.iterable.min(this).getExpectOfFeature()

/**
 * Expects that the result of calling `min()` on the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    ExpectImpl.iterable.min(this).addToInitial(assertionCreator)


/**
 * Creates an [Expect] for the result of calling `max()` on the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.11.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    ExpectImpl.iterable.max(this).getExpectOfFeature()

/**
 * Expects that the result of calling `max()` on  the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    ExpectImpl.iterable.max(this).addToInitial(assertionCreator)

/**
 *  Expects that the subject of the assertion (an [Iterable]) contains the [expected] value.
 *
 * It is a shortcut for `contains o inAny order atLeast 1 value expected`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.contains(expected: E) =
    it contains o inAny order atLeast 1 value expected

/**
 * Expects that the subject of the assertion (an [Iterable]) contains the expected [values].
 *
 * It is a shortcut for `contains o inAny order atLeast 1 the values(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values] is defined as `values("a", "a")`, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *    contains o inAny order exactly 2 value 'a'`
 * instead of:
 *   `contains values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the [Iterable]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.contains(values: Values<E>): Expect<T> =
    it contains o inAny order atLeast 1 the values

/**
 * Expects that the subject of the assertion (an [Iterable]) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains o inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.contains(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    it contains o inAny order atLeast 1 entry assertionCreatorOrNull

/**
 *  Expects that the subject of the assertion (an [Iterable]) contains an entry holding the
 * assertions created by [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or an entry
 * which is `null` in case [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each of the
 * [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `contains o inAny order atLeast 1 the entries({ ... }, ...)`
 *
 * @param entries The entries which are expected to be contained within the [Iterable]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.contains(entries: Entries<E>): Expect<T> =
    it contains o inAny order atLeast 1 the entries

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only
 * the [expected] value.
 *
 * It is a shortcut for `contains o inGiven order and only value expected`

 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.containsExactly(expected: E): Expect<T> =
    it contains o inGiven order and only value expected

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only
 * the expected [values] in the defined order.
 *
 * It is a shortcut for `contains o inGiven order and only the Values(...)`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param values The values which are expected to be contained within the [Iterable]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.containsExactly(values: Values<E>): Expect<T> =
    it contains o inGiven order and only the values

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains o inGiven order and only entry assertionCreatorOrNull`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.containsExactly(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = it contains o inGiven order and only entry assertionCreatorOrNull

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only an entry holding
 * the assertions created by [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or
 * `null` in case [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each
 * [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `contains o inGiven order and only the Entries(...)`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param entries The entries which are expected to be contained within the [Iterable]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.containsExactly(entries: Entries<E>): Expect<T> =
    it contains o inGiven order and only the entries

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only elements of [expectedIterable]
 * in same order
 *
 * It is a shortcut for 'contains.inOrder.only.elementsOf(anotherList)'
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
inline infix fun <reified E, T : Iterable<E>> Expect<T>.containsExactlyElementsOf(
    expectedIterable: Iterable<E>
): Expect<T> = it contains o inGiven order and only elementsOf (expectedIterable)

/** Expects that the subject of the assertion (an [Iterable]) contains all elements of [expectedIterable].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).elementsOf(expectedIterable)`
 *
 * @param expectedIterable The [Iterable] whose elements are expected to be contained within this [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case the given [expectedIterable] does not have elements (is empty).
 *
 * @since 0.11.0
 */
inline infix fun <reified E, T : Iterable<E>> Expect<T>.containsElementsOf(expectedIterable: Iterable<E>): Expect<T> =
    it contains o inAny order atLeast 1 elementsOf  expectedIterable

/**
 * Expects that the subject of the assertion (an [Iterable]) does not contain the [expected] value.
 *
 *  It is a shortcut for `containsNot o value expected`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.containsNot(expected: E): Expect<T> =
    it containsNot o value expected

/**
 * Expects that the subject of the assertion (an [Iterable]) does not contain the expected [values].
 *
 *  It is a shortcut for `containsNot o the values(...)`
 *
 * @param values The values which should not be contained within the [Iterable]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.containsNot(values: Values<E>): Expect<T> =
    it containsNot o the values


/**
 * Expects that the subject of the assertion (an [Iterable]) contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains o inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.any(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    it contains o inAny order atLeast 1 entry assertionCreatorOrNull


/**
 * Expects that the subject of the assertion (an [Iterable]) does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull] or does not contain a single entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `containsNot o entry assertionCreatorOrNull`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.none(assertionCreatorOrNull: (Expect<E>.() -> Unit)?) =
    it containsNot o entry assertionCreatorOrNull

/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element and
 * that every element holds all assertions created by the [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?) =
    addAssertion(ExpectImpl.iterable.all(this, assertionCreatorOrNull))

/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.has(@Suppress("UNUSED_PARAMETER") next: next) =
    addAssertion(ExpectImpl.iterable.hasNext(this))

/**
 * Expects that the subject of the assertion (an [Iterable]) does not have next element.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.hasNot(@Suppress("UNUSED_PARAMETER") next: next) =
    addAssertion(ExpectImpl.iterable.hasNotNext(this))
