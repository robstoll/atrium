package ch.tutteli.atrium.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.checkers.CheckerFactory

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
abstract class NotCheckerOptionBase<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    override val  containsBuilder: IterableContains.Builder<E, T, S>
) : IterableContains.CheckerOption<E, T, S> {

    override val checkers = listOf(
        CheckerFactory.newNotChecker()
    )
}

