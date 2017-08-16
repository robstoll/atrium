package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.IAssertion

class CharSequenceContainsAtLeastChecker(
    times: Int,
    nameContainsNotFun: String,
    nameAtLeastFun: String
) : CharSequenceContainsChecker(times, nameContainsNotFun, nameAtLeastFun) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = createBasicAssertion(DescriptionCharSequenceAssertion.AT_LEAST, foundNumberOfTimes >= times)
}
