package ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.logic.creating.basic.contains.checkers.impl.ContainsChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.AtMostChecker
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents a check that an expected entry is contained at most [times] in the [Iterable].
 *
 * @param times The number which the check uses to compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param nameContainsNotFun The function which should be used instead of `atMostCall` when [times] equals to zero.
 * @param atMostCall The function which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
class DefaultAtMostChecker(
    times: Int,
    override val nameContainsNotFun: String,
    override val atMostCall: (Int) -> String
) : AtMostChecker, ContainsChecker(times, nameContainsNotFun, atMostCall) {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion =
        createDescriptiveAssertion(DescriptionIterableAssertion.AT_MOST) { foundNumberOfTimes <= times }
}
