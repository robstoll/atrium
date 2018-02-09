package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder

/**
 * A dummy implementation of [IIterableContainsSearchBehaviours] which should be replaced by an actual implementation.
 */
object IterableContainsSearchBehaviours : IIterableContainsSearchBehaviours {
    override fun <E, T : Iterable<E>> inAnyOrder(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
        = _containsInAnyOrder(containsBuilder)

    override fun <E, T : Iterable<E>> inAnyOrderOnly(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
        = _containsInAnyOrderOnly(containsBuilder)

    override fun <E, T : Iterable<E>> inOrder(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
        = _containsInOrder(containsBuilder)

    override fun <E, T : Iterable<E>> inOrderOnly(
        containsBuilder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
    ): IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>
        = _containsInOrderOnly(containsBuilder)

}
