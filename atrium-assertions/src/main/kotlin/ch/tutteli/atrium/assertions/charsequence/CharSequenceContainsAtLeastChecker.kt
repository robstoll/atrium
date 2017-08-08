package ch.tutteli.atrium.assertions.charsequence

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertion

class CharSequenceContainsAtLeastChecker(
    times: Int,
    nameContainsNotFun: String,
    nameAtLeastFun: String
) : CharSequenceContainsChecker(times, nameContainsNotFun, nameAtLeastFun) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = BasicAssertion(DescriptionCharSequenceAssertion.AT_LEAST, times, foundNumberOfTimes >= times)
}
