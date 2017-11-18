package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsExactlyCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we are looking
 * for, occurs `at least` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderDecorator>.atLeast(times: Int): IterableContainsAtLeastCheckerBuilder<E, T>
    = IterableContainsAtLeastCheckerBuilder(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `exactly` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderDecorator>.exactly(times: Int): IterableContainsExactlyCheckerBuilder<E, T>
    = IterableContainsExactlyCheckerBuilder(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `at least` once but `at most` number of [times] within the [Iterable].
 *
 * If you want to use a higher lower bound than one, then use `atLeast(2).butAtMost(3)` instead of `atMost(3)`.
 * And in case you want to state that it is either not contained at all or at most a certain number of times,
 * then use `notOrAstMost(2)` instead.
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [exactly] instead.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderDecorator>.atMost(times: Int): IterableContainsAtMostCheckerBuilder<E, T>
    = IterableContainsAtMostCheckerBuilder(times, this)
