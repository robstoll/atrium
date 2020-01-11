package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders

import ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.WithTimesCheckerOption
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * Represents the extension point for another option after a `contains at least but at most`-check within a
 * sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 0.10.0")
interface ButAtMostCheckerOption<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : WithTimesCheckerOption<E, T, S>
