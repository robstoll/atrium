package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.basic.contains.checkers.ContainsChecker
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains

/**
 * Represents the base class for [ICharSequenceContains.IChecker].
 *
 * It does not much more than specifying that its super class is [ContainsChecker] and sub-classes have to implement
 * [ICharSequenceContains.IChecker].
 */
abstract class CharSequenceContainsChecker(
    times: Int,
    nameFunToUse: String,
    nameFunUsed: String
) : ContainsChecker(times, nameFunToUse, nameFunUsed), ICharSequenceContains.IChecker
