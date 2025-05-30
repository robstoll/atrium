//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entries
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.values
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.utils.toVarArg
import ch.tutteli.kbox.glue

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the [expected] value.
 *
 * Delegates to [values].
 *
 * @param expected The value which is expected to be contained within the subject (an [IterableLike]).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInAnyOrderOnlyCreatorSamples.value
 */
fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): Expect<T> =
    values(expected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the [expected] value as well as the [otherExpected] values
 * where it does not matter in which order.
 *
 * @param expected The value which is expected to be contained within the subject (an [IterableLike]).
 * @param otherExpected Additional values which are expected to be contained within [IterableLike].
 * @param report The lambda configuring the [InAnyOrderOnlyReportingOptions] -- it is optional where
 *   the default [InAnyOrderOnlyReportingOptions] apply if not specified.
 *   since 0.18.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInAnyOrderOnlyCreatorSamples.values
 */
fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.values(
    expected: E,
    vararg otherExpected: E,
    report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend { valuesInAnyOrderOnly(expected glue otherExpected, report) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only one entry which holds all assertions created by the given [assertionCreatorOrNull]
 * or is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to [entries].
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInAnyOrderOnlyCreatorSamples.entry
 */
fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InAnyOrderOnlySearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only an entry for [assertionCreatorOrNull] as well as for the [otherAssertionCreatorsOrNulls]
 * where it does not matter in which order they appear -- an entry is contained if it either
 * holds all assertions [assertionCreatorOrNull] creates or
 * needs to be `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toEqual(1) })` but for
 * `entries({ toEqual(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toEqual(1)` would match it. As a consequence `toEqual(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 * @param report The lambda configuring the [InAnyOrderOnlyReportingOptions] -- it is optional where
 *   the default [InAnyOrderOnlyReportingOptions] apply if not specified.
 *   since 0.18.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInAnyOrderOnlyCreatorSamples.entries
 */
fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InAnyOrderOnlySearchBehaviour>.entries(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?,
    report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend { entriesInAnyOrderOnly(assertionCreatorOrNull glue otherAssertionCreatorsOrNulls, report) }

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
 * @param report The lambda configuring the [InAnyOrderOnlyReportingOptions] -- it is optional where
 *   the default [InAnyOrderOnlyReportingOptions] apply if not specified.
 *   since 0.18.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not
 *   an [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.14.0 -- API existed for [Iterable] since 0.13.0 but not for [IterableLike].
 */
inline fun <reified E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike,
    noinline report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logic.toVarArg<E>(expectedIterableLike).let { (first, rest) -> values(first, *rest, report = report) }
