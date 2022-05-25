package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInOrderOnlyReportingOptions
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.kbox.identity
import kotlin.jvm.JvmName

/**
 * Starts a sophisticated `toContain` assertion building process based on this [Expect].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainBuilder
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.toContain(
    @Suppress("UNUSED_PARAMETER") o: o
): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour> = _logic.builderContainsInIterableLike(::identity)

/**
 * Starts a sophisticated `toContain` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToContainBuilder
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.notToContain(
    @Suppress("UNUSED_PARAMETER") o: o
): NotCheckerStep<E, T, NotSearchBehaviour> = _logic.builderContainsNotInIterableLike(::identity)

/**
 *  Expects that the subject of `this` expectation (an [Iterable]) contains the [expected] value.
 *
 * It is a shortcut for `toContain o inAny order atLeast 1 value expected`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainValue
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.toContain(expected: E): Expect<T> =
    it toContain o inAny order atLeast 1 value expected

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains the expected [values].
 *
 * It is a shortcut for `toContain o inAny order atLeast 1 the values(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values] is defined as `values("a", "a")`, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *    toContain o inAny order exactly 2 value 'a'`
 * instead of:
 *   `toContain values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the [Iterable]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainValues
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.toContain(values: Values<E>): Expect<T> =
    it toContain o inAny order atLeast 1 the values

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `toContain o inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainAssertion
 *
 * @since 0.17.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toContain(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    it toContain o inAny order atLeast 1 entry assertionCreatorOrNull

/**
 *  Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding the
 * assertions created by [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or an entry
 * which is `null` in case [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each of the
 * [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `toContain o inAny order atLeast 1 the entries({ ... }, ...)`
 *
 * @param entries The entries which are expected to be contained within the [Iterable]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainAssertions
 *
 * @since 0.17.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toContain(entries: Entries<E>): Expect<T> =
    it toContain o inAny order atLeast 1 the entries

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only
 * the [expected] value.
 *
 * It is a shortcut for `toContain o inGiven order and only value expected`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyValue
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.toContainExactly(expected: E): Expect<T> =
    it toContain o inGiven order and only value expected

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only
 * the expected [values] in the defined order.
 *
 * It is a shortcut for `toContain o inGiven order and only the values(...)`
 *
 * @param values The values which are expected to be contained within the [Iterable]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyValues
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.toContainExactly(values: Values<E>): Expect<T> =
    it toContain o inGiven order and only the values

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only
 * the expected [values] in the defined order.
 *
 * It is a shortcut for `toContain o inGiven order and only the values(..., reportOptionsInOrderOnly = {... })`
 *
 * @param values The values which are expected to be contained within the [IterableLike] plus a lambda configuring
 *   the [InOrderOnlyReportingOptions] -- use the function `values(t, ..., reportOptionsInOrderOnly = { ... })`
 *   to create a [WithInOrderOnlyReportingOptions] with a wrapped [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyValues
 *
 * @since 0.18.0
 */
