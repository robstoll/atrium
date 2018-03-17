package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.basic.contains.checkers.ContainsChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains

/**
 * Represents the base class for [CharSequenceContains.Checker].
 *
 * It does not do much more than specifying that its super class is [ContainsChecker] and sub-classes have to implement
 * [CharSequenceContains.Checker].
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param correctCall The function which should be used instead of `wrongCall` when [times] equals to zero.
 * @param wrongCall The function call which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
@Deprecated("Use the abstract class from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.charsequence.contains.checkers.CharSequenceContainsChecker"))
abstract class CharSequenceContainsChecker(
    times: Int,
    correctCall: String,
    wrongCall: (Int) -> String
) : ContainsChecker(times, correctCall, wrongCall), CharSequenceContains.Checker
