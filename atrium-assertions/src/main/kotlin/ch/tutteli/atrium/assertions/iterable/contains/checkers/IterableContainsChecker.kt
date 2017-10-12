package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

abstract class IterableContainsChecker(
    val times: Int,
    nameFunToUse: String,
    nameFunUsed: String
) : IterableContainsAssertionCreator.IChecker {
    init {
        if (times < 0) throw IllegalArgumentException("only positive numbers allowed: $times given")
        if (times == 0) throw IllegalArgumentException("use $nameFunToUse instead of $nameFunUsed(0)")
    }

    protected fun createBasicAssertion(description: ITranslatable, check: Boolean): IBasicAssertion
        = BasicAssertion(description, RawString(times.toString()), check)
}
