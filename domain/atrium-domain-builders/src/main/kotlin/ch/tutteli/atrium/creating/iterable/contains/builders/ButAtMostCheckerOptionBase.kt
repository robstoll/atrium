package ch.tutteli.atrium.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.basic.contains.builders.validateButAtMost
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.IterableContains.Checker
import ch.tutteli.atrium.creating.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.checkers.checkerFactory

/**
 * The base class for builders which create the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied to the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *   is found in the input of the search.
 *
 * @constructor The base class for builders which create the second step of a `contains at least but at most` check
 *   within the fluent API of a sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 * @param nameContainsNotFun The name of the function which represents a `Iterable contains not` assertion.
 * @param atMostCall The name of the function which represents a `Iterable contains at most` assertion.
 * @param atLeastCall The name of the function which represents a `Iterable contains at least` assertion.
 * @param atMostCall The name of the function which was called and created this builder.
 * @param exactlyCall The name of the function which represents a `Iterable contains exactly` assertion.
 */
abstract class ButAtMostCheckerOptionBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    final override  val times: Int,
    atLeastBuilder: WithTimesCheckerOption<E, T, S>,
    final override  val containsBuilder: IterableContains.Builder<E, T, S>,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String
) : WithTimesCheckerOption<E, T, S> {

    init {
        validateButAtMost(
            atLeastBuilder.times,
            times,
            atLeastButAtMostCall,
            atLeastCall,
            butAtMostCall,
            exactlyCall
        )
    }

    override val checkers: List<Checker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        checkerFactory.newAtMostChecker(times, nameContainsNotFun, atMostCall)
    )
}
