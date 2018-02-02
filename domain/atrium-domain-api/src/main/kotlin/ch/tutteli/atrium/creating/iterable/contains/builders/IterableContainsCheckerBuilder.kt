package ch.tutteli.atrium.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.basic.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.Checker
import ch.tutteli.atrium.creating.iterable.contains.IterableContains

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
abstract class IterableContainsCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>(
    containsBuilder: IterableContainsBuilder<E, T, S>
) : ContainsCheckerBuilder<T, S, IterableContains.Checker, IterableContainsBuilder<E, T, S>>(containsBuilder)
