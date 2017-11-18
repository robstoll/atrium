package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.base.contains.builders.ContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains

/**
 * The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 *              `contains` assertion which was started with the given [containsBuilder].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 */
abstract class IterableContainsCheckerBuilder<E, T : Iterable<E>, D : IIterableContains.IDecorator>(
    containsBuilder: IterableContainsBuilder<E, T, D>
) : ContainsCheckerBuilder<T, D, IIterableContains.IChecker, IterableContainsBuilder<E, T, D>>(containsBuilder)
