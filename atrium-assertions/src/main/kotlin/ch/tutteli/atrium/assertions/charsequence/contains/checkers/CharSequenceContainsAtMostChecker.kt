package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertion

class CharSequenceContainsAtMostChecker(
    times: Int,
    nameContainsNotFun: String,
    nameAtMostFun: String
) : CharSequenceContainsChecker(times, nameContainsNotFun, nameAtMostFun) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = createBasicAssertion(DescriptionCharSequenceAssertion.AT_MOST, foundNumberOfTimes <= times)
}
