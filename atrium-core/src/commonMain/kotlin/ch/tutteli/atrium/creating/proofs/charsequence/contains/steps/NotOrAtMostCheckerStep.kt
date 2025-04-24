package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents the extension point for another step after a `contains not or at most`-check within
 * a sophisticated `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @since 1.3.0
 */
interface NotOrAtMostCheckerStep<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>
    : CharSequenceToContain.CheckerStep<T, S>
