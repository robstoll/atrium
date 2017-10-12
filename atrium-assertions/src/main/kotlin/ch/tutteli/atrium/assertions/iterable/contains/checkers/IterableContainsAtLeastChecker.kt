package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.IAssertion

class IterableContainsAtLeastChecker(
    times: Int,
    nameContainsNotFun: String,
    nameAtLeastFun: String
) : IterableContainsChecker(times, nameContainsNotFun, nameAtLeastFun) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = createBasicAssertion(DescriptionIterableAssertion.AT_LEAST, foundNumberOfTimes >= times)
}
