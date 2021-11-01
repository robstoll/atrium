//TODO remove both annotations with 1.0.0
@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.kbox.identity
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect].
 *
 * @return The newly created builder.
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain"))
val <E, T : Iterable<E>> Expect<T>.contains: IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour>
    get() = _logic.builderContainsInIterableLike(::identity)

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @return The newly created builder.
 */
@Deprecated("Use notToContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToContain"))
val <E, T : Iterable<E>> Expect<T>.containsNot: NotCheckerStep<E, T, NotSearchBehaviour>
    get() = _logic.builderContainsNotInIterableLike(::identity)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains the
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
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<E, T>(expected, *otherExpected)")
)
fun <E, T : Iterable<E>> Expect<T>.contains(expected: E, vararg otherExpected: E): Expect<T> =
    toContain.inAnyOrder.atLeast(1).values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<E, T>(assertionCreatorOrNull)")
)
fun <E : Any, T : Iterable<E?>> Expect<T>.contains(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    toContain.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding the
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
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<E, T>(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)")
)
fun <E : Any, T : Iterable<E?>> Expect<T>.contains(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = toContain.inAnyOrder.atLeast(1).entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only
 * the [expected] value and the [otherExpected] values (if given) in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.values(expected, *otherExpected)`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toContainExactly; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainExactly<E, T>(expected, *otherExpected)")
)
fun <E, T : Iterable<E>> Expect<T>.containsExactly(expected: E, vararg otherExpected: E): Expect<T> =
    toContain.inOrder.only.values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inOrder.only.entry(assertionCreatorOrNull)`
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
 */
@Deprecated(
    "Use toContainExactly; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainExactly<E, T>(assertionCreatorOrNull)")
)
fun <E : Any, T : Iterable<E?>> Expect<T>.containsExactly(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    toContain.inOrder.only.entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or `null` in case [assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each [otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
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
 */
@Deprecated(
    "Use toContainExactly; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainExactly<E, T>(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)")
)
fun <E : Any, T : Iterable<E?>> Expect<T>.containsExactly(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = toContain.inOrder.only.entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only elements of [expectedIterableLike]
 * in same order
 *
 * It is a shortcut for 'contains.inOrder.only.elementsOf(anotherList)'
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
 * @since 0.13.0
 */
@Deprecated(
    "Use toContainExactlyElementsOf; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainExactlyElementsOf<E, T>(expectedIterableLike)")
)
inline fun <reified E, T : Iterable<E>> Expect<T>.containsExactlyElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = toContain.inOrder.only.elementsOf(expectedIterableLike)

/** Expects that the subject of `this` expectation (an [Iterable]) contains all elements of [expectedIterableLike].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).elementsOf(expectedIterable)`
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
 * @since 0.13.0
 */
@Deprecated(
    "Use toContainElementsOf; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainElementsOf<E, T>(expectedIterableLike)")
)
inline fun <reified E, T : Iterable<E>> Expect<T>.containsElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = toContain.inAnyOrder.atLeast(1).elementsOf(expectedIterableLike)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element and
 * that it does not contain the [expected] value and neither one of the [otherExpected] values (if given).
 *
 * It is a shortcut for `containsNot.values(expected, *otherExpected)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use notToContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToContain<E, T>(expected, *otherExpected)")
)
fun <E, T : Iterable<E>> Expect<T>.containsNot(expected: E, vararg otherExpected: E): Expect<T> =
    notToContain.values(expected, *otherExpected)



/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toHaveElementsAndAny; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveElementsAndAny<E, T>(assertionCreatorOrNull)")
)
fun <E : Any, T : Iterable<E?>> Expect<T>.any(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    toContain.inAnyOrder.atLeast(1).entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element and
 * that it either does not contain a single entry which holds all assertions created by [assertionCreatorOrNull] or
 * that it does not contain a single entry which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `containsNot.entry(assertionCreatorOrNull)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toHaveElementsAndNone; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveElementsAndNone<E, T>(assertionCreatorOrNull)")
)
fun <E : Any, T : Iterable<E?>> Expect<T>.none(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    notToContain.entry(assertionCreatorOrNull)

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element and
 * that either every element holds all assertions created by the [assertionCreatorOrNull] or
 * that all elements are `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use toHaveElementsAndAll; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveElementsAndAll<E, T>(assertionCreatorOrNull)")
)
fun <E : Any, T : Iterable<E?>> Expect<T>.all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    _logicAppend { all(::identity, assertionCreatorOrNull) }


/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toHaveElements; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveElements<E, T>()")
)
fun <E, T : Iterable<E>> Expect<T>.hasNext(): Expect<T> =
    _logicAppend { hasNext(::identity) }

/**
 * Expects that the subject of `this` expectation (an [Iterable]) does not have next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use notToHaveElements; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToHaveElements<E, T>()")
)
fun <E, T : Iterable<E>> Expect<T>.hasNotNext(): Expect<T> =
    _logicAppend { hasNotNext(::identity) }

/**
 * Expects that the subject of `this` expectation (an [Iterable]) does not have duplicate elements.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated(
    "Use toHaveElementsAndNoDuplicates; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveElementsAndNoDuplicates<E, T>()")
)
fun <E, T : Iterable<E>> Expect<T>.containsNoDuplicates(): Expect<T> =
    _logicAppend { containsNoDuplicates(::identity) }
