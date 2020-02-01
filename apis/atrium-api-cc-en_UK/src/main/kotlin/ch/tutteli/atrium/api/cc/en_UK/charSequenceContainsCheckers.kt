@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.*
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder as DeprecatedAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilder as DeprecatedAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilder as DeprecatedButAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsExactlyCheckerBuilder as DeprecatedExactlyCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsNotOrAtMostCheckerBuilder as DeprecatedNotOrAtMostCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.SearchBehaviour as DeprecatedSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour as DeprecatedNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour as DeprecatedNotSearchBehaviour

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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.atLeast(times)"))
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.atLeast(times: Int): AtLeastCheckerOption<T, S>
    = AtLeastCheckerOptionImpl(times, this)

@Deprecated("Use the extension fun `atLeast`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.atLeast(times)"))
fun <T : CharSequence, S : SearchBehaviour> atLeast(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedAtLeastCheckerBuilder<T, S>
    = DeprecatedAtLeastCheckerBuilder(times, builder)


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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.butAtMost(times)"))
fun <T : CharSequence, S : SearchBehaviour> AtLeastCheckerOption<T, S>.butAtMost(times: Int): ButAtMostCheckerOption<T, S>
    = ButAtMostCheckerOptionImpl(times, this, containsBuilder)

@Deprecated("Use the extension fun `butAtMost`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.butAtMost(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> butAtMost(checkerBuilder: DeprecatedAtLeastCheckerBuilder<T, S>, times: Int): DeprecatedButAtMostCheckerBuilder<T, S>
    = DeprecatedButAtMostCheckerBuilder(times, checkerBuilder, checkerBuilder.containsBuilder)


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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.exactly(times)"))
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.exactly(times: Int): ExactlyCheckerOption<T, S>
    = ExactlyCheckerOptionImpl(times, this)

@Deprecated("Use the extension fun `exactly`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.exactly(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> exactly(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedExactlyCheckerBuilder<T, S>
    = DeprecatedExactlyCheckerBuilder(times, builder)


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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.atMost(times)"))
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.atMost(times: Int): AtMostCheckerOption<T, S>
    = AtMostCheckerOptionImpl(times, this)

@Deprecated("Use the extension fun `atMost`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.atMost(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> atMost(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedAtMostCheckerBuilder<T, S>
    = DeprecatedAtMostCheckerBuilder(times, builder)


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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.notOrAtMost(times)"))
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.notOrAtMost(times: Int): NotOrAtMostCheckerOption<T, S>
    = NotOrAtMostCheckerOptionImpl(times, this)

@Deprecated("Use the extension fun `notOrAtMost`. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.notOrAtMost(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> notOrAtMost(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedNotOrAtMostCheckerBuilder<T, S>
    = DeprecatedNotOrAtMostCheckerBuilder(times, builder)
