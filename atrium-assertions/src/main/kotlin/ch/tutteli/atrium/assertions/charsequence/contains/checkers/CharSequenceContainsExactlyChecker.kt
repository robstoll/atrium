package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertion

class CharSequenceContainsExactlyChecker(
    times: Int,
    nameContainsNotFun: String,
    nameExactlyFun: String
) : CharSequenceContainsChecker(times, nameContainsNotFun, nameExactlyFun) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = createBasicAssertion(DescriptionCharSequenceAssertion.EXACTLY, foundNumberOfTimes == times)
}
