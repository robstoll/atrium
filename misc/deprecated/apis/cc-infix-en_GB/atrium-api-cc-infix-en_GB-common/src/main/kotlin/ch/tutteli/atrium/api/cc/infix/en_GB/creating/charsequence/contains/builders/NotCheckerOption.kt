package ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the extension point for another option after a `contains not at all`-check within
 * a sophisticated `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
@Deprecated("Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.")
interface NotCheckerOption<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerOption<T, S>
