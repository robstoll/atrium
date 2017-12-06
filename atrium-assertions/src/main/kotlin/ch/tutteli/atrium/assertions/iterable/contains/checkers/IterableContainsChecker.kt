package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.basic.contains.checkers.ContainsChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains

/**
 * Represents the base class for [IIterableContains.IChecker].
 *
 * It does not do much more than specifying that its super class is [ContainsChecker] and sub-classes have to implement
 * [IIterableContains.IChecker].
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
) : ContainsChecker(times, correctCall, wrongCall), IIterableContains.IChecker
