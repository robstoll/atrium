package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion

/**
 * Represents a check that an expected object is contained exactly [times] in the search input.
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param nameContainsNotFun The function which should be used instead of `exactlyCall` when [times] equals to zero.
 * @param exactlyCall The function call which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class CharSequenceContainsExactlyChecker(
    times: Int,
    nameContainsNotFun: String,
    exactlyCall: (Int) -> String
) : CharSequenceContainsChecker(times, nameContainsNotFun, exactlyCall) {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = createBasicAssertion(DescriptionCharSequenceAssertion.EXACTLY, foundNumberOfTimes == times)
}
