package ch.tutteli.atrium.assertions.charsequence

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertion

class CharSequenceContainsAtLeastChecker<T : CharSequence>(
    times: Int,
    nameContainsNotFun: String,
    nameAtLeastFun: String
) : CharSequenceContainsChecker<T>(times, nameContainsNotFun, nameAtLeastFun) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = BasicAssertion(DescriptionCharSequenceAssertion.AT_LEAST, times, foundNumberOfTimes >= times)
}
