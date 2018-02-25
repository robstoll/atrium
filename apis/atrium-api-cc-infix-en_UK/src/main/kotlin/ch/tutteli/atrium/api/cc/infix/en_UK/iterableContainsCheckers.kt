package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.*
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder as DeprecatedAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders.IterableContainsAtMostCheckerBuilder as DeprecatedAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders.IterableContainsButAtMostCheckerBuilder as DeprecatedButAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders.IterableContainsExactlyCheckerBuilder as DeprecatedExactlyCheckerBuilder
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders.IterableContainsNotOrAtMostCheckerBuilder as DeprecatedNotOrAtMostCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder as DeprecatedBuilder

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we are looking
 * for, occurs `at least` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.atLeast(times: Int): IterableContainsAtLeastCheckerBuilder<E, T>
    = IterableContainsAtLeastCheckerBuilder(times, this)

@Deprecated("use the extension fun `atLeast` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder atLeast times"))
fun <E, T : Iterable<E>> atLeast(builder:  DeprecatedBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>, times: Int): DeprecatedAtLeastCheckerBuilder<E, T>
    = DeprecatedAtLeastCheckerBuilder(times, builder)


/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `at most` number of [times] within the [Iterable].
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
infix fun <E, T : Iterable<E>> IterableContainsAtLeastCheckerBuilder<E, T>.butAtMost(times: Int): IterableContainsButAtMostCheckerBuilder<E, T>
    = IterableContainsButAtMostCheckerBuilder(times, this, containsBuilder)

@Deprecated("use the extension fun `butAtMost` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder butAtMost times"))
fun <E, T : Iterable<E>> butAtMost(checkerBuilder: DeprecatedAtLeastCheckerBuilder<E, T>, times: Int): DeprecatedButAtMostCheckerBuilder<E, T>
    = DeprecatedButAtMostCheckerBuilder(times, checkerBuilder, checkerBuilder.containsBuilder)


/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `exactly` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.exactly(times: Int): IterableContainsExactlyCheckerBuilder<E, T>
    = IterableContainsExactlyCheckerBuilder(times, this)

@Deprecated("use the extension fun `exactly` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder exactly times"))
fun <E, T : Iterable<E>> exactly(builder: DeprecatedBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>, times: Int): DeprecatedExactlyCheckerBuilder<E, T>
    = DeprecatedExactlyCheckerBuilder(times, builder)


/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `at least` once but `at most` number of [times] within the [Iterable].
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
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.atMost(times: Int): IterableContainsAtMostCheckerBuilder<E, T>
    = IterableContainsAtMostCheckerBuilder(times, this)

@Deprecated("use the extension fun `atMost` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder atMost times"))
fun <E, T : Iterable<E>> atMost(builder: DeprecatedBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>, times: Int): DeprecatedAtMostCheckerBuilder<E, T>
    = DeprecatedAtMostCheckerBuilder(times, builder)


/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for, occurs `not at all or at most` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.notOrAtMost(times: Int): IterableContainsNotOrAtMostCheckerBuilder<E, T>
    = IterableContainsNotOrAtMostCheckerBuilder(times, this)

@Deprecated("use the extension fun `notOrAtMost` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder notOrAtMost times"))
fun <E, T : Iterable<E>> notOrAtMost(builder: DeprecatedBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>, times: Int): DeprecatedNotOrAtMostCheckerBuilder<E, T>
    = DeprecatedNotOrAtMostCheckerBuilder(times, builder)
