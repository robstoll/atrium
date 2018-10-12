@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.basic.contains.checkers.ContainsChecker
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains

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
@Deprecated("Use the abstract class from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.checkers.IterableContainsChecker"))
abstract class IterableContainsChecker(
    times: Int,
    correctCall: String,
    wrongCall: (Int) -> String
) : ContainsChecker(times, correctCall, wrongCall), IterableContains.Checker
