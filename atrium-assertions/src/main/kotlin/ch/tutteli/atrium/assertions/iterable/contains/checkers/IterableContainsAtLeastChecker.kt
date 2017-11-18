package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents a check that an expected entry is contained at least [times] in the [Iterable].
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param nameContainsNotFun The function which should be used instead of [nameAtLeastFun] when [times] equals to zero.
 * @param nameAtLeastFun The function which was used and should not be used if [times] equals to zero.
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class IterableContainsAtLeastChecker(
    times: Int,
    nameContainsNotFun: String,
    nameAtLeastFun: String
) : IterableContainsChecker(times, nameContainsNotFun, nameAtLeastFun) {

    override fun createAssertion(foundNumberOfTimes: Int): IAssertion
        = createBasicAssertion(DescriptionIterableAssertion.AT_LEAST, foundNumberOfTimes >= times)
}
