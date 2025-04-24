package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents the extension point for another step after a `contains at least once but at most`-check within
 * a sophisticated `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @since 1.3.0
 */
interface AtMostCheckerStep<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>
    : WithTimesCheckerStep<T, S>
