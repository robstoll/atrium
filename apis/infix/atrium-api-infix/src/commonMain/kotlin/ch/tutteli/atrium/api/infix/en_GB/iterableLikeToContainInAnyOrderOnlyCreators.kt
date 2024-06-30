//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInOrderOnlyReportingOptions
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.values
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.utils.toVarArg
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the [expected] value.
 *
 * Delegates to `the values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [IterableLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): Expect<T> =
    this the values(expected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the expected [values] where it does not matter in which order.
 *
 *
 * @param values The values which are expected to be contained within the [IterableLike]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.the(values: Values<E>): Expect<T> =
    the(WithInAnyOrderOnlyReportingOptions({}, values))

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the expected [values] where it does not matter in which order.
 *
 * @param values The values which are expected to be contained within the [IterableLike] plus a lambda configuring
 *   the [InAnyOrderOnlyReportingOptions] -- use the function `values(t, ..., reportOptionsInAnyOrderOnly = { ... })`
 *   to create a [WithInAnyOrderOnlyReportingOptions] with a wrapped [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.18.0
 */
@JvmName("theValuesWithReportingOptions")
infix fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.the(values: WithInAnyOrderOnlyReportingOptions<Values<E>>): Expect<T> =
    _logicAppend { valuesInAnyOrderOnly(values.t.toList(), values.options) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only one entry which holds all assertions created by the given [assertionCreatorOrNull]
 * or is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the entries(assertionCreatorOrNull)`
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InAnyOrderOnlySearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = this the entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the given [entries] where it does not matter in which order they appear -- an entry
 * is contained if it either holds all assertions
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] creates or it needs to be `null` in case
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toEqual(1) })` but for `entries({ toEqual(1) }, { isGreaterThan(0) })`
 * otherwise `isGreaterThan(0)` matches `1` before `toEqual(1)` would match it. As a consequence `toEqual(1)` could
 * only match the entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param entries The entries which are expected to be contained within the [IterableLike]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InAnyOrderOnlySearchBehaviour>.the(
    entries: Entries<E>
): Expect<T> = the(WithInAnyOrderOnlyReportingOptions({}, entries))

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the given [entries] where it does not matter in which order they appear -- an entry
 * is contained if it either holds all assertions
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] creates or it needs to be `null` in case
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toEqual(1) })` but for `entries({ toEqual(1) }, { isGreaterThan(0) })`
 * otherwise `isGreaterThan(0)` matches `1` before `toEqual(1)` would match it. As a consequence `toEqual(1)` could
 * only match the entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param entries The entries which are expected to be contained within the [IterableLike] plus a lambda configuring
 *   the [InAnyOrderOnlyReportingOptions] -- use the function `entries(t, ..., reportOptionsInAnyOrderOnly = { ... })`
 *   to create a [WithInAnyOrderOnlyReportingOptions] with a wrapped [Entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.18.0
 */
@JvmName("theEntriesWithReportingOptions")
infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InAnyOrderOnlySearchBehaviour>.the(
    entries: WithInAnyOrderOnlyReportingOptions<Entries<E>>
): Expect<T> = _logicAppend { entriesInAnyOrderOnly(entries.t.toList(), entries.options) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only and all elements of [expectedIterableLike] where it does not matter in which order.
 *
 * Delegates to [values].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed (this can be changed via [IterableLikeToIterableTransformer]).
 * This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [IterableLike]
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an
 *   [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.14.0 -- API existed for [Iterable] since 0.13.0 but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInAnyOrderOnlyCreatorSamples.elementsOf
 */
inline infix fun <reified E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = _logic.toVarArg<E>(expectedIterableLike).let { (first, rest) -> this the values(first, *rest) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only and all elements of the given [IterableLike] in the specified order.
 *
 * Delegates to [values].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed (this can be changed via [IterableLikeToIterableTransformer]).
 * This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.

 * @param elementsOf The [IterableLike] whose elements are expected to be contained within
 *   this [IterableLike] plus a lambda configuring the [InOrderOnlyReportingOptions] -- use the function
 *   `elementsOf(iterableLike, report = { ... })`
 *   to create a [WithInOrderOnlyReportingOptions] with a wrapped [IterableLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case the wrapped [IterableLike] is not
 *   an [Iterable], [Sequence] or one of the [Array] types
 *   or the wrapped [IterableLike] does not have elements (is empty).
 *
 * @since 0.18.0
 */
@JvmName("theElementsOfsWithReportingOptions")
inline infix fun <reified E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.the(
    elementsOf: WithInAnyOrderOnlyReportingOptions<IterableLike>
): Expect<T> = _logic.toVarArg<E>(elementsOf.t).let { (first, rest) ->
    this the values(first, *rest, reportOptionsInAnyOrderOnly = elementsOf.options)
}
