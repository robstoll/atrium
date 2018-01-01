package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.basic.contains.builders.ContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Represents the entry point of the fluent API of sophisticated `contains` assertions.
 * It contains the [plant] for which the [Assertion] shall be build as well as the decoration behaviour which shall be
 * applied to the [plant]'s [subject][AssertionPlant.subject].
 *
 * @param T The input type of the search which is the same as the type of the [subject][AssertionPlant.subject] of the
 *          [plant].
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the entry point of the fluent API of sophisticated `contains` assertions.
 * @param plant The [AssertionPlant] for which the sophisticated `contains` assertions shall be built.
 * @param searchBehaviour The search behaviour which shall be applied to the input of the search.
 */
class IterableContainsBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>(
    plant: AssertionPlant<T>, searchBehaviour: S
) : ContainsBuilder<T, S>(plant, searchBehaviour)
