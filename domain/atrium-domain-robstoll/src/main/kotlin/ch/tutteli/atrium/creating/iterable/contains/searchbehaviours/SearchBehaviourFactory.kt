package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

/**
 * Robstoll's implementation of [ISearchBehaviourFactory].
 */
object SearchBehaviourFactory : ISearchBehaviourFactory {

    override fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
        = _containsInAnyOrder(builder)

    override fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>
        = _containsInAnyOrderOnly(builder)

    override fun <E, T : Iterable<E>> inOrder(
        containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderSearchBehaviour>
        = _containsInOrder(containsBuilder)

    override fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
        = _containsInOrderOnly(builder)

}
