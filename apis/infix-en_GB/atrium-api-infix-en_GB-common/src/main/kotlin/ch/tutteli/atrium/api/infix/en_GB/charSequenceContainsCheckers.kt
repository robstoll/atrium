//TODO rename file to charSequenceToContain... in 0.18.0
//TODO remove JvmMultifileClass with 1.0.0
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsCheckersKt")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.impl.StaticNames
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.*
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

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
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.atLeast(
    times: Int
): AtLeastCheckerStep<T, S> =
    _logic.atLeastCheckerStep(times, StaticNames.containsNotValuesFun) { "`${StaticNames.atLeast} $it`" }

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
 */
infix fun <T : CharSequence, S : SearchBehaviour> AtLeastCheckerStep<T, S>.butAtMost(
    times: Int
): ButAtMostCheckerStep<T, S> = _logic.butAtMostCheckerStep(
    times,
    StaticNames.containsNotValuesFun,
    { l, u -> "`${StaticNames.atLeast} $l ${StaticNames.butAtMost} $u`" },
    { "`${StaticNames.atLeast} $it`" },
    { "`${StaticNames.butAtMost} $it`" },
    { "`${StaticNames.exactly} $it`" },
    { "`${StaticNames.atMost} $it`" }
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
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.exactly(
    times: Int
): ExactlyCheckerStep<T, S> =
    _logic.exactlyCheckerStep(times, StaticNames.containsNotValuesFun) { "`${StaticNames.exactly} $it`" }

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
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.atMost(
    times: Int
): AtMostCheckerStep<T, S> = _logic.atMostCheckerStep(
    times,
    StaticNames.containsNotValuesFun,
    { "`${StaticNames.atMost} $it`" },
    { "`${StaticNames.atLeast} $it`" },
    { "`${StaticNames.exactly} $it`" }
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
 */
infix fun <T : CharSequence, S : SearchBehaviour> CharSequenceContains.EntryPointStep<T, S>.notOrAtMost(
    times: Int
): NotOrAtMostCheckerStep<T, S> =
    _logic.notOrAtMostCheckerStep(times, StaticNames.containsNotValuesFun) { "`${StaticNames.notOrAtMost} $it`" }
