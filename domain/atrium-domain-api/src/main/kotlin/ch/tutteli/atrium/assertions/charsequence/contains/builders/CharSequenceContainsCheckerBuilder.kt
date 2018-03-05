package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains

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
    "use the abstract class from package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsCheckerBuilder")
)
interface CharSequenceContainsCheckerBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : ContainsCheckerBuilder<T, S, CharSequenceContains.Checker, CharSequenceContains.Builder<T, S>>,
    CharSequenceContains.CheckerOption<T, S>
