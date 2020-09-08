package ch.tutteli.atrium.logic.creating.iterable.contains.steps

import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains

/**
 * Represents the extension point for another step after a `contains at least`-check within a sophisticated
 * `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface AtLeastCheckerStep<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>
    : WithTimesCheckerStep<E, T, S>
