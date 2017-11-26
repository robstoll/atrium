package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.basic.contains.checkers.ContainsChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains

/**
 * Represents the base class for [IIterableContains.IChecker].
 *
 * It does not much more than specifying that its super class is [ContainsChecker] and sub-classes have to implement
 * [IIterableContains.IChecker].
 */
abstract class IterableContainsChecker(
    times: Int,
    nameFunToUse: String,
    nameFunUsed: String
) : ContainsChecker(times, nameFunToUse, nameFunUsed), IIterableContains.IChecker
