package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.kbox.identity

/**
 * Starts a sophisticated `toContain` assertion building process based on this [Expect].
 *
 * @return The newly created builder.
 *
 * @since 0.17.0
 */
val <E, T : Iterable<E>> Expect<T>.toContain: IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour>
    get() = _logic.builderContainsInIterableLike(::identity)

/**
 * Starts a sophisticated `toContain` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @return The newly created builder.
 *
 * @since 0.17.0
 */
val <E, T : Iterable<E>> Expect<T>.notToContain: NotCheckerStep<E, T, NotSearchBehaviour>
    get() = _logic.builderContainsNotInIterableLike(::identity)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains the
 * [expected] value and the [otherExpected] values (if given).
 *
 * It is a shortcut for `toContain.inAnyOrder.atLeast(1).values(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.inAnyOrder.exactly(2).value('a')`
 * instead of:
 *   `toContain('a', 'a')`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E, T : Iterable<E>> Expect<T>.toContain(expected: E, vararg otherExpected: E): Expect<T> =
    toContain.inAnyOrder.atLeast(1).values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `toContain.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.toContain(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    toContain.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `toContain.inAnyOrder.atLeast(1).entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.toContain(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = toContain.inAnyOrder.atLeast(1).entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only
 * the [expected] value and the [otherExpected] values (if given) in the defined order.
 *
 * It is a shortcut for `toContain.inOrder.only.values(expected, *otherExpected)`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E, T : Iterable<E>> Expect<T>.toContainExactly(expected: E, vararg otherExpected: E): Expect<T> =
    toContain.inOrder.only.values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `toContain.inOrder.only.entry(assertionCreatorOrNull)`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.toContainExactly(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    toContain.inOrder.only.entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or `null` in case [assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each [otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `toContain.inOrder.only.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.toContainExactly(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = toContain.inOrder.only.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only elements of [expectedIterableLike]
 * in same order
 *
 * It is a shortcut for 'toContain.inOrder.only.elementsOf(anotherList)'
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [Iterable].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.17.0
 */
inline fun <reified E, T : Iterable<E>> Expect<T>.toContainExactlyElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = toContain.inOrder.only.elementsOf(expectedIterableLike)

/** Expects that the subject of `this` expectation (an [Iterable]) contains all elements of [expectedIterableLike].
 *
 * It is a shortcut for `toContain.inAnyOrder.atLeast(1).elementsOf(expectedIterable)`
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [Iterable].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.17.0
 */
inline fun <reified E, T : Iterable<E>> Expect<T>.toContainElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = toContain.inAnyOrder.atLeast(1).elementsOf(expectedIterableLike)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element and
 * that it does not contain the [expected] value and neither one of the [otherExpected] values (if given).
 *
 * It is a shortcut for `notToContain.values(expected, *otherExpected)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E, T : Iterable<E>> Expect<T>.notToContain(expected: E, vararg otherExpected: E): Expect<T> =
    notToContain.values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has next element(s) and
 * - that any of them holds the expectations the [assertionCreatorOrNull] creates or
 * - that any of them is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is logically equivalent to `toContain.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.toHaveNextAndAny(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    toContain.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has next element(s) and
 * - that none of them holds all expectations the [assertionCreatorOrNull] creates or
 * - that none of them is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * It is logically equivalent to `toHaveNext().notToContain.entry(assertionCreatorOrNull)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.toHaveNextAndNone(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    notToContain.entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has next element(s) and
 * - that all of them hold all expectations the [assertionCreatorOrNull] creates or
 * - that all of them are `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E : Any, T : Iterable<E?>> Expect<T>.toHaveNextAndAll(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    _logicAppend { all(::identity, assertionCreatorOrNull) }


/**
 * Expects that the subject of `this` expectation (an [Iterable]) has a next element ([hasNext] returns true).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E, T : Iterable<E>> Expect<T>.toHaveNext(): Expect<T> =
    _logicAppend { hasNext(::identity) }

/**
 * Expects that the subject of `this` expectation (an [Iterable]) does not have a next element ([hasNext] returns false).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E, T : Iterable<E>> Expect<T>.notToHaveNext(): Expect<T> =
    _logicAppend { hasNotNext(::identity) }

/**
 * Expects that the subject of `this` expectation (an [Iterable]) does not have duplicate elements.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
fun <E, T : Iterable<E>> Expect<T>.notToContainDuplicates(): Expect<T> =
    _logicAppend { containsNoDuplicates(::identity) }
