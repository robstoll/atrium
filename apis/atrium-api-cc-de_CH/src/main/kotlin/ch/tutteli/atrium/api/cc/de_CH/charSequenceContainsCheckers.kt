package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.*
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder as DeprecatedAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilder as DeprecatedAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilder as DeprecatedButAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsExactlyCheckerBuilder as DeprecatedExactlyCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotOrAtMostCheckerBuilder as DeprecatedNotOrAtMostCheckerBuilder
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
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.zumindest(times: Int): AtLeastCheckerBuilder<T, S>
    = CharSequenceContainsAtLeastCheckerBuilder(times, this)

@Deprecated("Use the extension fun `zumindest`. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.zumindest(times)"))
fun <T : CharSequence, S : SearchBehaviour> zumindest(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedAtLeastCheckerBuilder<T, S>
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
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *   `at least` restriction; use the [genau] restriction instead.
 */
fun <T : CharSequence, S : SearchBehaviour> AtLeastCheckerBuilder<T, S>.aberHoechstens(times: Int): ButAtMostCheckerBuilder<T, S>
    = CharSequenceContainsButAtMostCheckerBuilder(times, this, containsBuilder)

@Deprecated("Use the extension fun `aberHoechstens`. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.aberHoechstens(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> aberHoechstens(checkerBuilder: DeprecatedAtLeastCheckerBuilder<T, S>, times: Int): DeprecatedButAtMostCheckerBuilder<T, S>
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
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.genau(times: Int): ExactlyCheckerBuilder<T, S>
    = CharSequenceContainsExactlyCheckerBuilder(times, this)

@Deprecated("Use the extension fun `genau`. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.genau(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> genau(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedExactlyCheckerBuilder<T, S>
    = DeprecatedExactlyCheckerBuilder(times, builder)


/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the object which we
 * are looking for, occurs `at least` once but `at most` number of [times] within the search input.
 *
 * If you want to use a higher lower bound than one, then use `zumindest(2).aberHoechstens(3)` instead of
 * `hoechstens(3)`. And in case you want to state that it is either not contained at all or at most a certain
 * number of times, then use `nichtOderHoechstens(2)` instead.
 *
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [genau] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.hoechstens(times: Int): AtMostCheckerBuilder<T, S>
    = CharSequenceContainsAtMostCheckerBuilder(times, this)

@Deprecated("Use the extension fun `hoechstens`. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.hoechstens(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> hoechstens(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedAtMostCheckerBuilder<T, S>
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
 * @throws IllegalArgumentException In case [times] equals to zero; use [enthaeltNicht] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.Builder<T, S>.nichtOderHoechstens(times: Int): NotOrAtMostCheckerBuilder<T, S>
    = CharSequenceContainsNotOrAtMostCheckerBuilder(times, this)

@Deprecated("Use the extension fun `nichtOderHoechstens`. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.nichtOderHoechstens(times)"))
fun <T : CharSequence, S : DeprecatedSearchBehaviour> nichtOderHoechstens(builder: DeprecatedBuilder<T, S>, times: Int): DeprecatedNotOrAtMostCheckerBuilder<T, S>
    = DeprecatedNotOrAtMostCheckerBuilder(times, builder)
