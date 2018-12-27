@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * The *deprecated* base class for builders which create [IterableContains.Checker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [IterableContains.Checker]s within the fluent API of a sophisticated
 *   `contains` assertion which was started with the given [containsBuilder].
 */
@Deprecated(
    "use the interface IterableContains.CheckerOption instead; will be removed with 1.0.0",
    ReplaceWith(
        "IterableContains.CheckerOption",
        "ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains"
    )
)
interface IterableContainsCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : ContainsCheckerBuilder<T, S, IterableContains.Checker, IterableContains.Builder<E, T, S>>,
    IterableContains.CheckerOption<E, T, S>
