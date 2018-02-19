package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains.Checker

/**
 * The base class for builders which create [Checker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [Checker]s within the fluent API of a sophisticated
 *   `contains` assertion which was started with the given [containsBuilder].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 */
@Deprecated(
    "use the abstract class from package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.IterableContainsCheckerBuilder")
)
abstract class IterableContainsCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>(
    containsBuilder: IterableContainsBuilder<E, T, S>
) : ContainsCheckerBuilder<T, S, IterableContains.Checker, IterableContainsBuilder<E, T, S>>(containsBuilder)
