package ch.tutteli.atrium.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.checkers.IterableContainsCheckers

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
abstract class IterableContainsNotCheckerBuilderBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    containsBuilder: IterableContainsBuilder<E, T, S>
) : IterableContainsCheckerBuilder<E, T, S>(containsBuilder) {

    override val checkers = listOf(
        IterableContainsCheckers.newContainsNotChecker()
    )
}

