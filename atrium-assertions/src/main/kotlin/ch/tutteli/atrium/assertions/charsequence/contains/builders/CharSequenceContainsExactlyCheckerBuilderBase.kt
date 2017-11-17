package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsExactlyChecker
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator.IDecorator

/**
 * The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *                 is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 *              `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param nameExactlyFun The name of the function which was called and created this builder.
 */
abstract class CharSequenceContainsExactlyCheckerBuilderBase<T : CharSequence, D : IDecorator>(
    val times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, D>,
    nameContainsNotFun: String,
    nameExactlyFun: String
) : CharSequenceContainsCheckerBuilder<T, D>(containsBuilder) {

    override val checkers: List<IChecker> =
        listOf(CharSequenceContainsExactlyChecker(times, nameContainsNotFun, nameExactlyFun))
}
