package ch.tutteli.atrium.assertions.basic.contains.checkers

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents a base class for [IContains.IChecker]s which compare how many occurrences of an expected object are found
 * in the input of the search, against how many [times] the check expect it to be contained.
 *
 * It further checks that [times] is bigger than 0 (throws an [IllegalArgumentException] otherwise) and additionally
 * suggest to use a different function if [times] equals to zero.
 *
 * @property times The number which the check uses to compare against the actual number of times an expected object is
 *           found in the input of the search.
 *
 * @constructor Represents a base class for checkers which compare how many occurrences of an expected object are found
 *              in the input of the search, against how many [times] the check expect it to be contained.
 * @param times The number which the check uses to compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param correctCall The function which should be used instead of `wrongCall` when [times] equals to zero.
 * @param wrongCall The function call which was used and should not be used if [times] equals to zero.
 *
 * @throws IllegalArgumentException In case [times] is smaller than 1.
 */
abstract class ContainsChecker(
    val times: Int,
    correctCall: String,
    wrongCall: (Int) -> String
) : IContains.IChecker {
    init {
        require(times != 0) { "use $correctCall instead of ${wrongCall(0)}" }
        require(times > 0) { "only positive numbers allowed: $times given" }
    }

    /**
     * Creates an [IBasicAssertion] based on the given [description], the property [times] as [RawString]
     * and the given [check].
     *
     * @param description The description used for [IBasicAssertion.description]
     * @param check The check used for [IBasicAssertion.holds]
     *
     * @return The newly created [IBasicAssertion].
     */
    protected fun createBasicAssertion(description: ITranslatable, check: Boolean): IBasicAssertion
        = BasicAssertion(description, RawString(times.toString()), check)
}
