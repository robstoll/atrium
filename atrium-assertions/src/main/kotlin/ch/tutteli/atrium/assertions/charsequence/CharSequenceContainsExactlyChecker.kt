package ch.tutteli.atrium.assertions.charsequence

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertion

class CharSequenceContainsExactlyChecker<T : CharSequence>(
    val times: Int,
    nameContainsNotFun: String,
    nameExactlyFun: String
) : CharSequenceContainsAssertionCreator.IChecker<T> {

    init {
        if (times < 0) throw IllegalArgumentException("only positive numbers allowed: $times given")
        if (times == 0) throw IllegalArgumentException("use $nameContainsNotFun instead of $nameExactlyFun(0)")
    }

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = BasicAssertion(DescriptionCharSequenceAssertion.EXACTLY, times, foundNumberOfTimes == times)
}
