package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtMostChecker

/**
 * The base class for builders which create the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied to the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *                 is found in the input of the search.
 *
 * @constructor The base class for builders which create the second step of a `contains at least but at most` check
 *              within the fluent API of a sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param nameAtMostFun The name of the function which represents a `CharSequence contains at most` assertion.
 * @param nameAtLeastFun The name of the function which represents a `CharSequence contains at least` assertion.
 * @param nameAtMostFun The name of the function which was called and created this builder.
 * @param nameExactlyFun The name of the function which represents a `CharSequence contains exactly` assertion.
 */
abstract class CharSequenceContainsButAtMostCheckerBuilderBase<T : CharSequence, D : IDecorator>(
    val times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilderBase<T, D>,
    containsBuilder: CharSequenceContainsBuilder<T, D>,
    nameContainsNotFun: String,
    nameAtMostFun: String,
    nameAtLeastFun: String,
    nameButAtMostFun: String,
    nameExactlyFun: String
) : CharSequenceContainsCheckerBuilder<T, D>(containsBuilder) {

    init {
        if (atLeastBuilder.times == times) throw IllegalArgumentException(
            "use $nameExactlyFun($times) instead of $nameAtLeastFun($times).$nameButAtMostFun($times)")

        if (atLeastBuilder.times > times) throw IllegalArgumentException(
            "specifying $nameButAtMostFun($times) does not make sense if $nameAtLeastFun(${atLeastBuilder.times}) was used before")
    }

    override val checkers: List<IChecker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        CharSequenceContainsAtMostChecker(times, nameContainsNotFun, nameAtMostFun)
    )
}
