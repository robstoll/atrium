package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.*
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderSearchBehaviour

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we are looking
 * for, occurs `at least` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.zumindest(times: Int): IterableContainsAtLeastCheckerBuilder<E, T>
    = IterableContainsAtLeastCheckerBuilder(times, this)

/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `at most` number of [times] within the [Iterable].
 *
 * The resulting restriction will be a `contains at least but at most` assertion.
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *                                  `at least` restriction; use the [genau] restriction instead.
 */
fun <E, T : Iterable<E>> IterableContainsAtLeastCheckerBuilder<E, T>.aberHoechstens(times: Int): IterableContainsButAtMostCheckerBuilder<E, T>
    = IterableContainsButAtMostCheckerBuilder(times, this, containsBuilder)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `genau` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.genau(times: Int): IterableContainsExactlyCheckerBuilder<E, T>
    = IterableContainsExactlyCheckerBuilder(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `at least` once but `at most` number of [times] within the [Iterable].
 *
 * If you want to use a higher lower bound than one, then use `zumindest(2).aberHoechstens(3)` instead of `hoechstens(3)`.
 * And in case you want to state that it is either not contained at all or at most a certain number of times,
 * then use `notOrAstMost(2)` instead.
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [genau] instead.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.hoechstens(times: Int): IterableContainsAtMostCheckerBuilder<E, T>
    = IterableContainsAtMostCheckerBuilder(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `not at all or at most` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.nichtOderHoechstens(times: Int): IterableContainsNotOrAtMostCheckerBuilder<E, T>
    = IterableContainsNotOrAtMostCheckerBuilder(times, this)
