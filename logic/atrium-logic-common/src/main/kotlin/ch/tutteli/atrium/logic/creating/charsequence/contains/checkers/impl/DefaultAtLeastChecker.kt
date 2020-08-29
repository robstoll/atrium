package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.logic.creating.basic.contains.checkers.impl.ContainsChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_LEAST

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param nameContainsNotFun The function which should be used instead of `atLeastCall` when [times] equals to zero.
 * @param atLeastCall The function which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class DefaultAtLeastChecker(
    times: Int,
    override val nameContainsNotFun: String,
    override val atLeastCall: (Int) -> String
) : AtLeastChecker, ContainsChecker(times, nameContainsNotFun, atLeastCall) {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion =
        createDescriptiveAssertion(AT_LEAST) { foundNumberOfTimes >= times }
}
