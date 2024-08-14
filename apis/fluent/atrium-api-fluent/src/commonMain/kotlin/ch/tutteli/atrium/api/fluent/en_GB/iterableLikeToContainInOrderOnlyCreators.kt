package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInOrderOnlyCreatorSamples.value
 */
fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.value(expected: E): Expect<T> =
    values(expected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only the [expected] value as well as the [otherExpected] values
 * in the specified order.
 *
 * @param expected The value which is expected to be contained within the subject (an [IterableLike]).
 * @param otherExpected Additional values which are expected to be contained within [IterableLike].
 * @param report The lambda configuring the [InOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
 *   since 0.17.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInOrderOnlyCreatorSamples.values
 */
fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.values(
    expected: E,
    vararg otherExpected: E,
    report: InOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend { valuesInOrderOnly(expected glue otherExpected, report) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only one entry which holds all assertions created by the given [assertionCreatorOrNull]
 * or is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `entries(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InOrderOnlySearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only an entry for [assertionCreatorOrNull] as well as for the [otherAssertionCreatorsOrNulls]
 * in the specified order -- an entry is contained if it either
 * holds all assertions [assertionCreatorOrNull] creates or
 * needs to be `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 * @param report The lambda configuring the [InOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
 *   since 0.17.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InOrderOnlySearchBehaviour>.entries(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?,
    report: InOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logicAppend { entriesInOrderOnly(assertionCreatorOrNull glue otherAssertionCreatorsOrNulls, report) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain only and all elements of [expectedIterableLike] in the specified order.
 *
 * Delegates to [values].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed (this can be changed via [IterableLikeToIterableTransformer]).
 * This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [IterableLike].
 * @param report The lambda configuring the [InOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
     since 0.17.0
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an
 *   [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.14.0 -- API existed for [Iterable] since 0.13.0 but not for [IterableLike].
 */
inline fun <reified E, T : IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike,
    noinline report: InOrderOnlyReportingOptions.() -> Unit = {}
): Expect<T> = _logic.toVarArg<E>(expectedIterableLike).let { (first, rest) -> values(first, *rest, report = report) }
