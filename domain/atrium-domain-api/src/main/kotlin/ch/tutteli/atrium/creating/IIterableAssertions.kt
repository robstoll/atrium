package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviour

/**
 * Defines the minimum set of assertion functions and builders applicable to [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IIterableAssertions {
    fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>): IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>): IterableContainsBuilder<E, T, IterableContainsNotSearchBehaviour>
}
