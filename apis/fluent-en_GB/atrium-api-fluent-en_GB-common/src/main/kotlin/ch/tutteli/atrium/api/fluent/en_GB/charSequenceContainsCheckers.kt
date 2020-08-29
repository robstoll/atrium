package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.impl.StaticName
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.*

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we are looking
 * for occurs `at least` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.atLeast(
    times: Int
): AtLeastCheckerStep<T, S> =
    _logic.atLeastCheckerStep(times, StaticName.containsNotValuesFun) { "${StaticName.atLeast}($it)" }

/**
 * Restricts a `contains at least` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `at most` number of [times] within the search input.
 *
 * The resulting restriction will be a `contains at least but at most` assertion.
 *
 * @param times The number which the check will compare against the actual number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *   `at least` restriction; use the [exactly] restriction instead.
 */
fun <T : CharSequence, S : SearchBehaviour> AtLeastCheckerStep<T, S>.butAtMost(
    times: Int
): ButAtMostCheckerStep<T, S> = _logic.butAtMostCheckerStep(
    times,
    StaticName.containsNotValuesFun,
    { l, u -> "${StaticName.atLeast}($l).${StaticName.butAtMost}($u)" },
    { "${StaticName.atLeast}($it)" },
    { "${StaticName.butAtMost}($it)" },
    { "${StaticName.exactly}($it)" },
    { "${StaticName.atMost}($it)" }
)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `exactly` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.exactly(
    times: Int
): ExactlyCheckerStep<T, S> =
    _logic.exactlyCheckerStep(times, StaticName.containsNotValuesFun) { "${StaticName.exactly}($it)" }

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `at least` once but `at most` number of [times] within the search input.
 *
 * If you want to use a higher lower bound than one, then use `atLeast(2).butAtMost(3)` instead of `atMost(3)`.
 * And in case you want to state that it is either not contained at all or at most a certain number of times,
 * then use `notOrAstMost(2)` instead.
 *
 * @param times The number which the check will compare against the actual number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [exactly] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.atMost(
    times: Int
): AtMostCheckerStep<T, S> = _logic.atMostCheckerStep(
    times,
    StaticName.containsNotValuesFun,
    { "${StaticName.atMost}($it)" },
    { "${StaticName.atLeast}($it)" },
    { "${StaticName.exactly}($it)" }
)

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `not at all or at most` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] equals to zero; use [containsNot] instead.
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.notOrAtMost(
    times: Int
): NotOrAtMostCheckerStep<T, S> =
    _logic.notOrAtMostCheckerStep(times, StaticName.containsNotValuesFun) { "${StaticName.notOrAtMost}($it)" }
