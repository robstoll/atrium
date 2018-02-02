package ch.tutteli.atrium.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.iterable.contains.IterableContains.Checker
import ch.tutteli.atrium.creating.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.checkers.IterableContainsAtMostChecker

/**
 * The base class for builders which create a `contains not or at most` check within the fluent API of a
 * sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create a `contains at most` check within the fluent API of a
 *   sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param notOrAtMostCall The name of the function which was called and created this builder.
 */
abstract class IterableContainsNotOrAtMostCheckerBuilderBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    val times: Int,
    containsBuilder: IterableContainsBuilder<E, T, S>,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String
) : IterableContainsCheckerBuilder<E, T, S>(containsBuilder) {

    override val checkers: List<Checker> = listOf(
        IterableContainsAtMostChecker(times, nameContainsNotFun, notOrAtMostCall)
    )
}
