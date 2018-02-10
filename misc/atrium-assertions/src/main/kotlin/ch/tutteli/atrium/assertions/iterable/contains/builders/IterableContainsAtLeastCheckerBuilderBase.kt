package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.IterableContains.Checker
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsAtLeastChecker

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
 * @param containsBuilder The previously used [IterableContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param atLeastCall The name of the function which was called and created this builder.
 */
@Deprecated("use the abstract class from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContainsAtLeastCheckerBuilderBase"))
abstract class IterableContainsAtLeastCheckerBuilderBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    val times: Int,
    containsBuilder: IterableContainsBuilder<E, T, S>,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
) : IterableContainsCheckerBuilder<E, T, S>(containsBuilder) {

    override val checkers: List<Checker> =
        listOf(IterableContainsAtLeastChecker(times, nameContainsNotFun, atLeastCall))
}
