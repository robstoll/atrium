@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.Checker
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsAtLeastChecker
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * The base class for builders which create a `contains at least` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *   is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains at least` check within the fluent API of a
 *   sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param atLeastCall The name of the function which was called and created this builder.
 */
@Deprecated(
    "Use the abstract class from package creating; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilderBase")
)
abstract class CharSequenceContainsAtLeastCheckerBuilderBase<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    val times: Int,
    override val containsBuilder: CharSequenceContains.Builder<T, S>,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
) : CharSequenceContainsCheckerBuilder<T, S> {

    override val checkers: List<Checker> =
        listOf(CharSequenceContainsAtLeastChecker(times, nameContainsNotFun, atLeastCall))
}
