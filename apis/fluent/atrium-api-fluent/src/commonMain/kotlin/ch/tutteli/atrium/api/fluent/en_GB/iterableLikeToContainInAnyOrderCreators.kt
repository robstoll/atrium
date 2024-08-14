package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.toContain.impl.StaticNames
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.CheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.entries
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.values
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.utils.toVarArg
import ch.tutteli.kbox.glue

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain the [expected] value.
 *
 * Delegates to [values].
 *
 * @param expected The value which is expected to be contained within this [IterableLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInAnyOrderCreatorSamples.value
 */
fun <E, T: IterableLike> CheckerStep<E, T, InAnyOrderSearchBehaviour>.value(expected: E): Expect<T> =
    values(expected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain the [expected] value as well as the [otherExpected] values where it does not matter
 * in which order they appear.
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [expected] is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).values('a')`
 * instead of:
 *   `contains.inAnyOrder.atLeast(1).values('a', 'a')`
 *
 * @param expected The object which is expected to be contained within this [IterableLike].
 * @param otherExpected Additional objects which are expected to be contained within this [IterableLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IterableLikeToContainInAnyOrderCreatorSamples.values
 */
fun <E, T: IterableLike> CheckerStep<E, T, InAnyOrderSearchBehaviour>.values(
    expected: E,
    vararg otherExpected: E
): Expect<T> = _logicAppend { values(expected glue otherExpected, StaticNames.notToHaveElementsOrNone) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain an entry which either holds all assertions [assertionCreatorOrNull] creates or
 * needs to be `null` in case [assertionCreatorOrNull] is defined as `null`.
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
 */
fun <E : Any, T: IterableLike> CheckerStep<out E?, T, InAnyOrderSearchBehaviour>.entry(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Expect<T> = entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain an entry for [assertionCreatorOrNull] as well as for the [otherAssertionCreatorsOrNulls]
 * where it does not matter in which order they appear -- an entry is contained if it either
 * holds all assertions [assertionCreatorOrNull] creates or
 * needs to be `null` in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
fun <E : Any, T: IterableLike> CheckerStep<out E?, T, InAnyOrderSearchBehaviour>.entries(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<E>.() -> Unit)?
): Expect<T> = _logicAppend { entries(assertionCreatorOrNull glue otherAssertionCreatorsOrNulls, StaticNames.notToHaveElementsOrNone) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the subject (an [IterableLike])
 * needs to contain all elements of the [expectedIterableLike] where it does not matter in which order they appear.
 *
 * Delegates to [values] which also means that it does not search for unique matches
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed (this can be changed via [IterableLikeToIterableTransformer]).
 * This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within this [IterableLike].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not
 *   an [Iterable], [Sequence] or one of the [Array] types
 *   or the given [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.14.0 -- API existed for [Iterable] since 0.13.0 but not for [IterableLike].
 */
inline fun <reified E, T: IterableLike> CheckerStep<E, T, InAnyOrderSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> = _logic.toVarArg<E>(expectedIterableLike).let { (first, rest) -> values(first, *rest) }
