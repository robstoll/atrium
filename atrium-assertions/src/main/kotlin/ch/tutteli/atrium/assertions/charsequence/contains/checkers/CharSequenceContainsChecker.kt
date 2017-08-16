package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

abstract class CharSequenceContainsChecker(
    val times: Int,
    nameFunToUse: String,
    nameFunUsed: String
) : CharSequenceContainsAssertionCreator.IChecker {
    init {
        if (times < 0) throw IllegalArgumentException("only positive numbers allowed: $times given")
        if (times == 0) throw IllegalArgumentException("use $nameFunToUse instead of $nameFunUsed(0)")
    }

    protected fun createBasicAssertion(description: ITranslatable, check: Boolean): IAssertion
        = BasicAssertion(description, RawString(times.toString()), check)
}
