package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents the extension point for another step after a `to contain at least`-check within a sophisticated
 * `toContain` expectation building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @since 1.3.0
 */
//TODO 1.3.0 seems smelly to me, core should not drive the api - we want to reuse the marker interfaces but should they
// really go all way up to the API? On the other hand, it's just a marker interface
interface AtLeastCheckerStep<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>
    : WithTimesCheckerStep<T, S>
