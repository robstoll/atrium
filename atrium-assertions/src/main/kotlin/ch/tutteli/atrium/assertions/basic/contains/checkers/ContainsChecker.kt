package ch.tutteli.atrium.assertions.basic.contains.checkers

import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.StringBasedRawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a base class for [Contains.Checker]s which compare how many occurrences of an expected object are found
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
) : Contains.Checker {
    init {
        require(times != 0) { "use $correctCall instead of ${wrongCall(0)}" }
        require(times > 0) { "only positive numbers allowed: $times given" }
    }

    /**
     * Creates an [DescriptiveAssertion] based on the given [description], the property [times] as [StringBasedRawString]
     * and the given [check].
     *
     * @param description The description used for [DescriptiveAssertion.description]
     * @param check The check used for [DescriptiveAssertion.holds]
     *
     * @return The newly created [DescriptiveAssertion].
     */
    protected fun createBasicAssertion(description: Translatable, check: Boolean): DescriptiveAssertion
        = BasicDescriptiveAssertion(description, RawString.create(times.toString()), check)
}
