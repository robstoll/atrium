package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearchBehaviour

/**
 * The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 *              `contains` assertion which was started with the given [containsBuilder].
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
abstract class CharSequenceContainsCheckerBuilder<out T : CharSequence, S : ISearchBehaviour>(
    containsBuilder: CharSequenceContainsBuilder<T, S>
) : ContainsCheckerBuilder<T, S, IChecker, CharSequenceContainsBuilder<T, S>>(containsBuilder)
