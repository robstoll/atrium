package ch.tutteli.atrium.logic.creating.charsequence.contains.steps

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the extension point for another step after a `contains not or at most`-check within
 * a sophisticated `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface NotOrAtMostCheckerStep<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerStep<T, S>
