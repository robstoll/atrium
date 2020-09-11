//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.toVarArg
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.values
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInAnyOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [IterableLike]
 * needs to contain only the [expected] value.
 *
 * Delegates to `the values(expected)`.
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param expected The value which is expected to be contained within the [IterableLike].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): Expect<T> =
    this the values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * must be contained in [IterableLike] but it does not matter in which order.
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param values The values which are expected to be contained within the [IterableLike]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.the(values: Values<E>): Expect<T> =
    _logicAppend { valuesInAnyOrderOnly(values.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [IterableLike]
 * needs to contain only one entry which holds all assertions created by the given [assertionCreatorOrNull]
 * or is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the entries(assertionCreatorOrNull)`
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
infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InAnyOrderOnlySearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = this the entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry needs to be contained in the
 * [IterableLike] which holds all assertions [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull]
 * creates or it needs to be `null` in case
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null` -- likewise an
 * entry for each [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls] needs to
 * be contained in the [IterableLike] where it does not matter in which order the entries appear but
 * only as many entries should be returned by the [IterableLike] as assertion creators are defined.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for `entries({ toBe(1) }, { isGreaterThan(0) })`
 * otherwise `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could
 * only match the entry which is left -- in this case `2` -- and of course this would fail.
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param entries The entries which are expected to be contained within the [IterableLike]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */

infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InAnyOrderOnlySearchBehaviour>.the(
    entries: Entries<E>
): Expect<T> = _logicAppend { entriesInAnyOrderOnly(entries.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements in
 * [expectedIterableLike] need to be contained in [IterableLike] where it does not matter in which order but only as
 * many entries should be returned by the [IterableLike] as values defined.
 *
 * Delegates to [values].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [IterableLike]
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an
 *   [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.14.0 -- API existed for [Iterable] since 0.13.0 but not for [IterableLike].
 */
inline infix fun <reified E, T : IterableLike> EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> {
    val (first, rest) = toVarArg<E>(expectedIterableLike)
    return this the values(first, *rest)
}
