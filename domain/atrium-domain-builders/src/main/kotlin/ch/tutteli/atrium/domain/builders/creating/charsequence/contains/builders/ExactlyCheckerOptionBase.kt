package ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.Checker
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.checkers.checkerFactory

/**
 * The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *   is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param exactlyCall The function call which should not be used if [times] equals to zero.
 */
abstract class ExactlyCheckerOptionBase<out T : CharSequence, out S : SearchBehaviour>(
    final override val times: Int,
    final override val containsBuilder: CharSequenceContains.Builder<T, S>,
    nameContainsNotFun: String,
    exactlyCall: (Int) -> String
) : WithTimesCheckerOption<T, S> {

    override val checkers: List<Checker> = listOf(
        checkerFactory.newExactlyChecker(times, nameContainsNotFun, exactlyCall)
    )
}
