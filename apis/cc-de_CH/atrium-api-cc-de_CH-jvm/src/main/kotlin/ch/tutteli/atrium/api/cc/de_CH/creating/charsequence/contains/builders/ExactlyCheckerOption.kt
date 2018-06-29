package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the extension point for another option after a `contains exactly`-check within
 * a sophisticated `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface ExactlyCheckerOption<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerOption<T, S>
