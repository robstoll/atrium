package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtLeastChecker
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtMostChecker

/**
 * The base class for builders which create a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *                 is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains at most` check within the fluent API of a sophisticated
 *              `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param nameAtMostFun The name of the function which was called and created this builder.
 * @param nameAtLeastFun The name of the function which represents a `CharSequence contains at least` assertion.
 * @param nameExactlyFun The name of the function which represents a `CharSequence contains exactly` assertion.
 */
abstract class CharSequenceContainsAtMostCheckerBuilderBase<T : CharSequence, D : IDecorator>(
    val times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, D>,
    nameContainsNotFun: String,
    nameAtMostFun: String,
    nameAtLeastFun: String,
    nameExactlyFun: String
) : CharSequenceContainsCheckerBuilder<T, D>(containsBuilder) {

    init {
        if (1 == times) throw IllegalArgumentException(
            "use $nameExactlyFun($times) instead of $nameAtMostFun($times); $nameAtMostFun defines implicitly $nameAtLeastFun(1) as well")
    }

    override val checkers: List<IChecker> = listOf(
        CharSequenceContainsAtLeastChecker(1, nameContainsNotFun, nameAtMostFun),
        CharSequenceContainsAtMostChecker(times, nameContainsNotFun, nameAtMostFun)
    )
}
