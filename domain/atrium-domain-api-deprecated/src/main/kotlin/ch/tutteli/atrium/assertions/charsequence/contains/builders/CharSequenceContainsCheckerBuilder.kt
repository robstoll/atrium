package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * The *deprecated* base class for builders which create [CharSequenceContains.Checker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [CharSequenceContains.Checker]s within the fluent API of a sophisticated
 *   `contains` assertion which was started with the given [containsBuilder].
 */
@Deprecated(
    "use the interface CharSequenceContains.CheckerOption instead, will be removed with 1.0.0",
    ReplaceWith(
        "CharSequenceContains.CheckerOption",
        "ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains"
    )
)
interface CharSequenceContainsCheckerBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : ContainsCheckerBuilder<T, S, CharSequenceContains.Checker, CharSequenceContains.Builder<T, S>>,
    CharSequenceContains.CheckerOption<T, S>
