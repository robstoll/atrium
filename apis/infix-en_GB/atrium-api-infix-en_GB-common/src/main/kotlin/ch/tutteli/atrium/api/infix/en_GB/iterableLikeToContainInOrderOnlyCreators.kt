package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Entries
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.utils.toVarArg

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (an [IterableLike])
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
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.value(expected: E): Expect<T> =
    this the values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (an [IterableLike])
 * needs to contain only the expected [values] in the specified order.
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param values The values which are expected to be contained within the [IterableLike]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.the(values: Values<E>): Expect<T> =
    _logicAppend {
        valuesInOrderOnly(
            values.toList(),
            //TODO 0.18.0 add: report: InOrderOnlyReportingOptions.() -> Unit = {}
            {}
        )
    }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (an [IterableLike])
 * needs to contain only one entry which holds all assertions created by the given [assertionCreatorOrNull]
 * or is `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the entries(assertionCreatorOrNull)`.
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
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InOrderOnlySearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = this the entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (an [IterableLike])
 * needs to contain only the given [entries] in the specified order -- an entry
 * is contained if it either holds all assertions
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] creates or it needs to be `null` in case
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null`
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param entries The entries which are expected to be contained within the [IterableLike]
 *   -- use the function `entries(t, ...)` to create an [Entries].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */

infix fun <E : Any, T : IterableLike> EntryPointStep<out E?, T, InOrderOnlySearchBehaviour>.the(
    entries: Entries<E>
): Expect<T> = _logicAppend {
    entriesInOrderOnly(
        entries.toList(),
        //TODO 0.18.0 add: report: InOrderOnlyReportingOptions.() -> Unit = {}
        {}
    )
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where the subject (an [IterableLike])
 * needs to contain only and all elements of [expectedIterableLike] in the specified order.
 *
 * Delegates to [values].
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed (this can be changed via [IterableLikeToIterableTransformer]).
 * This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within
 *   this [IterableLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not
 *   an [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.14.0 -- API existed for [Iterable] since 0.13.0 but not for [IterableLike].
 */
//TODO 0.18.0 add: report: InOrderOnlyReportingOptions.() -> Unit = {}
inline infix fun <reified E, T : IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = _logic.toVarArg<E>(expectedIterableLike).let { (first, rest) -> this the values(first, *rest) }
