//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders

import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.WithTimesCheckerOption
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the extension point for another option after a `contains at least`-check within a sophisticated
 * `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
@Deprecated(
    "Use AtLeastCheckerStep from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerStep")
)
interface AtLeastCheckerOption<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : WithTimesCheckerOption<T, S>
