@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsNotChecker


/**
 * The base class for builders which create a `contains not` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create a `contains at least` check within the fluent API of a
 *   sophisticated `contains` assertion for [Iterable].
 */
@Deprecated(
    "Use the abstract class from package creating; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContainsNotCheckerBuilderBase")
)
abstract class IterableContainsNotCheckerBuilderBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    override val containsBuilder: IterableContainsBuilder<E, T, S>
) : IterableContainsCheckerBuilder<E, T, S> {

    override val checkers = listOf(IterableContainsNotChecker())
}

