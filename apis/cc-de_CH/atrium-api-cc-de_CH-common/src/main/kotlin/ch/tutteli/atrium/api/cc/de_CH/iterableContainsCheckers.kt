@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsCheckersKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.*
import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl.*
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we are looking
 * for, occurs `at least` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <E, T : Iterable<E>, S: InAnyOrderSearchBehaviour> IterableContains.Builder<E, T, S>.zumindest(times: Int): AtLeastCheckerOption<E, T, S>
    = AtLeastCheckerOptionImpl(times, this)

/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `at most` number of [times] within the [Iterable].
 *
 * The resulting restriction will be a `contains at least but at most` assertion.
 *
 * @param times The number which the check will compare against the number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *   `at least` restriction; use the [genau] restriction instead.
 */
fun <E, T : Iterable<E>, S: InAnyOrderSearchBehaviour> AtLeastCheckerOption<E, T, S>.aberHoechstens(times: Int): ButAtMostCheckerOption<E, T, S>
    = ButAtMostCheckerOptionImpl(times, this, containsBuilder)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `genau` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <E, T : Iterable<E>, S: InAnyOrderSearchBehaviour> IterableContains.Builder<E, T, S>.genau(times: Int): ExactlyCheckerOption<E, T, S>
    = ExactlyCheckerOptionImpl(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `at least` once but `at most` number of [times] within the [Iterable].
 *
 * If you want to use a higher lower bound than one, then use `zumindest(2).aberHoechstens(3)` instead of `hoechstens(3)`.
 * And in case you want to state that it is either not contained at all or at most a certain number of times,
 * then use `notOrAstMost(2)` instead.
 *
 * @param times The number which the check will compare against the number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [genau] instead.
 */
fun <E, T : Iterable<E>, S: InAnyOrderSearchBehaviour> IterableContains.Builder<E, T, S>.hoechstens(times: Int): AtMostCheckerOption<E, T, S>
    = AtMostCheckerOptionImpl(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the entry which we
 * are looking for occurs `not at all or at most` number of [times] within the [Iterable].
 *
 * @param times The number which the check will compare against the number of times an expected entry is
 *   found in the [Iterable].
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <E, T : Iterable<E>, S: InAnyOrderSearchBehaviour> IterableContains.Builder<E, T, S>.nichtOderHoechstens(times: Int): NotOrAtMostCheckerOption<E, T, S>
    = NotOrAtMostCheckerOptionImpl(times, this)
