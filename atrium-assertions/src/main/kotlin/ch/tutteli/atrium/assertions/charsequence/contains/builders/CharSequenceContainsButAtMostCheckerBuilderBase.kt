package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.validateButAtMost
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtMostChecker

/**
 * The base class for builders which create the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied to the input of the search.
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
abstract class CharSequenceContainsButAtMostCheckerBuilderBase<T : CharSequence, S : ISearchBehaviour>(
    val times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilderBase<T, S>,
    containsBuilder: CharSequenceContainsBuilder<T, S>,
    nameContainsNotFun: String,
    nameAtMostFun: String,
    nameAtLeastFun: String,
    nameButAtMostFun: String,
    nameExactlyFun: String
) : CharSequenceContainsCheckerBuilder<T, S>(containsBuilder) {

    init {
        validateButAtMost(atLeastBuilder.times, times, nameAtLeastFun, nameButAtMostFun, nameExactlyFun)
    }

    override val checkers: List<IChecker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        CharSequenceContainsAtMostChecker(times, nameContainsNotFun, nameAtMostFun)
    )
}
