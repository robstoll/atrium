@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.builders


import ch.tutteli.atrium.assertions.iterable.contains.IterableContains.Checker
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsExactlyChecker

/**
 * The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *   is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 *   `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `Iterable contains not` assertion.
 * @param exactlyCall The name of the function which was called and created this builder.
 */
@Deprecated(
    "Use the abstract class from package creating; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContainsExactlyCheckerBuilderBase")
)
abstract class IterableContainsExactlyCheckerBuilderBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    val times: Int,
    override val containsBuilder: IterableContainsBuilder<E, T, S>,
    nameContainsNotFun: String,
    exactlyCall: (Int) -> String
) : IterableContainsCheckerBuilder<E, T, S> {

    override val checkers: List<Checker> =
        listOf(IterableContainsExactlyChecker(times, nameContainsNotFun, exactlyCall))
}