@JvmName("toContainExactlyValuesWithReportingOption")
infix fun <E, T : Iterable<E>> Expect<T>.toContainExactly(values: WithInOrderOnlyReportingOptions<Values<E>>): Expect<T> =
    it toContain o inGiven order and only the values

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `toContain o inGiven order and only entry assertionCreatorOrNull`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyAssertion
 *
 * @since 0.17.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toContainExactly(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = it toContain o inGiven order and only entry assertionCreatorOrNull

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or
 * `null` in case [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each
 * [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `toContain o inGiven order and only the entries(...)`
 *
 * @param entries The entries which are expected to be contained within the [Iterable]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyAssertions
 *
 * @since 0.17.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toContainExactly(entries: Entries<E>): Expect<T> =
    it toContain o inGiven order and only the entries

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [entries].t.[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or
 * `null` in case [entries].t.[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each
 * [entries].t.[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `toContain o inGiven order and only the entries(..., reportOptionsInOrderOnly = { ... })`
 *
 * @param entries The entries which are expected to be contained within the [Iterable] plus a lambda configuring
 *   the [InOrderOnlyReportingOptions] -- use the function `entries(t, ..., reportOptionsInOrderOnly = { ... })`
 *   to create a [WithInOrderOnlyReportingOptions] with a wrapped [Entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyAssertions
 *
 * @since 0.18.0
 */
@JvmName("toContainExactlyEntriesWithReportingOption")
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toContainExactly(
    entries: WithInOrderOnlyReportingOptions<Entries<E>>
): Expect<T> = it toContain o inGiven order and only the entries

/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only elements of [expectedIterableLike]
 * in same order
 *
 * It is a shortcut for 'toContain o inGiven order and only elementsOf expectedIterableLike'
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types
 * or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyElementsOf
 *
 * @since 0.17.0
 */
inline infix fun <reified E, T : Iterable<E>> Expect<T>.toContainExactlyElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = it toContain o inGiven order and only elementsOf expectedIterableLike


/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains only an entry holding
 * the assertions created by [entries].t.[assertionCreatorOrNull][Entries.assertionCreatorOrNull] or
 * `null` in case [entries].t.[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each
 * [entries].t.[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `toContain o inGiven order and only the elementsOf(..., reportOptionsInOrderOnly = { ... })`
 *
 * @param elementsOf The [IterableLike] whose elements are expected to be contained within
 *   this [IterableLike] plus a lambda configuring the [InOrderOnlyReportingOptions] -- use the function
 *   `elementsOf(iterableLike, reportOptionsInOrderOnly = { ... })`
 *   to create a [WithInOrderOnlyReportingOptions] with a wrapped [IterableLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyAssertions
 *
 * @since 0.18.0
 */
@JvmName("toContainExactlyElementsOfWithReportingOption")
inline infix fun <reified E, T : Iterable<E>> Expect<T>.toContainExactly(
    elementsOf: WithInOrderOnlyReportingOptions<IterableLike>
): Expect<T> = it toContain o inGiven order and only the elementsOf

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
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types
 * or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainElementsOf
 *
 * @since 0.17.0
 */
inline infix fun <reified E, T : Iterable<E>> Expect<T>.toContainElementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = it toContain o inAny order atLeast 1 elementsOf expectedIterableLike

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element and
 * that it does not contain the [expected] value.
 *
 * It is a shortcut for `notToContain o value expected`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToContainValue
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.notToContain(expected: E): Expect<T> =
    it notToContain o value expected

/**
 * Expects that the subject of `this` expectation (an [Iterable])  has at least one element and
 * that it does not contain the expected [values].
 *
 * It is a shortcut for `notToContain o the values(...)`
 *
 * @param values The values which should not be contained within the [Iterable]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToContainValues
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.notToContain(values: Values<E>): Expect<T> =
    it notToContain o the values

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toHaveElements
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.toHave(@Suppress("UNUSED_PARAMETER") elements: elements): Expect<T> =
    _logicAppend { hasNext(::identity) }

/**
 * Expects that the subject of `this` expectation (an [Iterable]) does not have next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToHaveElements
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.notToHave(@Suppress("UNUSED_PARAMETER") elements: elements): Expect<T> =
    _logicAppend { hasNotNext(::identity) }


/**
 * Expects that the subject of `this` expectation (an [Iterable]) contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `toContain o inAny order atLeast 1 entry assertionCreatorOrNull`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toHaveElementsAndAny
 *
 * @since 0.17.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toHaveElementsAndAny(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    it toContain o inAny order atLeast 1 entry assertionCreatorOrNull


/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element and
 * that it either does not contain a single entry which holds all assertions created by [assertionCreatorOrNull] or
 * that it does not contain a single entry which is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `notToContain o entry assertionCreatorOrNull`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toHaveElementsAndNone
 *
 * @since 0.17.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toHaveElementsAndNone(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    it notToContain o entry assertionCreatorOrNull

/**
 * Expects that the subject of `this` expectation (an [Iterable]) has at least one element and
 * that either every element holds all assertions created by the [assertionCreatorOrNull] or
 * that all elements are `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toHaveElementsAndAll
 *
 * @since 0.17.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.toHaveElementsAndAll(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    _logicAppend { all(::identity, assertionCreatorOrNull) }


/**
 * Expects that the subject of `this` expectation (an [Iterable]) does not have duplicate elements.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toHaveElementsAndNoDuplicates
 *
 * @since 0.17.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.toHaveElementsAnd(@Suppress("UNUSED_PARAMETER") noDuplicates: noDuplicates): Expect<T> =
    _logicAppend { containsNoDuplicates(::identity) }


/**
 * Expects that the subject of `this` expectation (an [Iterable]) either has no next element or
 * - that any of them holds the expectations the [assertionCreatorOrNull] creates or
 * - that any of them is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToHaveElementsOrAny
 *
 * @since 0.19.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.notToHaveElementsOrAny(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    _logicAppend { hasNotNextOrAny(::identity, assertionCreatorOrNull) }

/**
 * Expects that the subject of `this` expectation (an [Iterable]) either has no next element or
 * - that all of them hold all expectations the [assertionCreatorOrNull] creates or
 * - that all of them are `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToHaveElementsOrAll
 *
 * @since 0.19.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.notToHaveElementsOrAll(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    _logicAppend { hasNotNextOrAll(::identity, assertionCreatorOrNull) }


/**
 * Expects that the subject of `this` expectation (an [Iterable]) either has no next element or
 * - that none of them holds all expectations the [assertionCreatorOrNull] creates or
 * - that none of them is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToHaveElementsOrNone
 *
 * @since 0.19.0
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.notToHaveElementsOrNone(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    _logicAppend { hasNotNextOrNone(::identity, assertionCreatorOrNull) }
