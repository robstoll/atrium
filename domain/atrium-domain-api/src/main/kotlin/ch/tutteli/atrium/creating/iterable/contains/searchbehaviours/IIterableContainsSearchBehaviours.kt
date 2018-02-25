package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Defines the minimum set of [IterableContains.SearchBehaviour]s an implementation of the domain of Atrium
 * has to provide.
 */
interface IIterableContainsSearchBehaviours {
    fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>

    fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>

    fun <E, T : Iterable<E>> inOrder(
        containsBuilder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>

    fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour>
}
