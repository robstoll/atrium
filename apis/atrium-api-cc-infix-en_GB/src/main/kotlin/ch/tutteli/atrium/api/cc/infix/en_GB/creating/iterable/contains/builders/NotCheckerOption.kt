package ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * Represents the extension point for another option after a `contains not at all`-check within
 * a sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface NotCheckerOption<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : IterableContains.CheckerOption<E, T, S>
