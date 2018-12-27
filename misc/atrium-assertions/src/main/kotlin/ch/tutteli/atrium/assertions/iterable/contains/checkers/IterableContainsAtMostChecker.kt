@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents a check that an expected entry is contained at most [times] in the [Iterable].
 *
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param nameContainsNotFun The function which should be used instead of `atMostCall` when [times] equals to zero.
 * @param atMostCall The function which was used and should not be used if [times] equals to zero.
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
@Deprecated("Use IterableContainsCheckers.newAtMostChecker; will be removed with 1.0.0", ReplaceWith("IterableContainsCheckers.newAtMostChecker(times, nameContainsNotFun, atMostCall)", "ch.tutteli.atrium.creating.iterable.contains.checkers.IterableContainsCheckers"))
class IterableContainsAtMostChecker(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String
) : IterableContainsChecker(times, nameContainsNotFun, atMostCall) {

    override fun createAssertion(foundNumberOfTimes: Int): Assertion
        = createBasicAssertion(DescriptionIterableAssertion.AT_MOST, foundNumberOfTimes <= times)
}
