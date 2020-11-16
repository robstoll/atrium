package ch.tutteli.atrium.logic.creating.iterablelike.contains.steps

import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Represents the extension point for another step after a `contains at least`-check within a sophisticated
 * `contains` assertion building process for [IterableLike].
 *
 * @param E The element type of the IterableLike type [T].
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface AtLeastCheckerStep<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>
    : WithTimesCheckerStep<E, T, S>
