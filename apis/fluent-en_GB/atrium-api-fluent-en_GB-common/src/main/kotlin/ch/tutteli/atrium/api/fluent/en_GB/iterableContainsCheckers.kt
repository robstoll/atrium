package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.impl.StaticName
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.*

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we are looking
 * for occurs `at least` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <E, T : Any, S : InAnyOrderSearchBehaviour> IterableLikeContains.EntryPointStep<E, T, S>.atLeast(
    times: Int
): AtLeastCheckerStep<E, T, S> =
    _logic.atLeastCheckerStep(times, StaticName.containsNotValuesFun) { "${StaticName.atLeast}($it)" }

/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `at most` number of [times] within the [Iterable].
 *
 * The resulting restriction will be a `contains at least but at most` assertion.
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *   `at least` restriction; use the [exactly] restriction instead.
 */
fun <E, T : Any, S : InAnyOrderSearchBehaviour> AtLeastCheckerStep<E, T, S>.butAtMost(
    times: Int
): ButAtMostCheckerStep<E, T, S> = _logic.butAtMostCheckerStep(
    times,
    StaticName.containsNotValuesFun,
    { l, u -> "${StaticName.atLeast}($l).${StaticName.butAtMost}($u)" },
    { "${StaticName.atLeast}($it)" },
    { "${StaticName.butAtMost}($it)" },
    { "${StaticName.exactly}($it)" },
    { "${StaticName.atMost}($it)" }
)


/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `exactly` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <E, T : Any, S : InAnyOrderSearchBehaviour> IterableLikeContains.EntryPointStep<E, T, S>.exactly(
    times: Int
): ExactlyCheckerStep<E, T, S> =
    _logic.exactlyCheckerStep(times, StaticName.containsNotValuesFun) { "${StaticName.exactly}($it)" }

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `at least` once but `at most` number of [times] within the [Iterable].
 *
 * If you want to use a higher lower bound than one, then use `atLeast(2).butAtMost(3)` instead of `atMost(3)`.
 * And in case you want to state that it is either not contained at all or at most a certain number of times,
 * then use `notOrAstMost(2)` instead.
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [exactly] instead.
 */
fun <E, T : Any, S : InAnyOrderSearchBehaviour> IterableLikeContains.EntryPointStep<E, T, S>.atMost(
    times: Int
): AtMostCheckerStep<E, T, S> = _logic.atMostCheckerStep(
    times,
    StaticName.containsNotValuesFun,
    { "${StaticName.atMost}($it)" },
    { "${StaticName.atLeast}($it)" },
    { "${StaticName.exactly}($it)" }
)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `not at all or at most` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <E, T : Any, S : InAnyOrderSearchBehaviour> IterableLikeContains.EntryPointStep<E, T, S>.notOrAtMost(
    times: Int
): NotOrAtMostCheckerStep<E, T, S> =
    _logic.notOrAtMostCheckerStep(times, StaticName.containsNotValuesFun) { "${StaticName.notOrAtMost}($it)" }
