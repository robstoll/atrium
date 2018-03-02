package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Robstoll's implementation of [IIterableContainsSearchBehaviours].
 */
object IterableContainsSearchBehaviours : IIterableContainsSearchBehaviours {
    override fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
        = _containsInAnyOrder(builder)

    override fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
        = _containsInAnyOrderOnly(builder)

    override fun <E, T : Iterable<E>> inOrder(
        containsBuilder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>
        = _containsInOrder(containsBuilder)

    override fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour>
        = _containsInOrderOnly(builder)

}
