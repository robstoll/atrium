package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * Creates an [IterableContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Expect<T>.contains: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = ExpectImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Expect<T>.containsNot: NotCheckerOption<E, T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(ExpectImpl.iterable.containsNotBuilder(this))

/**
 * Expects that the property min of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @since 0.9.0
 */
fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    ExpectImpl.iterable.min(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property min of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 * @since 0.9.0
 */
fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(): Expect<E> = ExpectImpl.iterable.min(this).getExpectOfFeature()

/**
 * Expects that the property max of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @since 0.9.0
 */
fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    ExpectImpl.iterable.max(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property max of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 * @since 0.9.0
 */
fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(): Expect<E> = ExpectImpl.iterable.max(this).getExpectOfFeature()

/**
 * Expects that the subject of the assertion (an [Iterable]) contains the
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
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> Expect<T>.contains(expected: E, vararg otherExpected: E): Expect<T> =
    contains.inAnyOrder.atLeast(1).values(expected, *otherExpected)

/**
 * Expects that the subject of the assertion (an [Iterable]) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.contains(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)

/**
 * Expects that the subject of the assertion (an [Iterable]) contains an entry holding the
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
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.contains(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = contains.inAnyOrder.atLeast(1).entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only
 * the [expected] value and the [otherExpected] values (if given) in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.values(expected, *otherExpected)`
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> Expect<T>.containsExactly(expected: E, vararg otherExpected: E): Expect<T> =
    contains.inOrder.only.values(expected, *otherExpected)

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inOrder.only.entry(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.containsExactly(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    contains.inOrder.only.entry(assertionCreatorOrNull)

/**
 * Expects that the subject of the assertion (an [Iterable]) contains only an entry holding
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
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.containsExactly(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = contains.inOrder.only.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


/**
 * Expects that the subject of the assertion (an [Iterable]) does not contain the [expected] value
 * and neither one of the [otherExpected] values (if given).
 *
 *  It is a shortcut for `containsNot.values(expected, *otherExpected)`
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> Expect<T>.containsNot(expected: E, vararg otherExpected: E) =
    containsNot.values(expected, *otherExpected)

/**
 * Expects that the subject of the assertion (an [Iterable]) contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.any(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)


/**
 * Expects that the subject of the assertion (an [Iterable]) does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull] or does not contain a single entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `containsNot.entry(assertionCreatorOrNull)`
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.none(assertionCreatorOrNull: (Expect<E>.() -> Unit)?) =
    containsNot.entry(assertionCreatorOrNull)


/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element and
 * that every element holds all assertions created by the [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?) =
    addAssertion(ExpectImpl.iterable.all(this, assertionCreatorOrNull))

/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <E, T : Iterable<E>> Expect<T>.hasNext() = addAssertion(ExpectImpl.iterable.hasNext(this))

/**
 * Expects that the subject of the assertion (an [Iterable]) does not have next element.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <E, T : Iterable<E>> Expect<T>.hasNotNext() = addAssertion(ExpectImpl.iterable.hasNotNext(this))
