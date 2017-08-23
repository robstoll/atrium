package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsExactlyCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we are looking
 * for, occurs `at least` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <T : CharSequence, D : IDecorator> CharSequenceContainsBuilder<T, D>.atLeast(times: Int): CharSequenceContainsAtLeastCheckerBuilder<T, D>
    = CharSequenceContainsAtLeastCheckerBuilder(times, this)

/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `at most` number of [times] within the search input.
 *
 * The resulting restriction will be a `contains at least but at most` assertion.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *                                  `at least` restriction; use the [exactly] restriction instead.
 */
fun <T : CharSequence, D : IDecorator> CharSequenceContainsAtLeastCheckerBuilder<T, D>.butAtMost(times: Int): CharSequenceContainsButAtMostCheckerBuilder<T, D>
    = CharSequenceContainsButAtMostCheckerBuilder(times, this, containsBuilder)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `exactly` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <T : CharSequence, D : IDecorator> CharSequenceContainsBuilder<T, D>.exactly(times: Int): CharSequenceContainsExactlyCheckerBuilder<T, D>
    = CharSequenceContainsExactlyCheckerBuilder(times, this)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `at most` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <T : CharSequence, D : IDecorator> CharSequenceContainsBuilder<T, D>.atMost(times: Int): CharSequenceContainsAtMostCheckerBuilder<T, D>
    = CharSequenceContainsAtMostCheckerBuilder(times, this)
