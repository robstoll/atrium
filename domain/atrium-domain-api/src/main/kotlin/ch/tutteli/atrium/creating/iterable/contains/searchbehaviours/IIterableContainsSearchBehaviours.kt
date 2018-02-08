package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder

/**
 * Defines the minimum set of [IterableContains.SearchBehaviour]s an implementation of the domain of Atrium
 * has to provide.
 */
interface IIterableContainsSearchBehaviours {
    fun <E, T : Iterable<E>> containsInAnyOrder(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>

    fun <E, T : Iterable<E>> containsInAnyOrderOnly(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>

    fun <E, T : Iterable<E>> containsInOrder(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>

    fun <E, T : Iterable<E>> containsInOrderOnly(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>
}
