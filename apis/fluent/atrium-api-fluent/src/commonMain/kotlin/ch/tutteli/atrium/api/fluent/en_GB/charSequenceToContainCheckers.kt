package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.fluent.en_GB.creating.charSequence.toContain.impl.StaticNames
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.SearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.*

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we are looking
 * for occurs `at least` number of [times] within the search input.
 *
 * @param times The number which the check will compare against the actual number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] is zero; use [notToContain] instead.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCheckerSamples.atLeast
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceToContain.EntryPointStep<T, S>.atLeast(
    times: Int
): AtLeastCheckerStep<T, S> =
    _core.atLeastCheckerStep(times, StaticNames.notToContainValuesFun) { "${StaticNames.atLeast}($it)" }

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
 * @throws IllegalArgumentException In case [times] is zero; use [notToContain] instead.
 * @throws IllegalArgumentException In case [times] of this `at most` restriction equals to the number of the
 *   `at least` restriction; use the [exactly] restriction instead.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCheckerSamples.butAtMost
 *
 */
fun <T : CharSequence, S : SearchBehaviour> AtLeastCheckerStep<T, S>.butAtMost(
    times: Int
): ButAtMostCheckerStep<T, S> = _core.butAtMostCheckerStep(
    times,
    StaticNames.notToContainValuesFun,
    { l, u -> "${StaticNames.atLeast}($l).${StaticNames.butAtMost}($u)" },
    { "${StaticNames.atLeast}($it)" },
    { "${StaticNames.butAtMost}($it)" },
    { "${StaticNames.exactly}($it)" },
    { "${StaticNames.atMost}($it)" }
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
 * @throws IllegalArgumentException In case [times] is zero; use [notToContain] instead.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCheckerSamples.exactly
 *
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceToContain.EntryPointStep<T, S>.exactly(
    times: Int
): ExactlyCheckerStep<T, S> =
    _core.exactlyCheckerStep(times, StaticNames.notToContainValuesFun) { "${StaticNames.exactly}($it)" }

/**
 * Restricts a `contains` assertion by specifying that the number of occurrences of the value which we
 * are looking for occurs `at least` once but `at most` number of [times] within the search input.
 *
 * If you want to use a higher lower bound than one, then use `atLeast(2).butAtMost(3)` instead of `atMost(3)`.
 * And in case you want to state that it is either not contained at all or at most a certain number of times,
 * then use `notOrAtMost(2)` instead.
 *
 * @param times The number which the check will compare against the actual number of times an expected value is
 *   found in the input of the search.
 *
 * @return The newly created builder.
 * @throws IllegalArgumentException In case [times] is smaller than zero.
 * @throws IllegalArgumentException In case [times] is zero; use [notToContain] instead.
 * @throws IllegalArgumentException In case [times] equals to one; use [exactly] instead.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCheckerSamples.atMost
 *
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceToContain.EntryPointStep<T, S>.atMost(
    times: Int
): AtMostCheckerStep<T, S> = _core.atMostCheckerStep(
    times,
    StaticNames.notToContainValuesFun,
    { "${StaticNames.atMost}($it)" },
    { "${StaticNames.atLeast}($it)" },
    { "${StaticNames.exactly}($it)" }
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
 * @throws IllegalArgumentException In case [times] is zero; use [notToContain] instead.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCheckerSamples.notOrAtMost
 *
 */
fun <T : CharSequence, S : SearchBehaviour> CharSequenceToContain.EntryPointStep<T, S>.notOrAtMost(
    times: Int
): NotOrAtMostCheckerStep<T, S> =
    _core.notOrAtMostCheckerStep(times, StaticNames.notToContainValuesFun) { "${StaticNames.notOrAtMost}($it)" }
