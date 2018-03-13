package ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.IterableContains.Checker
import ch.tutteli.atrium.creating.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.checkers.checkerFactory

/**
 * The base class for builders which create a `contains at least` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *   is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains at least` check within the fluent API of a
 *   sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param atLeastCall The name of the function which was called and created this builder.
 */
abstract class AtLeastCheckerOptionBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    final override val times: Int,
    final override  val containsBuilder: IterableContains.Builder<E, T, S>,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
) : WithTimesCheckerOption<E, T, S> {

    override val checkers: List<Checker> = listOf(
        checkerFactory.newAtLeastChecker(times, nameContainsNotFun, atLeastCall)
    )
}
