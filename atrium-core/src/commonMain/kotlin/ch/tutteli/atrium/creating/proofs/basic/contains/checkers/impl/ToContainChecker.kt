package ch.tutteli.atrium.creating.proofs.basic.contains.checkers.impl

import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain

/**
 * Represents a base class for [ToContain.Checker]s which compare how many occurrences of an expected object are found
 * in the input of the search, against how many [times] the check expect it to be contained.
 *
 * It further checks that [times] is bigger than 0 (throws an [IllegalArgumentException] otherwise) and additionally
 * suggest to use a different function if [times] is zero.
 *
 * @property times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 *
 * @constructor Represents a base class for checkers which compare how many occurrences of an expected object are found
 *   in the input of the search, against how many [times] the check expect it to be contained.
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param correctCall The function which should be used instead of `wrongCall` when [times] is zero.
 * @param wrongCall The function call which was used and should not be used if [times] is zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
abstract class ToContainChecker(
    val times: Int,
    correctCall: String,
    wrongCall: (Int) -> String
) : ToContain.Checker {
    init {
        require(times != 0) { "use $correctCall instead of ${wrongCall(0)}" }
        require(times > 0) { "only positive numbers allowed: $times given" }
    }
}
