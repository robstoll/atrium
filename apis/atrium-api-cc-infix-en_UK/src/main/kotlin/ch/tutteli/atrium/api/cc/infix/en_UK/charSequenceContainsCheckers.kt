package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders.*
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we are looking
 * for, occurs `at least` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContainsBuilder<T, S>.atLeast(times: Int): CharSequenceContainsAtLeastCheckerBuilder<T, S>
    = CharSequenceContainsAtLeastCheckerBuilder(times, this)

/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `at most` number of [times] within the search input.
 *
 * The resulting restriction will be a `contains at least but at most` assertion.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *   `at least` restriction; use the [exactly] restriction instead.
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContainsAtLeastCheckerBuilder<T, S>.butAtMost(times: Int): CharSequenceContainsButAtMostCheckerBuilder<T, S>
    = CharSequenceContainsButAtMostCheckerBuilder(times, this, containsBuilder)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `exactly` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContainsBuilder<T, S>.exactly(times: Int): CharSequenceContainsExactlyCheckerBuilder<T, S>
    =
    CharSequenceContainsExactlyCheckerBuilder(
        times,
        this
    )

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `at least` once but `at most` number of [times] within the search input.
 *
 * If you want to use a higher lower bound than one, then use `atLeast(2).butAtMost(3)` instead of `atMost(3)`.
 * And in case you want to state that it is either not contained at all or at most a certain number of times,
 * then use `notOrAstMost(2)` instead.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [exactly] instead.
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContainsBuilder<T, S>.atMost(times: Int): CharSequenceContainsAtMostCheckerBuilder<T, S>
    =
    CharSequenceContainsAtMostCheckerBuilder(
        times,
        this
    )

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `not at all or at most` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContainsBuilder<T, S>.notOrAtMost(times: Int): CharSequenceContainsNotOrAtMostCheckerBuilder<T, S>
    =
    CharSequenceContainsNotOrAtMostCheckerBuilder(
        times,
        this
    )
