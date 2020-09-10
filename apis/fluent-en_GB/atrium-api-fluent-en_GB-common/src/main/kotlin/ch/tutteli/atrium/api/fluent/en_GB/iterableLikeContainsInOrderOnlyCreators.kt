package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.toVarArg
import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entriesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.valuesInOrderOnly
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.kbox.glue

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [IterableLike]
 * needs to contain only the [expected] value.
 *
 * Delegates to [values].
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param expected The value which is expected to be contained within the [IterableLike].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E, T: IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.value(expected: E): Expect<T> =
    values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [IterableLike]
 * needs to contain only the [expected] value as well as the [otherExpected] values
 * (if given) in the specified order.
 *
 * Note that we might change the signature of this function with the next version
 * which will cause a binary backward compatibility break (see
 * [#292](https://github.com/robstoll/atrium/issues/292) for more information)
 *
 * @param expected The value which is expected to be contained within the [IterableLike].
 * @param otherExpected Additional values which are expected to be contained within [IterableLike].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E, T: IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.values(
    expected: E,
    vararg otherExpected: E
): Expect<T> = _logicAppend { valuesInOrderOnly(expected glue otherExpected) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [IterableLike]
 * needs to contain only a single entry which holds all assertions created by the given [assertionCreatorOrNull]
 * or needs to be `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `entries(assertionCreatorOrNull)`.
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
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E : Any, T: IterableLike> EntryPointStep<out E?, T, InOrderOnlySearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [IterableLike]
 * needs to contain only an entry which holds all assertions [assertionCreatorOrNull] creates
 * or is `null` in case [assertionCreatorOrNull] is defined as `null` and likewise a further entry for each
 * [otherAssertionCreatorsOrNulls] (if given) whereas the entries have to appear in the specified order.
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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E : Any, T: IterableLike> EntryPointStep<out E?, T, InOrderOnlySearchBehaviour>.entries(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = _logicAppend { entriesInOrderOnly(assertionCreatorOrNull glue otherAssertionCreatorsOrNulls) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterableLike]
 * shall be searched within the [IterableLike]
 * (if given) in the specified order.
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
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [IterableLike].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an
 *   [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.14.0 -- API existed for [Iterable] since 0.13.0 but not for [IterableLike].
 */
inline fun <reified E, T: IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> {
    val (first, rest) = toVarArg<E>(expectedIterableLike)
    return values(first, *rest)
}
