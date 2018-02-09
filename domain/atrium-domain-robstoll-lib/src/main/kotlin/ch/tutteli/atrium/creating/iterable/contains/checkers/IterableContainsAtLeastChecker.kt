package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion

/**
 * Represents a check that an expected entry is contained at least [times] in the [Iterable].
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the [Iterable].
 * @param nameContainsNotFun The function which should be used instead of `atLeastCall` when [times] equals to zero.
 * @param atLeastCall The function which was used and should not be used if [times] equals to zero.
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class IterableContainsAtLeastChecker(
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
) : IterableContainsChecker(times, nameContainsNotFun, atLeastCall) {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = createBasicAssertion(DescriptionIterableAssertion.AT_LEAST, foundNumberOfTimes >= times)
}
