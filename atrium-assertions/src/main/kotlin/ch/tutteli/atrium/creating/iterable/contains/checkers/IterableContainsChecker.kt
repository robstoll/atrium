package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.creating.basic.contains.checkers.ContainsChecker
import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Represents the base class for [IterableContains.Checker].
 *
 * It does not do much more than specifying that its super class is [ContainsChecker] and sub-classes have to implement
 * [IterableContains.Checker].
 *
 * @param correctCall The function which should be used instead of `wrongCall` when [times] equals to zero.
 * @param wrongCall The function call which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.

 */
abstract class IterableContainsChecker(
    times: Int,
    correctCall: String,
    wrongCall: (Int) -> String
) : ContainsChecker(times, correctCall, wrongCall), IterableContains.Checker
