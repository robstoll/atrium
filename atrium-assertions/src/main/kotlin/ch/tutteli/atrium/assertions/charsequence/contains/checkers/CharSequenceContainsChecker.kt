package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator

abstract class CharSequenceContainsChecker(
    val times: Int, nameFunToUse: String, nameFunUsed: String
) : CharSequenceContainsAssertionCreator.IChecker {
    init {
        if (times < 0) throw IllegalArgumentException("only positive numbers allowed: $times given")
        if (times == 0) throw IllegalArgumentException("use $nameFunToUse instead of $nameFunUsed(0)")
    }
}
