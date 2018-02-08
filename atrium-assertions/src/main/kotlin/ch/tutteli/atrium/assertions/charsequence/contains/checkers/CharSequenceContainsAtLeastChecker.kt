package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion

/**
 * Represents a check that an expected object is contained at least [times] in the search input.
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param nameContainsNotFun The function which should be used instead of `atLeastCall` when [times] equals to zero.
 * @param atLeastCall The function which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
@Deprecated("use CharSequenceContainsCheckers.newAtLeastChecker, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsCheckers.newAtLeastChecker(times, nameContainsNotFun, atLeastCall)", "ch.tutteli.atrium.creating.charsequence.contains.checkers.CharSequenceContainsCheckers"))
class CharSequenceContainsAtLeastChecker(
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
) : CharSequenceContainsChecker(times, nameContainsNotFun, atLeastCall) {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = createBasicAssertion(DescriptionCharSequenceAssertion.AT_LEAST, foundNumberOfTimes >= times)
}
