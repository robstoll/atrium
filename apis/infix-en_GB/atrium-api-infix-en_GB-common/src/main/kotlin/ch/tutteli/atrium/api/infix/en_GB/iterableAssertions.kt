//TODO remove both annotations with 1.0.0
@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.NotCheckerStep
import ch.tutteli.kbox.identity
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Expect<T>.contains(
    @Suppress("UNUSED_PARAMETER") o: o
): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour> = _logic.containsBuilder(::identity)

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Expect<T>.containsNot(
    @Suppress("UNUSED_PARAMETER") o: o
): NotCheckerStep<E, T, NotSearchBehaviour> = _logic.containsNotBuilder(::identity)

/**
 *  Expects that the subject of the assertion (an [Iterable]) contains the [expected] value.
 *
 * It is a shortcut for `contains o inAny order atLeast 1 value expected`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.contains(expected: E): Expect<T> =
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
 * Expects that the subject of the assertion (an [Iterable]) contains only elements of [expectedIterableLike]
 * in same order
 *
 * It is a shortcut for 'contains.inOrder.only.elementsOf(anotherList)'
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types
 * or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 */
inline infix fun <reified E, T : Iterable<E>> Expect<T>.containsExactlyElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = it contains o inGiven order and only elementsOf (expectedIterableLike)

/** Expects that the subject of the assertion (an [Iterable]) contains all elements of [expectedIterableLike].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).elementsOf(expectedIterable)`
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types
 * or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 */
inline infix fun <reified E, T : Iterable<E>> Expect<T>.containsElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = it contains o inAny order atLeast 1 elementsOf expectedIterableLike

/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element and
 * that it does not contain the [expected] value.
 *
 * It is a shortcut for `containsNot o value expected`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Expect<T>.containsNot(expected: E): Expect<T> =
    it containsNot o value expected

/**
 * Expects that the subject of the assertion (an [Iterable])  has at least one element and
 * that it does not contain the expected [values].
 *
 * It is a shortcut for `containsNot o the values(...)`
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
 * Creates an [Expect] for the result of calling `min()` on the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    _logic.min(::identity).transform()

/**
 * Expects that the result of calling `min()` on the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.min(::identity).collectAndAppend(assertionCreator)


/**
 * Creates an [Expect] for the result of calling `max()` on the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    _logic.max(::identity).transform()

/**
 * Expects that the result of calling `max()` on  the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.max(::identity).collectAndAppend(assertionCreator)


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
 * Expects that the subject of the assertion (an [Iterable]) has at least one element and
 * that it either does not contain a single entry which holds all assertions created by [assertionCreatorOrNull] or
 * that it does not contain a single entry which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `containsNot o entry assertionCreatorOrNull`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.none(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    it containsNot o entry assertionCreatorOrNull

/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element and
 * that either every element holds all assertions created by the [assertionCreatorOrNull] or
 * that all elements are `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    _logicAppend { all(::identity, assertionCreatorOrNull) }


/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.has(@Suppress("UNUSED_PARAMETER") next: next): Expect<T> =
    _logicAppend { hasNext(::identity) }

/**
 * Expects that the subject of the assertion (an [Iterable]) does not have next element.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.hasNot(@Suppress("UNUSED_PARAMETER") next: next): Expect<T> =
    _logicAppend { hasNotNext(::identity) }

/**
 * Expects that the subject of the assertion (an [Iterable]) does not have duplicate elements.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.14.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.contains(@Suppress("UNUSED_PARAMETER") noDuplicates: noDuplicates): Expect<T> =
    _logicAppend { containsNoDuplicates(::identity) }

/**
 * Turns `Expect<E, T : Iterable<E>>` into `Expect<List<E>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.14.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.asList(
    @Suppress("UNUSED_PARAMETER") o: o
): Expect<List<E>> = _logic.changeSubject.unreported { it.toList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @since 0.14.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<T> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }
