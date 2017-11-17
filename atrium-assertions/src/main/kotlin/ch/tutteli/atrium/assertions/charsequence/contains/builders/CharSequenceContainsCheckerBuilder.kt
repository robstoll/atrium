package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.base.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IDecorator

/**
 * The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 *              `contains` assertion which was started with the given [containsBuilder].
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
abstract class CharSequenceContainsCheckerBuilder<out T : CharSequence, D : IDecorator>(
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : ContainsCheckerBuilder<T, D, IChecker, CharSequenceContainsBuilder<T, D>>(containsBuilder)
